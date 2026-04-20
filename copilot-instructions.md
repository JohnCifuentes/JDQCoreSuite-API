# Copilot Instructions for JDQCoreSuite

## Objetivo de este documento
Estas instrucciones indican cómo debe trabajar GitHub Copilot dentro de este repositorio. La prioridad no es inventar una arquitectura nueva, sino respetar y extender el flujo real ya implementado en JDQCoreSuite.

Copilot debe comportarse como un asistente de mantenimiento y evolución del backend, preservando:
- la estructura modular actual,
- las reglas de seguridad con JWT y 2FA,
- el patrón Controller -> Service -> Repository -> Mapper -> Entity/DTO,
- el uso de `RespuestaDTO` como envoltorio estándar de respuestas,
- las convenciones de documentación y comentarios en español.

---

## Contexto técnico del proyecto
- Backend en **Java 21** con **Spring Boot 3.4.1**.
- Build con **Gradle Kotlin DSL**.
- Persistencia con **Spring Data JPA** y **PostgreSQL**.
- Migraciones administradas con **Flyway**.
- Seguridad implementada con **Spring Security**, **JWT** y flujo de **doble factor**.
- Mapeo DTO <-> entidad usando **MapStruct**.
- Reducción de boilerplate con **Lombok**.
- Documentación de endpoints con **springdoc OpenAPI / Swagger**.

### Paquete base
`uq.com.jdq.coresuite`

### Módulos funcionales principales
- `catalogo`: datos maestros.
- `seguridad`: usuarios, roles, autenticación, códigos.
- `sistema`: empresas, licencias, planes, sesiones.
- `operacion`: módulos funcionales, interfaces, campos, dependencias, validaciones y listas de valores.
- `infra`: eventos y soporte transversal.
- `config`: seguridad, respuestas, excepciones y clases comunes.
- `notificacion`: correos y avisos del sistema.

---

## Flujo arquitectónico transversal que se debe respetar
Cada cambio funcional debe seguir el mismo recorrido que ya usa el repositorio:

1. **Controller**
   - Expone el endpoint HTTP.
   - Usa `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`.
   - Retorna `ResponseEntity<RespuestaDTO<T>>`.
   - No debe contener lógica de negocio compleja.

2. **DTOs**
   - Se usan para entrada y salida.
   - El proyecto suele distinguir entre DTOs de creación, actualización, inactivación y respuesta.
   - Evitar exponer directamente entidades JPA desde los endpoints, salvo casos existentes muy puntuales que ya estén en producción.

3. **Service / ServiceImpl**
   - Aquí vive la lógica de negocio.
   - Se validan reglas, dependencias, existencia de registros y unicidad.
   - Normalmente aplica `@Transactional` para operaciones de escritura.

4. **Repository**
   - Extiende `JpaRepository`.
   - Se encarga de la consulta y persistencia.

5. **Mapper**
   - El repositorio utiliza `MapStruct` con `@Mapper(componentModel = "spring")`.
   - Debe mantenerse este patrón para conversiones entre entidades y DTOs.

6. **Excepciones y respuesta unificada**
   - Se utilizan excepciones como `NoExisteException` y `RegistroRepetidoException`.
   - Los errores deben integrarse con el manejo centralizado existente.

Copilot no debe saltarse capas ni mover lógica de negocio al controlador.

---

## Flujo de trabajo funcional existente

A continuación se describe el flujo real del sistema que Copilot debe conocer antes de modificar cualquier módulo.

### 1. Flujo de registro de empresa y aprovisionamiento inicial
Este es uno de los flujos más importantes del sistema y ya está automatizado.

#### Endpoint principal
- `POST /api/sistema/empresa`

#### Secuencia actual
Cuando se crea una empresa, no solo se guarda el registro de la empresa. El backend hace un aprovisionamiento inicial completo:

1. Valida referencias de catálogo:
   - tipo de identificación,
   - país,
   - departamento,
   - municipio.

2. Valida reglas de unicidad:
   - no repetir tipo + número de identificación,
   - no repetir correo electrónico.

3. Guarda la nueva empresa.

4. Crea automáticamente un rol base para la empresa:
   - rol `ADMIN`.

5. Crea automáticamente un usuario administrador inicial asociado a la empresa.

6. Asigna el rol creado a ese usuario mediante el módulo de roles por usuario.

7. Crea una licencia inicial para la empresa.

8. Envía una notificación por correo con el mensaje de bienvenida.

#### Regla crítica para Copilot
Si se modifica el flujo de creación de empresa, se debe preservar el encadenamiento completo de:
**empresa -> rol inicial -> usuario administrador -> asignación de rol -> licencia -> notificación**.

No romper este orden sin revisar impacto en onboarding, seguridad y acceso al sistema.

---

### 2. Flujo de autenticación, 2FA y cierre de sesión
La autenticación no termina con correo y contraseña; existe un flujo de doble factor y validaciones operativas.

#### Endpoints principales
- `POST /api/login`
- `POST /api/login/2FA`
- `PUT /api/login`

#### Flujo actual de login
1. El usuario envía correo electrónico y contraseña a `POST /api/login`.
2. El servicio busca al usuario y valida credenciales.
3. Si el correo no existe o la contraseña es incorrecta, se registra un evento de autenticación fallida.
4. Si el usuario está bloqueado o inactivo, el acceso se rechaza y también se registra el evento correspondiente.
5. Si el usuario pertenece a una empresa distinta del caso especial administrativo, se valida además:
   - que la empresa tenga licencia activa,
   - que el estado de la licencia permita el acceso,
   - que el número de sesiones activas no supere la capacidad contratada del plan.
6. Si todo es correcto, se genera y envía un código de doble factor usando el servicio de códigos.
7. El endpoint responde con el mensaje de confirmación del código enviado.

#### Flujo actual de confirmación 2FA
1. El usuario envía correo y código a `POST /api/login/2FA`.
2. El backend confirma el código.
3. Si el código es válido:
   - se obtiene el usuario,
   - se construyen los claims del token,
   - se determina el rol principal del usuario,
   - se genera el JWT,
   - se crea una sesión activa,
   - se registra un evento de autenticación exitosa.
4. Se retorna el token al cliente.

#### Flujo de logout
1. El cliente envía el identificador del usuario.
2. El servicio localiza la sesión activa y la marca como inactiva.
3. Se devuelve el mensaje de confirmación de cierre de sesión.

#### Regla crítica para Copilot
No simplificar este flujo a un login directo sin revisar el impacto en:
- códigos 2FA,
- licencias,
- control de sesiones,
- auditoría de eventos,
- claims del JWT.

---

### 3. Flujo de seguridad JWT
La configuración de seguridad ya define qué rutas son públicas y cuáles son protegidas.

#### Componentes relevantes
- `SecurityConfig`
- `JWTFilter`
- `JWTUtils`
- `AutenticacionEntryPoint`

#### Reglas observadas en el proyecto
- Swagger es público.
- `POST /api/login` y `POST /api/login/2FA` son públicos.
- `POST /api/sistema/empresa` es público para permitir el onboarding.
- las rutas de `catalogo` son públicas,
- varias rutas de recuperación o bloqueo de usuario también están expuestas de forma controlada,
- el resto de endpoints requieren autenticación.

#### Instrucción para Copilot
Si se agrega un endpoint nuevo, revisar si debe:
- ser público,
- requerir `bearerAuth`,
- aparecer documentado con anotaciones de OpenAPI,
- respetar la política stateless del sistema.

---

### 4. Flujo de usuarios
El módulo de usuarios gestiona creación, actualización, consulta, bloqueo, desbloqueo y cambio de contraseña.

#### Endpoints existentes del flujo
- crear usuario,
- actualizar usuario,
- inactivar usuario,
- obtener todos,
- obtener por id,
- obtener por empresa,
- recuperar contraseña,
- actualizar contraseña,
- bloquear usuario,
- desbloquear usuario.

#### Comportamiento actual relevante
- Se valida que la empresa exista.
- Se valida el tipo de identificación.
- Se evita duplicar correos electrónicos.
- La contraseña se codifica con `PasswordEncoder`.
- En la creación se dispara una notificación de bienvenida.
- Cuando el acceso falla por credenciales inválidas se registran eventos de autenticación.
- El bloqueo y desbloqueo forman parte del flujo normal del sistema y no deben tratarse como una eliminación lógica cualquiera.

#### Regla crítica para Copilot
Si cambia algo en usuarios, revisar siempre el impacto en:
- login,
- recuperación de contraseña,
- correo de bienvenida,
- eventos de autenticación,
- relación con empresa y tipo de identificación.

---

### 5. Flujo de roles y roles por usuario
El módulo de seguridad no se limita a los usuarios; existe asignación explícita de roles.

#### Flujo esperado
- Se crean roles por empresa.
- Luego se asocian roles a usuarios mediante el módulo de relación rol-usuario.
- Durante el login exitoso, el sistema evalúa los roles del usuario para determinar el rol principal que viajará en el token.

#### Jerarquía observada en el login
- si el usuario tiene `SUPER-ADMIN`, ese valor tiene prioridad,
- si no tiene `SUPER-ADMIN` pero sí `ADMIN`, se usa el rol de administración de empresa,
- en otros casos, el sistema considera un rol operativo.

#### Regla crítica para Copilot
Si modifica la lógica de roles, no hacerlo solo en el CRUD; revisar también la generación de claims y el comportamiento del login.

---

### 6. Flujo de licencias, planes y sesiones
Estos tres módulos están conectados y forman parte directa de la autorización operativa.

#### Planes
Los planes representan la oferta contratada y condicionan la cantidad de usuarios simultáneos permitidos.

#### Licencias
Las licencias se asignan a empresas y se consultan durante el login para validar si la empresa puede usar el sistema.

#### Sesiones
Las sesiones se crean cuando el login con 2FA es exitoso y se cierran al hacer logout.

#### Relación entre estos módulos
1. Una empresa posee una o más licencias.
2. La licencia apunta a un plan.
3. El plan define la capacidad operativa.
4. Al autenticar un usuario, el sistema cuenta sesiones activas de la empresa.
5. Si el número de sesiones activas excede la cantidad permitida por el plan, el acceso se bloquea.

#### Regla crítica para Copilot
No tratar licencias ni sesiones como módulos aislados. Cualquier cambio aquí puede romper el acceso de usuarios aunque la autenticación sea correcta.

---

### 7. Flujo del módulo de operación
Este módulo modela la configuración funcional de la aplicación y debe entenderse como una jerarquía.

#### Jerarquía funcional general
**empresa -> módulo -> interfaz -> grupo de campos -> campos -> validaciones / dependencias / listas de valores**

#### Submódulos identificados
- `modulo`
- `interfaz`
- `campo`
- `tipo_campo`
- `campo_validacion`
- `tipo_validacion`
- `campo_dependencia`
- `interface_grupo_campos`
- `lista_valores`
- `lista_valores_detalle`

#### Flujo de uso esperado
1. Se crea un módulo asociado a una empresa.
2. Dentro del módulo se crean una o varias interfaces.
3. Dentro de una interfaz se definen grupos y campos.
4. Cada campo puede depender de:
   - un tipo de campo,
   - una lista de valores,
   - una validación,
   - una dependencia respecto a otro campo.
5. Las listas de valores tienen un catálogo maestro y un detalle asociado.
6. Las validaciones y dependencias permiten construir formularios dinámicos y reglas operativas.

#### Patrón de endpoints ya presente
- obtener módulos por empresa,
- obtener interfaces por módulo,
- obtener campos por interfaz,
- y en los demás submódulos mantener el mismo enfoque relacional.

#### Regla crítica para Copilot
Cuando se modifique cualquier elemento del módulo de operación, preservar siempre la relación padre-hijo. No convertir un cambio local en un cambio aislado sin revisar impacto en formularios, renderización y dependencias funcionales.

---

### 8. Flujo de catálogos
El módulo `catalogo` reúne datos maestros usados por otros módulos del sistema.

#### Catálogos identificados
- país,
- departamento,
- municipio,
- género,
- tipo de identificación.

#### Características del flujo
- sirven como soporte a empresas y usuarios,
- son consultados desde otros módulos,
- varias rutas de catálogo están expuestas como públicas,
- deben mantenerse consistentes porque son referencias para relaciones foráneas.

#### Regla crítica para Copilot
Antes de cambiar la estructura o comportamiento de un catálogo, revisar qué entidades lo consumen desde `empresa`, `usuario` y otros módulos relacionados.

---

### 9. Flujo de notificaciones y eventos
El repositorio también implementa procesos transversales de auditoría y comunicación.

#### Notificaciones
Se envían correos, al menos, en estos casos ya observados:
- bienvenida al registrar empresa,
- bienvenida al crear usuario,
- notificación al bloquear usuario,
- envío de códigos de verificación o 2FA.

#### Eventos de autenticación
El sistema registra eventos cuando ocurren situaciones como:
- usuario inexistente,
- contraseña incorrecta,
- usuario bloqueado,
- usuario inactivo,
- problemas de licencia o aforo,
- autenticación exitosa.

#### Regla crítica para Copilot
Cuando modifique procesos de acceso, no eliminar el registro de eventos ni las notificaciones asociadas sin validación explícita.

---

## Cómo debe trabajar Copilot al modificar una funcionalidad
Antes de cambiar cualquier feature, seguir este orden:

1. Identificar el módulo funcional afectado.
2. Revisar su controlador, servicio, repositorio, DTOs y mapper.
3. Confirmar si el endpoint está protegido por JWT o es público.
4. Revisar si el cambio afecta:
   - catálogos,
   - empresa,
   - usuarios,
   - roles,
   - licencias,
   - sesiones,
   - eventos,
   - correos,
   - formularios dinámicos del módulo de operación.
5. Si cambia persistencia, agregar migración Flyway nueva.
6. Mantener la respuesta unificada con `RespuestaDTO`.
7. Documentar o mantener `@Operation` y `@SecurityRequirement` cuando corresponda.
8. Verificar que el cambio no rompa el flujo end-to-end existente.

---

## Reglas de estilo y convenciones obligatorias
- Mantener nombres claros y consistentes con el código actual.
- No usar imports wildcard.
- Preferir inyección por constructor con `final` y `@RequiredArgsConstructor`.
- Mantener comentarios y Javadocs en español.
- No mezclar lógica de infraestructura con lógica de negocio.
- No introducir frameworks, dependencias o patrones nuevos si no son estrictamente necesarios.
- Hacer cambios pequeños, precisos y fáciles de revisar.

---

## Persistencia y migraciones
- Las migraciones viven en `src/main/resources/db/migration`.
- El nombre debe seguir el formato `V<n>__descripcion.sql`.
- El proyecto trabaja con varios esquemas definidos en Flyway, por lo que todo cambio de base debe respetar la segmentación actual.
- Si se modifica una entidad persistente, revisar en conjunto:
  - entidad,
  - DTOs,
  - mapper,
  - repository,
  - service,
  - migración SQL.

---

## Qué no debe hacer Copilot
- No romper el flujo de 2FA para simplificar el login.
- No eliminar validaciones de licencia o sesiones activas.
- No mover reglas del servicio al controlador.
- No exponer entidades JPA sin revisar si ya existe DTO equivalente.
- No cambiar rutas REST existentes sin considerar el impacto en el frontend.
- No alterar la semántica de estados como `A`, `I` o `B` sin revisar todos los módulos que los consumen.
- No documentar algo como terminado sin verificar el comportamiento relevante.

---

## Checklist mínimo antes de dar una tarea por finalizada
Antes de cerrar cualquier cambio, Copilot debe comprobar:

- que el flujo funcional sigue intacto,
- que las relaciones entre módulos continúan correctas,
- que las respuestas siguen el patrón del proyecto,
- que la seguridad no quedó debilitada,
- que las migraciones y DTOs están alineados con la lógica nueva,
- que no se modificó lógica accidentalmente si la tarea era solo documental,
- que existe evidencia real de compilación, validación o prueba cuando se afirme que algo funciona.

---

## Prioridad general de Copilot en este repositorio
Copilot debe priorizar siempre:
1. respetar el flujo existente,
2. preservar la seguridad,
3. mantener la consistencia entre módulos,
4. hacer cambios conservadores,
5. documentar en español,
6. evitar soluciones invasivas cuando una extensión pequeña del código actual sea suficiente.


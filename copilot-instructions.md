# Copilot Instructions for JDQCoreSuite

## 🧭 Purpose
This file provides instructions for GitHub Copilot (and related AI assistants) to follow when writing, refactoring, or reviewing code in the `JDQCoreSuite` project.

## ✅ Code Style & Conventions
- This is a **Java / Spring Boot** codebase using **Gradle (Kotlin DSL)**.
- Prefer **standard Java naming conventions** (camelCase for variables/methods, PascalCase for classes, UPPER_SNAKE_CASE for constants).
- Keep methods short (ideally < 40 lines) and single-purpose.
- Use **Java 17+** language features when they improve clarity (records, `var`, `switch` expressions, `Optional`, etc.), but avoid overusing them in a way that reduces readability.
- Favor **constructor injection** for Spring beans (@Autowired only when necessary for legacy / edge cases).
- Keep imports clean and avoid wildcard imports (`import java.util.*`).

## 🧱 Architecture Notes
- The project contains typical layers: **controller**, **service**, **repository**, and **domain/entity**.
- Database migrations are managed via **Flyway** (SQL files under `src/main/resources/db/migration`).
- Look for `application.properties` and `application.yml` for configuration patterns.

## 🧪 Tests & Quality
- Prefer writing **unit tests** with JUnit 5 and Mockito. Existing test sources are in `src/test/java`.
- Keep test cases focused, readable, and independent.

## 🔍 When Suggesting Code
- Always reference existing conventions in the codebase (e.g., naming patterns, exception handling, logging).
- When adding new dependencies, ensure they make sense for this repository and are minimal.

## 💬 Communication Style
- Provide concise, actionable suggestions.
- When modifying or generating code, include a brief rationale (e.g., why a change improves maintainability).

---

*Note: This file is intended for Copilot/AI guidance and doesn't affect builds or runtime behavior.*

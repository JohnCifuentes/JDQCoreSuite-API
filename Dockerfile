# =====================================================
# Stage 1: Builder
# =====================================================
FROM gradle:8.5-jdk23-alpine AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN gradle dependencies --no-daemon || true

COPY src src

RUN gradle bootJar --no-daemon -x test

# =====================================================
# Stage 2: Runtime
# =====================================================
FROM eclipse-temurin:23-jre-alpine AS runtime

RUN addgroup -S appgroup && adduser -S appuser -G appgroup && \
    apk add --no-cache wget

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

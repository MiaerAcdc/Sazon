# Usa una imagen base de Java con Maven preinstalado
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Copia el código fuente y el pom.xml
COPY . /app
WORKDIR /app

# Construye la aplicación (genera el archivo JAR)
RUN mvn clean package -DskipTests

# --- SEGUNDA FASE (FASE DE EJECUCIÓN) ---
# Usa una imagen base más ligera (solo Java) para ahorrar espacio
FROM eclipse-temurin:17-jre-focal

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado desde la fase 'build'
COPY --from=build /app/target/*.jar app.jar

# Expón el puerto que usa tu Spring Boot (por defecto es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

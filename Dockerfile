# --- FASE 1: CONSTRUCCIÓN (BUILD) ---
# Usa una imagen base de Java 17 con Maven preinstalado
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Copia el contenido de la carpeta 'sazon' a /app (ya que el Dockerfile está dentro de sazon)
COPY . /app 

# CORRECCIÓN: El pom.xml está en la raíz de /app, por lo que el WORKDIR es /app
WORKDIR /app 

# Construye la aplicación (genera el archivo JAR)
# El JAR se llama 'sazon.jar' (según finalName en pom.xml) y está en /app/target/
RUN mvn clean package -DskipTests

# --- FASE 2: EJECUCIÓN (RUNTIME) ---
# Usa una imagen base ligera de solo JRE (Java Runtime Environment)
FROM eclipse-temurin:17-jre-focal

# Establece el directorio de trabajo
WORKDIR /app

# CORRECCIÓN: Copia el JAR generado ('sazon.jar') y lo renombra a 'app.jar'
COPY --from=build /app/target/sazon.jar app.jar

# Expón el puerto que usa tu Spring Boot (por defecto es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
# Inicia el archivo 'app.jar'
ENTRYPOINT ["java", "-jar", "app.jar"]

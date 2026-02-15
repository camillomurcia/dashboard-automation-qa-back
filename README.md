# Dashboard Automation

## 📋 Descripción

Este es un proyecto de desarrollo de una aplicación dashboard desarrollada con Spring Boot. El proyecto está configurado para proporcionar un entorno robusto de desarrollo con documentación automática de API y análisis de código.

## 🚀 Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación principal
- **Spring Boot 4.0.2**: Framework para desarrollo de aplicaciones Java
- **Gradle**: Sistema de construcción y gestión de dependencias
- **H2 Database**: Base de datos en memoria para desarrollo
- **JPA (Java Persistence API)**: Gestión de persistencia de datos
- **Lombok**: Reducción de código boilerplate
- **SpringDoc OpenAPI**: Documentación automática de API (Swagger UI)
- **SonarQube**: Análisis de calidad de código integrado con SonarCloud

## 📦 Dependencias Principales

- Spring Boot Starter Web MVC
- Spring Boot Starter Data JPA
- H2 Console
- SpringDoc OpenAPI UI (v2.8.4)

## 🛠️ Configuración del Proyecto

El proyecto utiliza:
- **Grupo**: `com.dashboard`
- **Versión**: `0.0.1-SNAPSHOT`
- **Java Version**: 21

## 🏗️ Estructura del Proyecto

```
dashboard-automation/
├── src/
│   ├── main/          # Código fuente principal
│   └── test/          # Pruebas
├── gradle/            # Configuración de Gradle
├── build.gradle       # Archivo de configuración de construcción
├── settings.gradle    # Configuración del proyecto Gradle
├── gradlew           # Gradle Wrapper (Unix)
└── gradlew.bat       # Gradle Wrapper (Windows)
```

## 🚦 Cómo Ejecutar

### Prerrequisitos
- Java 21 instalado
- Gradle (o usar el wrapper incluido)

### Compilar el proyecto
```bash
./gradlew build
```

### Ejecutar la aplicación
```bash
./gradlew bootRun
```

### Análisis de SonarQube
```bash
./gradlew sonar
```

## 📝 Documentación API (Swagger)

Una vez ejecutada la aplicación, puedes acceder a la documentación interactiva de la API en:

- **Swagger UI**: <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a>
- **OpenAPI JSON**: <a href="http://localhost:8080/v3/api-docs">http://localhost:8080/v3/api-docs</a>

La interfaz de Swagger UI te permite:
- 📖 Explorar todos los endpoints disponibles
- 🧪 Probar las APIs directamente desde el navegador
- 📋 Ver los esquemas de request/response
- 🔍 Consultar la documentación de cada endpoint

## 📊 Análisis de Código

El proyecto está configurado para integrarse con SonarCloud (`https://sonarcloud.io`) para análisis continuo de calidad de código.

## 📄 Licencia

Este es un proyecto demo para Spring Boot.

---

**Nota**: Este es un proyecto de ejercicio de desarrollo para una aplicación dashboard.

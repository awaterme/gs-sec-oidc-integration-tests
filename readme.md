# gs-sec-oidc-integration-tests

This project can serve as a foundation for creating GeoServer OIDC integration tests, using Spring Authorization Server (based on Spring Boot) as the OIDC provider.

Currently, the project requires Java 17 or higher, whereas GeoServer primarily relies on Java 11.

As a result, the project is currently limited to running Spring Authorization Server as an OIDC provider for development purposes only.

# Installation

Installation Java 17, min required for spring-boot 3:

    sdk install java 17.0.12-tem

Use Java 17 in current terminal:

    sdk use java 17.0.12-tem

# Running

Run the application:

    mvn spring-boot:run

# Endpoints

Discovery endpoint:

    http://127.0.0.1:9000/.well-known/openid-configuration

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

# GeoServer OpenID Connect configuration

```
<oauth2LoginAuthentication>
  <id>-6f4a2b55:1944238d8b7:-7ffe</id>
  <name>oidc</name>
  <className>org.geoserver.security.oauth2.login.GeoServerOAuth2LoginAuthenticationFilter</className>
  <roleSource class="org.geoserver.security.config.PreAuthenticatedUserNameFilterConfig$PreAuthenticatedUserNameRoleSource">UserGroupService</roleSource>
  <userGroupServiceName>default</userGroupServiceName>
  <baseRedirectUri>http://localhost:8080/geoserver/</baseRedirectUri>
  <googleEnabled>false</googleEnabled>
  <googleUserNameAttribute>email</googleUserNameAttribute>
  <googleRedirectUri>http://localhost:8080/geoserver/login/oauth2/code/google</googleRedirectUri>
  <gitHubEnabled>false</gitHubEnabled>
  <gitHubUserNameAttribute>id</gitHubUserNameAttribute>
  <gitHubRedirectUri>http://localhost:8080/geoserver/login/oauth2/code/gitHub</gitHubRedirectUri>
  <msEnabled>false</msEnabled>
  <msUserNameAttribute>sub</msUserNameAttribute>
  <msRedirectUri>http://localhost:8080/geoserver/login/oauth2/code/microsoft</msRedirectUri>
  <msScopes>openid profile email</msScopes>
  <oidcEnabled>true</oidcEnabled>
  <oidcClientId>geoserver</oidcClientId>
  <oidcClientSecret>secret</oidcClientSecret>
  <oidcUserNameAttribute>sub</oidcUserNameAttribute>
  <oidcRedirectUri>http://localhost:8080/geoserver/login/oauth2/code/oidc</oidcRedirectUri>
  <oidcScopes>openid</oidcScopes>
  <oidcDiscoveryUri>http://localhost:9000/.well-known/openid-configuration</oidcDiscoveryUri>
  <oidcTokenUri>http://localhost:9000/oauth2/token</oidcTokenUri>
  <oidcAuthorizationUri>http://localhost:9000/oauth2/authorize</oidcAuthorizationUri>
  <oidcUserInfoUri>http://localhost:9000/userinfo</oidcUserInfoUri>
  <oidcJwkSetUri>http://localhost:9000/oauth2/jwks</oidcJwkSetUri>
  <oidcLogoutUri>http://localhost:9000/connect/logout</oidcLogoutUri>
  <oidcForceAuthorizationUriHttps>false</oidcForceAuthorizationUriHttps>
  <oidcForceTokenUriHttps>false</oidcForceTokenUriHttps>
  <oidcEnforceTokenValidation>true</oidcEnforceTokenValidation>
  <oidcUsePKCE>false</oidcUsePKCE>
  <oidcAuthenticationMethodPostSecret>false</oidcAuthenticationMethodPostSecret>
  <oidcAllowUnSecureLogging>false</oidcAllowUnSecureLogging>
  <postLogoutRedirectUri>http://localhost:8080/geoserver/web/</postLogoutRedirectUri>
  <enableRedirectAuthenticationEntryPoint>false</enableRedirectAuthenticationEntryPoint>
</oauth2LoginAuthentication>
```

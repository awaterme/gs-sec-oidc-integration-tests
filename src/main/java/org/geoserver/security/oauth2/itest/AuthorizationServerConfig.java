package org.geoserver.security.oauth2.itest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * Tweaks configuration to be more compatible.
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        logger.info("Creating JWK source");
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * Makes JWK Set endpoint to produce JWK having attribute:
     * <code>use: "sig"</code>
     * Otherwise spring oauth client used in geoserver will ignore JWK for
     * validation and validation fails.
     * 
     * @return A properly initialized key for signing
     */
    public static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey.Builder lBuilder = new RSAKey.Builder(publicKey).privateKey(privateKey)
                .keyID(UUID.randomUUID().toString());
        // keyUse(KeyUse.SIGNATURE) is critical to be compatible.
        // lBuilder.keyUse(KeyUse.SIGNATURE);

        return lBuilder.build();
    }

    static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

}

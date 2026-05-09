package br.com.fiap.techchallenge.infrastructure.conf;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String OAUTH2_SCHEME = "oauth2";

    @Value("${KEYCLOAK_URL:http://localhost:8081}")
    private String keycloakUrl;

    @Value("${KEYCLOAK_REALM:fiap}")
    private String keycloakRealm;

    @Value("${KEYCLOAK_CLIENT_ID:techchallenge}")
    private String keycloakClientId;

    @Bean
    public OpenAPI openAPI() {
        String tokenUrl = keycloakUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        return new OpenAPI()
                .info(new Info()
                        .title("TechChallenge API")
                        .description("API de gerenciamento de oficina mecânica. Use o botão **Authorize** e informe usuário e senha para autenticar.")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(OAUTH2_SCHEME, List.of("openid", "profile")))
                .components(new Components()
                        .addSecuritySchemes(OAUTH2_SCHEME, new SecurityScheme()
                                .name(OAUTH2_SCHEME)
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Informe usuário e senha. Client: **" + keycloakClientId + "** (público, sem secret).")
                                .flows(new OAuthFlows()
                                        .password(new OAuthFlow()
                                                .tokenUrl(tokenUrl)
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID Connect")
                                                        .addString("profile", "Perfil do usuário"))))));
    }
}
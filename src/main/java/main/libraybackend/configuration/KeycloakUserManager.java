package main.libraybackend.configuration;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
public class KeycloakUserManager {

    private static final String SERVER_URL = "http://localhost:8080/auth"; // Keycloak server URL
    private static final String REALM = "your-realm";
    private static final String CLIENT_ID = "user-management-client";
    private static final String CLIENT_SECRET = "your-client-secret";

    private Keycloak keycloak;

    public KeycloakUserManager() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .build();
    }

    public void createUser(String username, String email, String password) {
        UsersResource usersResource = keycloak.realm(REALM).users();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);

        // Create the user
        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user: " + response.getStatusInfo().getReasonPhrase());
        }

        // Get the user ID
        String userId = CreatedResponseUtil.getCreatedId(response);

        // Set password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        usersResource.get(userId).resetPassword(passwordCred);

        // Assign default roles if needed
        // usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }
}
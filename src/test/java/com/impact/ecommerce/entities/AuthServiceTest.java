package com.impact.ecommerce.services;

import com.impact.ecommerce.dtos.auth.LoginRequest;
import com.impact.ecommerce.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Test
    void unknown_email_cannot_log_in() {
        UserRepository users = mock(UserRepository.class);
        when(users.findByEmail("ana@impact.md"))
                .thenReturn(Optional.empty());

        AuthService service = new AuthService(users, null, null);

        var error = assertThrows(
                ResponseStatusException.class,
                () -> service.login(
                        new LoginRequest("ana@impact.md", "parola123")
                )
        );

        assertEquals(HttpStatus.UNAUTHORIZED, error.getStatusCode());
    }
}
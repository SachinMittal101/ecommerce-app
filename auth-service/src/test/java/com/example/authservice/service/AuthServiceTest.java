package com.example.authservice.service;

import com.example.authservice.entity.UserCredential;
import com.example.authservice.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(authService, "passwordEncoder", passwordEncoder);
    }

    @Test
    public void testSaveUser() {
        UserCredential userCredential = new UserCredential(1, "testUser", "test-email", "password");

        Mockito.when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        Mockito.when(repository.save(userCredential)).thenReturn(userCredential);

        String result = authService.saveUser(userCredential);

        verify(passwordEncoder).encode("password");
        verify(repository).save(userCredential);

        assertEquals("user added to the system", result);
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        Mockito.when(jwtService.generateToken(username)).thenReturn("jwtToken");

        String token = authService.generateToken(username);

        assertEquals("jwtToken", token);
    }

    @Test
    public void testValidateToken() {
        String token = "validToken";
        Mockito.doNothing().when(jwtService).validateToken(token);

        authService.validateToken(token);

        verify(jwtService).validateToken(token);
    }
}

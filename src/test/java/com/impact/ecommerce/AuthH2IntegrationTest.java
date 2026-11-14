package com.impact.ecommerce;

import com.impact.ecommerce.entities.User;
import com.impact.ecommerce.repositories.UserRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthH2IntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Test
    void registration_saves_a_hashed_password() throws Exception {
        String email = "student-" + UUID.randomUUID() + "@example.com";
        String body = """
                {
                    "email": "%s",
                    "password": "secret123"
                }
                """.formatted(email);
        mvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());
        User saved = userRepository.findByEmail(email)
                .orElseThrow();
        assertThat(saved.getPasswordHash())
                .isNotEqualTo("secret123")
                .startsWith("$2");
    }
    // user can register -> login -> view their accounts - HOMEWORK
    // 1. make a function void like the one above
    // SAME STRING BODY, SAME STRING EMAIL
    // USE given().port().contentType()   when()  -> then()
    //        .given()
    //            .port(port)
    //            .contentType("application/json")
    //            .body(credentials)
    //        .when()
    //            .post("/api/auth/register")
    //        .then()
    //            .statusCode(201);

    // 1. register with given()
    // 2. login and save the token given()
    // 3. send to a protected endpoint given()
}

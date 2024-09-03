package dance.brain.scbtspring.integration.controller;

import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.integration.IntegrationTestBase;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class UserControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Autowired
    public UserControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    void init() {
        List<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        User testUser = new User("test@gmail.com", "test", roles);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), roles);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(testingAuthenticationToken);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("pageResponse"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .param("username", "test@gmail.com")
                        .param("firstname", "test_firstname")
                        .param("lastname", "test_lastname")
                        .param("role", "ADMIN")
                        .param("companyId", "1")
                        .param("birthDate", "2000-01-01"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/api/v1/users/{\\d+}")
                );
    }
}

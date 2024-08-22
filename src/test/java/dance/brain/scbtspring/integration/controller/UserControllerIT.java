package dance.brain.scbtspring.integration.controller;

import dance.brain.scbtspring.integration.IntegrationTestBase;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(5)));
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

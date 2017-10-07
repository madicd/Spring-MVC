package spitter.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import spitter.Spitter;
import spitter.data.SpitterRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {
    @Test
    public void shouldShowRegistrationForm() throws Exception {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldRegisterUser() throws Exception {

        SpitterRepository mockRepository = mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/spitter/register")
                .param("firstName", "james")
                .param("lastName", "bond")
                .param("username", "jbond")
                .param("password", "jb007"))
                .andExpect(redirectedUrl("/spitter/jbond"));

        Spitter unsaved = new Spitter(23L, "james", "bond", "jbond", "jb007");
        verify(mockRepository, atLeastOnce()).save(unsaved);
    }
}
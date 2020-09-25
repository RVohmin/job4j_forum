package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private PostRepository posts;

    @Test
    @WithMockUser
    public void shouldReturnPostView() throws Exception {
        this.mockMvc.perform(get("/posts/post?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostEdit() throws Exception {
        this.mockMvc.perform(get("/posts/edit?id=79"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

//    @Test
//    @WithMockUser
//    public void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(post("/posts/save")
//                .param("name","Куплю ладу-грант. Дорого."))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection());
//        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
//        verify(posts).save(argument.capture());
//        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
//    }
}
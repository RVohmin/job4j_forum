package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.TopicRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class TopicControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicRepository topicRepository;

    @Test
    @WithMockUser
    public void shouldReturnTopicCreateView() throws Exception {
        this.mockMvc.perform(get("/topic?username=root"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnTopicEditView() throws Exception {
        Mockito.when(topicRepository.findById(1L)).thenReturn(Optional.of(new Topic("theme")));
        this.mockMvc.perform(get("/topic/edit/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic/edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(post("/topic/addTopic")
                .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Topic> argument = ArgumentCaptor.forClass(Topic.class);
        verify(topicRepository).save(argument.capture());
        assertEquals("Куплю ладу-грант. Дорого.", argument.getValue().getName());
    }
}
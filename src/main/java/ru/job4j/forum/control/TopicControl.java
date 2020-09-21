package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.MemStore;

@Controller
@RequestMapping("/topic")
public class TopicControl {
    private final MemStore memStore;

    public TopicControl(MemStore memStore) {
        this.memStore = memStore;
    }

    @GetMapping
    public String redirect() {
        return ("topic/create");
    }

    @PostMapping("/addTopic")
    public String getCreateTopic(@ModelAttribute Topic topic) {
        memStore.addTopic(topic);
        return "redirect:/";
    }
}

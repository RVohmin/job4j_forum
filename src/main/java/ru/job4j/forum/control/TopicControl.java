package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.TopicRepository;
import ru.job4j.forum.repository.UserRepository;

@Controller
@RequestMapping("/topic")
public class TopicControl {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public TopicControl(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String redirect(Model model) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        model.addAttribute("username", username);
        return ("topic/create");
    }

    @PostMapping("/addTopic")
    public String getCreateTopic(@ModelAttribute Topic topic) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        Topic tempTopic = new Topic(topic.getName());
        tempTopic.setName(topic.getName());
        tempTopic.setCreator(userRepository.findByUsername(username));
        topicRepository.save(tempTopic);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute Topic topic) {
        Topic tempTopic = topicRepository.findById(id).get();
        tempTopic.setName(topic.getName());
        topicRepository.save(tempTopic);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String getViewUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("topic", topicRepository.findById(id).get());
        model.addAttribute("username", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        return "topic/edit";
    }
}

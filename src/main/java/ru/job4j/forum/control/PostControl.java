package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.MemStore;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/post")
public class PostControl {
    private final MemStore memStore;

    public PostControl(MemStore memStore) {
        this.memStore = memStore;
    }

    @GetMapping
    public String showPosts(@RequestParam("id") int topicId, Model model) {
        model.addAttribute("theme", memStore.findTopicById(topicId).getName());
        model.addAttribute("topicId", topicId);
        model.addAttribute("posts", memStore.getAllPosts(topicId));
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ("post");
    }

    @PostMapping("/create")
    public String addPost(@ModelAttribute Post post, HttpServletRequest req, Model model) {
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        memStore.addPost(topicId , post);
        model.addAttribute("topicId", topicId);
        model.addAttribute("posts", memStore.getAllPosts(topicId));
        return "/post";
    }
}

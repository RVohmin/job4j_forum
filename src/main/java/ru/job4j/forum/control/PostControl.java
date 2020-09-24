package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.TopicRepository;
import ru.job4j.forum.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts")
public class PostControl {
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostControl(TopicRepository topicRepository, PostRepository postRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/post")
    public String showPosts(@RequestParam("id") Long topicId, Model model) {
        Topic topic = topicRepository.findById(topicId).get();
        model.addAttribute("topic", topic);
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ("post");
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        model.addAttribute("post", postRepository.findById(id).get());
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return "post/edit";
    }

    @PostMapping("/save")
    public String addPost(@ModelAttribute Post post, HttpServletRequest req, Model model) {
        var topicId = Long.parseLong(req.getParameter("topicId"));
        Topic topic = topicRepository.findById(topicId).get();
        User user = userRepository.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        Post tempPost = new Post(post.getName(), post.getDescription());
        tempPost.setCreator(user);
        tempPost.setTopic(topic);
        tempPost.setId(post.getId());
        postRepository.save(tempPost);
        model.addAttribute("topic", topic);
        model.addAttribute("posts", topic.getPosts());
        return "post";
    }

    @PostMapping("/delete")
    public String delete(HttpServletRequest req) {
        Post post = postRepository.findById(Long.valueOf(req.getParameter("id"))).get();
        postRepository.delete(post);
        return "redirect:/";
    }
}

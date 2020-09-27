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
import ru.job4j.forum.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostControl {
    private final PostService postService;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostControl(PostService postService, TopicRepository topicRepository, PostRepository postRepository, UserRepository userRepository) {
        this.postService = postService;
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/post/{id}")
    public String showPosts(@PathVariable("id") Long id, Model model) {
        Topic topic = topicRepository.findById(id).get();
        model.addAttribute("topic", topic);
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ("post");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return "post/edit";
    }

    @PostMapping("/create/{topicId}")
    public String addPost(@ModelAttribute Post post,
                          @PathVariable("topicId") Long topicId,
                          Model model) {
        Topic topic = topicRepository.findById(topicId).get();
        User user = userRepository.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        Post tempPost = new Post(post.getName(), post.getDescription());
        tempPost.setCreator(user);
        tempPost.setTopic(topic);
        tempPost.setId(post.getId());
        postService.create(tempPost);
        model.addAttribute("topic", topic);
        model.addAttribute("posts", tempPost.getTopic().getPosts());
        return "redirect:/posts/post/" + topic.getId();
    }

    @PostMapping("/update/{postId}")
    public String update(@PathVariable("postId") Long postId, @ModelAttribute Post post) {
        Post tempPost = postService.findById(postId);
        tempPost.setName(post.getName());
        tempPost.setDescription(post.getDescription());
        postService.create(tempPost);
        return "redirect:/posts/post/" + tempPost.getTopic().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Post post = postService.findById(id);
        postService.delete(post);
        return "redirect:/posts/post/" + post.getTopic().getId();
    }
}

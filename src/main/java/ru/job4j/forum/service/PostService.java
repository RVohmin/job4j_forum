package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.TopicRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository posts;
    private final TopicRepository topics;
    private final UserRepository users;

    public PostService(PostRepository posts, TopicRepository topics, UserRepository users) {
        this.posts = posts;
        this.topics = topics;
        this.users = users;
    }

    public List<Topic> getAllTopics() {
        return (List<Topic>) topics.findAll();
    }

    public List<Post> getAllPosts(long id) {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public User findByUsername(String username) {
        return users.findByUsername(username);
    }
}

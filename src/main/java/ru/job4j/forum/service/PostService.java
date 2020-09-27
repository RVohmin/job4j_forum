package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> getAllPosts(long id) {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post findById(long id) {
        return posts.findById(id).get();
    }

    public void create(Post post) {
        posts.save(post);
    }

    public void delete(Post post) {
        posts.delete(post);
    }

    public void update(Post updPost) {
        Post post = findById(updPost.getId());
        post.setName(updPost.getName());
        post.setCreated(updPost.getCreated());
        posts.save(post);
    }
}

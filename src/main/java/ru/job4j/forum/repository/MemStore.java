package ru.job4j.forum.repository;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MemStore {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final Map<Integer, Topic> topics = new HashMap<>();
    private final List<User> users = new ArrayList<>();
    AtomicInteger postsID = new AtomicInteger(0);
    AtomicInteger topicsID = new AtomicInteger(0);

    public List<Post> getAllPosts(int topicId) {
        return topics.get(topicId).getPosts();
    }

    public List<Topic> getAllTopics() {
        return new ArrayList<>(topics.values());
    }

    public Topic findTopicById(int id) {
        return topics.get(id);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public void addTopic(Topic topic) {
        if (topic.getId() == 0) {
            topic.setId(topicsID.incrementAndGet());
        }
        topics.put(topic.getId(), topic);
    }

    public void addPost(int id, Post post) {
        if (post.getId() == 0) {
            post.setId(postsID.incrementAndGet());
        }
        topics.get(id).getPosts().add(post);
    }
}

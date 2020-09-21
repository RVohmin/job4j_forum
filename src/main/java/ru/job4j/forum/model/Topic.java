package ru.job4j.forum.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Topic {
    private int id;
    private String name;
    private Calendar created;
    private User creator;
    private final List<Post> posts;

    public Topic(String name) {
        this.name = name;
        created = Calendar.getInstance();
        posts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPost(Post post) {
        posts.add(post);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", creator=" + creator +
                ", posts=" + posts +
                '}';
    }
}

package com.techdev.sdg;

import com.techdev.sdg.Post.Post;
import com.techdev.sdg.Post.PostRepository;
import com.techdev.sdg.Tag.Tag;
import com.techdev.sdg.Tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SdgApplication implements CommandLineRunner {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;
    public static void main(String[] args) {
        SpringApplication.run(SdgApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Post post = new Post("Hibernate Many to Many Example with Spring Boot",
                "Learn how to map a many to many relationship using hibernate",
                "Entire Post content with Sample code");

        // Create two tags
        Tag tag1 = new Tag("Spring Boot");
        Tag tag2 = new Tag("Hibernate");

        post.getTags().add(tag1);
        post.getTags().add(tag2);

        tag1.getPosts().add(post);
        tag2.getPosts().add(post);

        postRepository.save(post);
    }
}

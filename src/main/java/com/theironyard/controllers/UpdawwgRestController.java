package com.theironyard.controllers;

import com.theironyard.entities.Dog;
import com.theironyard.entities.Post;
import com.theironyard.entities.User;
import com.theironyard.services.DogRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * Created by will on 7/7/16.
 */
@RestController
public class UpdawwgRestController {
    // link tables
    @Autowired
    DogRepository dogs;

    @Autowired
    UserRepository users;

    @Autowired
    PostRepository posts;

    // start h2 web server
    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    // get/post routes for users
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return users.findAll();
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public void user(String name, String password) {
        User user = new User(name, password);
        users.save(user);
    }

    // routes for dogs
    @RequestMapping(path = "/dogs", method = RequestMethod.GET)
    public Iterable<Dog> getDogs() {
        return dogs.findAll();
    }

    @RequestMapping(path = "/dogs", method = RequestMethod.POST)
    public void dog(String name, String image, String breed, int age, String description) {
        Dog dog = new Dog(name, image, breed, age, description);
        dogs.save(dog);
    }

    // routes for posts
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public Iterable<Post> getPosts() {
        return posts.findAll();
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void post(int replyId, String message, int dogId, int userId) {
        Dog dog = dogs.findOne(dogId);
        User user = users.findOne(userId);
        Post post = new Post(replyId, message, user, dog);
        posts.save(post);

    }





}

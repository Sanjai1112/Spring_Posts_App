package com.sanjai.posts.controller;

import com.sanjai.posts.entity.Posts;
import com.sanjai.posts.repositories.CommentsRepository;
import com.sanjai.posts.repositories.PostsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostsController {
    @Autowired
    PostsRepositories postsRepositories;
    @Autowired
    CommentsRepository commentsRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Posts> getAllPosts(){
        return postsRepositories.findAll();
    }

    @RequestMapping(value = "/posts/{id}",method = RequestMethod.GET)
    public Map<String,Posts> getPostById(@PathVariable(value = "id") Long id){
        Optional<Posts> postsOptional = postsRepositories.findById(id);
        if(postsOptional.isPresent()){
            return new HashMap<String,Posts>(){
                {
                    putIfAbsent("success",postsOptional.get());
                }
            };
        }else{
            return new HashMap<String,Posts>(){
                {
                    put("error",null);
                }
            };
        }
    }
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String addPost(@RequestBody Posts post){
        if(post.getId() == null) {
            post.setId(0l);
        }
        Posts newPost = postsRepositories.save(post);
        System.out.println(newPost);
        return "post added successfully";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.PATCH)
    public String updatePost(@RequestBody Posts post) {
        if(postsRepositories.existsById(post.getId())){
            Posts newPost = postsRepositories.save(post);
            System.out.println(newPost);
            return "Post updated successfully";
        }else{
            return "Requested post is not available";
        }
    }

    @RequestMapping(value = "/posts", method = RequestMethod.DELETE)
    public String deletePost(@RequestBody Posts post){
        if(postsRepositories.existsById(post.getId())){
            postsRepositories.delete(post);
            commentsRepository.deleteAllCommentsByPostId(post.getId());
            return "Post deleted successfully";
        }else {
            return "Requested post is not available";
        }
    }
}

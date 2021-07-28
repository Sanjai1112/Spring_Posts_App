package com.sanjai.posts.controller;

import com.sanjai.posts.entity.Comments;
import com.sanjai.posts.repositories.CommentsRepository;
import com.sanjai.posts.repositories.PostsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    PostsRepositories postsRepositories;

    @RequestMapping(value = "/posts/comments/count")
    public Long getAllCommentsCount(){
        return commentsRepository.getCounts();
    }

    @RequestMapping(value = "/posts/comments")
    public List<Comments> getAllComments(){
        return commentsRepository.findAll();
    }

    @RequestMapping(value = "/posts/comments/{postId}", method = RequestMethod.GET)
    public List<Comments> getAllCommentByPostId(@PathVariable(value = "postId") Long postId){
        List<Comments> comments = commentsRepository.getAllCommentByPostId(postId);
        return comments;
    }

    @RequestMapping(value = "/posts/comments",method = RequestMethod.POST)
    public String addCommentToPost(@RequestBody Comments comment){
        if(comment.getPostId() == null){
            return "post id is not presented in the request";
        }

        if(postsRepositories.findById(comment.getPostId()).isPresent()){
            if(comment.getId() == null){
                comment.setId(0l);
            }
            commentsRepository.save(comment);
            return "comment added successfully";
        }else{
            return "post is not available to comment";
        }
    }

    @RequestMapping(value = "/posts/comments",method = RequestMethod.PATCH)
    public String updateCommentByPostId(@RequestBody Comments comment){
        if(comment.getId() == null){
            return "Comment id not presented in the request";
        }

        if(comment.getPostId() == null){
            return "Post id is not presented in the request";
        }

        Optional<Comments> commentsOptional = commentsRepository.findById(comment.getId());
        if(commentsOptional.isPresent()){
            if(commentsOptional.get().getPostId() == comment.getPostId()){
                commentsRepository.save(comment);
                return "comment updated successfully";
            }else{
                return "comment is not associated with the given post";
            }
        }else {
            return "comment not found";
        }
    }
    @RequestMapping(value = "/posts/comments/{postId}/{commentId}",method = RequestMethod.DELETE)
    public String deleteCommentByPostId(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId){
        Optional<Comments> commentsOptional = commentsRepository.findById(commentId);
        if(commentsOptional.isPresent()){
            if(commentsOptional.get().getPostId() == postId) {
                commentsRepository.deleteById(commentId);
                return "comment deleted successfully";
            }else{
                return "comment is not associated with the given post";
            }
        }else{
            return "comment not found to delete";
        }
    }
}

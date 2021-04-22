package com.covid.cohelp.Controller;

import com.covid.cohelp.Entity.Post;
import com.covid.cohelp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/location")
    public List<Post> getPostsInLocation(@RequestParam(name = "location") String location){
        return postService.getPostsInLocation(location);
    }
    @GetMapping("/posts/item")
    public List<Post> getPostsWithItem(@RequestParam(name = "item") String item) {
        return postService.getPostsWithItem(item);
    }
    @GetMapping("posts/search")
    public List<Post> getPostsWithItemAndLocation(@RequestParam(name = "item") String item, @RequestParam(name = "location") String location) {
        return postService.getPostWithItemAndLocation(item, location);
    }
    @PostMapping("/post/")
    public Post createPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/post/{id}")
    public Post updatePost(@RequestBody Post updatedPost, @PathVariable("id") Long id) {
        return postService.updatePost(updatedPost, id);
    }
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        if(postService.deletePost(id)) return "Deletion Successfull";
        else return "Deletion failed";
    }
}

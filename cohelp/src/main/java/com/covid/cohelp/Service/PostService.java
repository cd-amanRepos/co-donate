package com.covid.cohelp.Service;

import com.covid.cohelp.Entity.Item;
import com.covid.cohelp.Entity.Post;
import com.covid.cohelp.Repository.ItemRepository;
import com.covid.cohelp.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ItemRepository itemRepository;
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    public Post addPost(Post post) {

        Post post1 = postRepository.save(post);
        Optional<Item> item = itemRepository.findByName(post.getItem_name());
        //System.out.println(item.get().getItem_id());
        if(item.isPresent()) {
            post.setItem(item.get());
            item.get().addPosts(post1);
            return postRepository.save(post1);
        }
        else postRepository.delete(post1);
        return null;
        //Checks for mobile no and all
    }

    public Post updatePost(Post updatedPost, Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            post.get().setDescription(updatedPost.getDescription());
            post.get().setOwner_email(updatedPost.getOwner_email());
            post.get().setOwner_name(updatedPost.getOwner_name());
            post.get().setOwner_mob(updatedPost.getOwner_mob());
            post.get().setLocation(updatedPost.getLocation());
            post.get().setItem(updatedPost.getItem());
            return postRepository.save(post.get());
        }
        return null;
    }

    public boolean deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }

    public List<Post> getPostsInLocation(String location) {
        return postRepository.findAllByLocation(location);
    }

    public List<Post> getPostsWithItem(String item) {
        Optional<Item> item1 = itemRepository.findByName(item);
        if(item1.isPresent()) {
            return item1.get().getPosts();
        }
        else return null;
    }

    public List<Post> getPostWithItemAndLocation(String item, String location) {
        List<Post> posts = getPostsWithItem(item);
        List<Post> postsWithLocation = new ArrayList<>();
        if(posts != null) {
            for(Post eachPost : posts) {
                if(eachPost.getLocation().equals(location)) postsWithLocation.add(eachPost);
            }
        }
        return postsWithLocation;
    }
}

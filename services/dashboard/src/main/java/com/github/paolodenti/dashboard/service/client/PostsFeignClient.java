package com.github.paolodenti.dashboard.service.client;

import com.github.paolodenti.dashboard.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "posts", url = "https://jsonplaceholder.typicode.com/")
public interface PostsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts", consumes = "application/json")
    List<Post> getPosts();
}
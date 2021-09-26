package com.github.paolodenti.dashboard.web;

import com.github.paolodenti.dashboard.model.Post;
import com.github.paolodenti.dashboard.model.Product;
import com.github.paolodenti.dashboard.service.client.PostsFeignClient;
import com.github.paolodenti.dashboard.service.client.ProductsFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final ProductsFeignClient productsFeignClient;

    private final PostsFeignClient postsFeignClient;

    public DashboardController(ProductsFeignClient productsFeignClient, PostsFeignClient postsFeignClient) {
        this.productsFeignClient = productsFeignClient;
        this.postsFeignClient = postsFeignClient;
    }

    @GetMapping
    public Map<String, Object> test() {
        List<Product> products = productsFeignClient.getProducts();
        List<Post> posts = postsFeignClient.getPosts();
        return Map.ofEntries(Map.entry("products", products), Map.entry("posts", posts));
    }
}

package com.github.paolodenti.restapi.web;

import com.github.paolodenti.restapi.service.client.Service1FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    private final Service1FeignClient service1FeignClient;

    public CustomersController(Service1FeignClient service1FeignClient) {
        this.service1FeignClient = service1FeignClient;
    }

    @GetMapping
    public Map<Long, String> test() {
        return service1FeignClient.getCustomers();
    }
}

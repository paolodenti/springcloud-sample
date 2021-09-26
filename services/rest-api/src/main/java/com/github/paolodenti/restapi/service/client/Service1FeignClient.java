package com.github.paolodenti.restapi.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("service-1")
public interface Service1FeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "customer", consumes = "application/json")
    Map<Long, String> getCustomers();
}

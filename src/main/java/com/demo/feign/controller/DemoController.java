package com.demo.feign.controller;

import com.demo.feign.service.DemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DemonService demonService;

    @GetMapping("/get")
    public String getController() {
        return demonService.get();
    }
}

package com.demo.feign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemonService {

    public String get() {
        return "get";
    }

}

package com.demo.feign.service;

import com.demo.feign.common.dto.BaseRequestInfo;
import com.demo.feign.common.dto.BaseResponseInfo;
import com.demo.feign.feign.client.DemoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemonService {

    private final DemoFeignClient demoFeignClient;

    public String get() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet(
                "CustomHeader",
                "CustomName",
                1L
        );

        log.info("Name : {}", response.getBody().getName());
        log.info("Age : {}", response.getBody().getAge());
        log.info("Header : {}", response.getBody().getHeader());

        return "get";
    }

    public String post() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callPost(
                "CustomHeader",
                new BaseRequestInfo("CustomName",
                        2L)
        );

        log.info("Name : {}", response.getBody().getName());
        log.info("Age : {}", response.getBody().getAge());
        log.info("Header : {}", response.getBody().getHeader());

        return "get";
    }

    public String errorDecoder() {
        demoFeignClient.callErrorDecoder();
        return "error";
    }
}

package com.demo.feign.feign.client;

import com.demo.feign.common.dto.BaseResponseInfo;
import com.demo.feign.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "demo-client",
        url = "${feign.url.prefix}",
        configuration = DemoFeignConfig.class
)
public interface DemoFeignClient {

    @GetMapping("/get") // ->  http://localhost:8080/target_server/get
    ResponseEntity<BaseResponseInfo> callGet(
            @RequestHeader("CustomHeaderName") String customHeaderName,
            @RequestParam("name") String name,
            @RequestParam("age") Long age
    );
}

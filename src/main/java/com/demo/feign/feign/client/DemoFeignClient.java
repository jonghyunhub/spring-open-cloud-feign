package com.demo.feign.feign.client;

import com.demo.feign.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "demo-client",
        url = "${feign.url.prefix}",
        configuration = DemoFeignConfig.class
)
public interface DemoFeignClient {


}

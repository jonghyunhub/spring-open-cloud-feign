package com.demo.feign.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class DemoFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());

        if (httpStatus == HttpStatus.NOT_FOUND) {
            log.info("[DemoFeignErrorDecoder] Http Status = {}", httpStatus);
            throw new RuntimeException(String.format("[DemoFeignErrorDecoder] Http Status is %s", httpStatus));
        }

        return errorDecoder.decode(methodKey, response);
    }
}

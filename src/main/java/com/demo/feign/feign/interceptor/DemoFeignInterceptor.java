package com.demo.feign.feign.interceptor;

import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(staticName = "of")
@Slf4j
public class DemoFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        // get 요청일 경우
        if (template.method().equals(HttpMethod.GET.name())) {
            log.info("[GET] [DemoFeignInterceptor] queries : {}", template.queries());
            return;
        }

        // post 요청일 경우
        final String encodedRequestBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        log.info("[POST] [DemoFeignInterceptor] queries : {}", encodedRequestBody);


        // 추가적으로 본인이 필요한 로직을 추가
        final String convertRequestBody = encodedRequestBody;
        template.body(convertRequestBody);

    }
}

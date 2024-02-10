package com.demo.feign.feign.logger;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;


/**
 * 로거는 글로벌하게 모든 feign에서 사용하는 설정값이므로 feign 패키지 x / config 패키지의 feignConfig에 설정
 */
@RequiredArgsConstructor
@Slf4j
public class FeignCustomLogger extends Logger {

    private static final int DEFAULT_SLOW_API_TIME = 3_000;
    private static final String SLOW_API_NOTICE = "Slow api";

    /**
     * 로그를 남길때 어떤 포맷으로 남길지 정해주는 부분
     */
    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info("{} {}", methodTag(configKey), String.format(format, args));
    }

    /**
     * request 로그 핸들링
     */
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        log.info("[logRequest] : {}", request);
    }

    /**
     * request, response 로그, 그 외에 모든 로그를 핸들링 가능
     */
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        String protocolVersion = resolveProtocolVersion(response.protocolVersion());
        String reason = response.reason() != null && logLevel.compareTo(Logger.Level.NONE) > 0 ? " " + response.reason() : "";
        int status = response.status();
        this.log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime);
        if (logLevel.ordinal() >= Logger.Level.HEADERS.ordinal()) {
            Iterator var9 = response.headers().keySet().iterator();

            while (true) {
                String field;
                do {
                    if (!var9.hasNext()) {
                        int bodyLength = 0;
                        if (response.body() != null && status != 204 && status != 205) {
                            if (logLevel.ordinal() >= Logger.Level.FULL.ordinal()) {
                                this.log(configKey, "");
                            }

                            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                            bodyLength = bodyData.length;
                            if (logLevel.ordinal() >= Logger.Level.FULL.ordinal() && bodyLength > 0) {
                                this.log(configKey, "%s", Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
                            }

                            if (elapsedTime > DEFAULT_SLOW_API_TIME) {
                                this.log(configKey, "[%s] elapsedTime : %s",SLOW_API_NOTICE, elapsedTime);
                            }

                            this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                            return response.toBuilder().body(bodyData).build();
                        }

                        this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                        return response;
                    }

                    field = (String) var9.next();
                } while (!this.shouldLogResponseHeader(field));

                Iterator var11 = Util.valuesOrEmpty(response.headers(), field).iterator();

                while (var11.hasNext()) {
                    String value = (String) var11.next();
                    this.log(configKey, "%s: %s", field, value);
                }
            }
        } else {
            return response;
        }
    }
}

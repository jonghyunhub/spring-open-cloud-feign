package com.demo.feign.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) // json으로 받아오는 데이터중에 null은 제외하고 출력
public class BaseResponseInfo {
    private String name;
    private Long age;
    private String header;
}

package com.runwsh.weimin.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class RequestView {

    // 第几条
    private Integer index;

    // cookie
    private String cookie;

    // json
    private String json;

    // 是否成功
    private String success;

    // 错误信息
    private String errorMsg;

}
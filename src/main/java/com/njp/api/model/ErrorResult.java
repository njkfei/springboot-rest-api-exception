package com.njp.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by niejinping on 2017/1/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult<T> {
    public int code = 0;
    public String message;
    public String error;
    public String url;
    public T data;
}

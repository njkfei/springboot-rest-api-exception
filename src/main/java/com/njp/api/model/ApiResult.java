package com.njp.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by niejinping on 2017/1/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "API返回结果")
public class ApiResult<T> {
    @ApiModelProperty(name = "返回码",value = "0",notes = "返回码",example = "200 错误　0正常")
    public int code = 0;
    @ApiModelProperty(name = "简要消息",value = "0",notes = "ok",example = "200 错误　0正常")
    public String message;
    @ApiModelProperty(name = "附加错误数据",value = "0",notes = "附加信息")
    public String error;
    public T data;
}

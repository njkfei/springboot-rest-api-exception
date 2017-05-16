package com.njp.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by niejinping on 2017/1/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(value = "用户信息")
public class User {
    @ApiModelProperty(name = "用户编号",value = "000",notes="notes",example = "example")
    private int userId;
    @ApiModelProperty(name = "用户名",value = "000",notes="notes",example = "example")
    private String userName;
    @ApiModelProperty(name = "用户密码",value = "000",notes="notes",example = "example")
    private String userPass;
}

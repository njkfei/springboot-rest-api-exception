package com.njp.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by niejinping on 2017/1/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class User {
    private int userId;
    private String userName;
    private String userPass;
}

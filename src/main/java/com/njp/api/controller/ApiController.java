package com.njp.api.controller;

import com.njp.api.errorhandler.GlobalExceptionHandler;
import com.njp.api.errorhandler.MyException;
import com.njp.api.model.ApiResult;
import com.njp.api.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by niejinping on 2017/1/12.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @ExceptionHandler(value = NumberFormatException.class)
    public void conflict() {
        // Nothing to do
    }

    @RequestMapping("/demo/{data")
    public ApiResult<String> demo(@PathVariable("data")String data){
        ApiResult<String> result = new ApiResult<String>();
        result.data = data;

        return result;
    }


    @RequestMapping("/user/{userId:\\d+}")
    public ApiResult<User> user(@PathVariable("userId")String userid) throws MyException{
        ApiResult<User> result = new ApiResult<User>();

        int id = 0;

        try{
            id = new Integer(userid);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new MyException(e.getMessage());
        }

        User user = new User();
        user.setUserId(id);
        user.setUserName("hello");
        user.setUserPass("world");
        result.data = user;

        return result;
    }

    // 强大的正则
    @RequestMapping("/user2/{userId:\\d+}")
    public ApiResult<User> user2( @PathVariable("userId")int userid) throws Exception{
        ApiResult<User> result = new ApiResult<User>();
        User user = new User();
        user.setUserId(userid);
        user.setUserName("hello");
        user.setUserPass("world");
        result.data = user;
        return result;
    }

    @RequestMapping("/user3/{userId:\\d+}")
    public ApiResult<User> user3(@PathVariable("userId")int userid) throws Exception{
      throw new Exception("test");
    }

    @RequestMapping("/user4")
    public ApiResult<User> user4( @RequestParam("userid")String userid ) throws Exception{
        ApiResult<User> result = new ApiResult<User>();
        User user = new User();

        int id = 0;
        try {
            id = Integer.valueOf(userid);
        }catch(Exception e){
            throw new Exception("Number convert exception");
        }
        user.setUserId(id);
        user.setUserName("hello");
        user.setUserPass("world");
        result.data = user;
        return result;
    }
}

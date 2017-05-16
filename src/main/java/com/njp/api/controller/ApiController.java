package com.njp.api.controller;

import com.njp.api.errorhandler.GlobalExceptionHandler;
import com.njp.api.errorhandler.MyException;
import com.njp.api.model.ApiResult;
import com.njp.api.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @RequestMapping(value = "/demo/{data}",method = RequestMethod.GET)
    public ApiResult<String> demo(@PathVariable("data")String data){
        ApiResult<String> result = new ApiResult<String>();
        result.data = data;

        return result;
    }


    @RequestMapping(value = "/user/{userId:\\d+}",method = RequestMethod.GET)
    @ApiOperation(value="测试-user", notes="获取用户名")
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
    @RequestMapping(value = "/user2/{userId:\\d+}",method = RequestMethod.GET)
    public ApiResult<User> user2( @PathVariable("userId")int userid) throws Exception{
        ApiResult<User> result = new ApiResult<User>();
        User user = new User();
        user.setUserId(userid);
        user.setUserName("hello");
        user.setUserPass("world");
        result.data = user;
        return result;
    }

    @RequestMapping(value = "/user3/{userId:\\d+}",method = RequestMethod.GET)
    public ApiResult<User> user3(@PathVariable("userId")int userid) throws Exception{
      throw new Exception("test");
    }

    @RequestMapping(value = "/user4",method = RequestMethod.GET)
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

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ApiResult save(@RequestBody User user){
        ApiResult<User> result = new ApiResult<User>();

        result.data = user;
        return result;
    }

    @RequestMapping(value = "/user2",method = RequestMethod.POST)
    public ApiResult update(@ModelAttribute User user){
        ApiResult<User> result = new ApiResult<User>();

        result.data = user;
        return result;
    }
}

## 1. 问题提出
springboot返回的正常响应数据是json格式的数据，可是……………。
如果服务内部故障呢，或者参数异常，这时候返回的是html数据。非常的不方便。

> 那怎么办?

## 2. 建议异常体系
> 拦截异常响应，并进行改写。

比如我的请求为（userid期望是数字，输入不是数字）：

```
http://localhost:9876/api/user4?userid=23d
```

异常响应码为：

```
{
    code: 200,
    message: "Number convert exception",
    url: "http://localhost:9876/api/user4"
}
```

如果输入为：

```
http://localhost:9876/api/user4?userid=23
```
正常响应为：
```
{
    code: 0,
    data:  {
       userId: 23,
       userName: "hello",
       userPass: "world"
    }
}
```

## 3. show me the code

> 关键：@RestControllerAdvice

### 3.0 关系统返回的异常

```
spring.mvc.throw-exception-if-no-handler-found=true
```

反序列化的时候，null字段别反序列化了。
```
spring.jackson.serialization-inclusion=NON_NULL
```
也可以这么干。不返回null字段
```
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    public int code = 0;
    public String message;
    public String error;
    public T data;
}
```

### 3.1 controller返回异常
如下所示。


```
 @RequestMapping("/user4")
    public ApiResult<User> user4( @RequestParam("userid")String userid ) throws MyException{
        ApiResult<User> result = new ApiResult<User>();
        User user = new User();

        int id = 0;
        try {
            id = Integer.valueOf(userid);
        }catch(Exception e){
            throw new MyException("Number convert exception");  // 看这里啊！！！！！！！！！！！！
        }
        user.setUserId(id);
        user.setUserName("hello");
        user.setUserPass("world");
        result.data = user;
        return result;
    }
```

> 不要去试TypeMismatchException，NumberFormatException,没戏。还是老实
将string 转为int吧。系统的错误，通过GlobalExceptionHandler是捕猎不到的。

### 3.2 开启全局异常处理器
如下所示。
```
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    public ErrorResult handleBadRequest(HttpServletRequest req, MyException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }
}
```

### 小提示
- null字段别反序列化了
- 系统异常，转化为自定义异常，更可控
- url如果是数字参数，请使用正则表达式，更安全

>
```
    @RequestMapping("/user/{userId:\\d+}")
    public ApiResult<User> user(@PathVariable("userId")String userid) throws MyException{
      }
```
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

> 说明:上面的方法，data字段，还是会返回null的。如果在data里面想不返回null，需要在data的类定义上加上注解。
如下所示　。

```$xslt
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class User {
    private int userId;
    private String userName;
    private String userPass;
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

### 4 系统错误怎么搞
> Q: 404,这时候，app拿到这个返回，会无语的，怎么搞呢？


> A: 重写/error接口
```$xslt
@RestController
public class HttpErrorHandler implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        ErrorResult result = new ErrorResult();
        result.code=404;
        result.message="not found(404)";
        result.url=request.getRequestURL().toString();
        return result;
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
```

### 5 安全怎么搞
> Q:　不同的用户，权限不一样，有些人有查看的权限，有些人有修改的权限，有些人的删除的权限，如何搞

> A: 通过拦截机制，搞，思路如下。

* token机制
* 路由
* 规则引擎

1.　用户登陆，换token
2.　用户以后的请求，带上token
3.  url拦截，查token,通过token，查路由表
4.　根据路由规则，决定是放还是弃

很简单，对吧。

### 6 文档代码一体化
原理列表
* swagger
* swagger-ui web server
* api service

swagger是api文档一体化的一种方案．

api service返回swagger.json数据．

swagger-ui-web server解析swagger.json数据．进行页面展示．




使用swagger即可．

需要部署swagger-ui服务器，将url指向本服务localhost:8888/api-docs即可．

小坑如下:
#### 6.1 配置跨域
否则，swagger-ui服务器无法获得后端请求数据．
```$xslt
  // 配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
```

#### 6.2 配置
改一下url即可．
```
  const ui = SwaggerUIBundle({
    //url: "http://petstore.swagger.io/v2/swagger.json",
    url: "http://localhost:9876/api-docs",
```

### 7 swagger java注解
* @Api 作用在控制器上
* @ApiModel　作用在model　class上面
* @ApiModelProperty　作用在model class里面的属性上面
* @ApiOperation　作用的requestMapping上面．
* @ApiResponses　描述该API操作可能出现的异常情况。
* @ApiParam 描述该API操作接受的参数类型

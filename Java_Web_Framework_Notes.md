# Java Web 与主流框架核心知识笔记

---

## 1. 课程介绍

### Web 与框架学习路线
```
SpringBoot -> SpringMVC -> MyBatis -> MyBatisPlus -> Spring高级 -> SpringBoot高级
```

---

## 2. Web 核心概念

### Web 项目
基于网络通信，由客户端和服务器组成的程序。
- **客户端**：一般用于页面展示和数据收集。
- **服务端**：用于业务逻辑处理及数据存储操作。

### 架构模式（C/S 与 B/S）
| 架构模式 | 全称 | 客户端形态 | 服务端形态 |
| :--- | :--- | :--- | :--- |
| **C/S** | Client / Server | 手机端 APP、微信小程序、AI 智能体等 | Tomcat / 专用服务器 |
| **B/S** | Browser / Server | PC 浏览器、移动端浏览器 | Tomcat / 专用服务器 |

### 资源分类
- **静态资源**：HTML、CSS、JS、Image、MP3、MP4 等内容固定的资源。
- **动态资源**：根据请求参数到数据库动态查询数据并执行业务逻辑后生成的资源。

### 通信与服务器
- **通信规则**：HTTP 协议。
- **常见 Servlet 容器 / 服务器软件**：Tomcat、JBoss 等。

---

## 3. Spring 与 SpringBoot 介绍

### Spring (2003 年)
- **核心功能**：
  - **IoC（Inversion of Control，控制反转）**：对 Bean 对象的生命周期进行统一管理。
  - **AOP（Aspect Oriented Programming，面向切面编程）**：将非业务代码（事务、日志、权限校验等）与业务代码分离解耦。

### SpringBoot (2013 年)
SpringBoot 是为了简化 Spring 开发而生。
- **核心优势**：
  - 提供大量场景启动器（Starters）；
  - 内置 Web 容器（如 Tomcat）；
  - 简化繁琐配置（约定大于配置）；
  - 提供开箱即用的健康监控（Actuator）等。

---

## 4. Spring Web 项目快速开发体验
*(基于 SpringBoot Starter Web 快速搭建并启动 RESTful 服务)*

---

## 5. HTTP 协议

### 5.1 概念
**HTTP（HyperText Transfer Protocol，超文本传输协议）**：应用层协议，用于规范客户端与服务器之间的数据传输。
- **核心要素**：通信规则、请求报文与响应报文。

### 5.2 通信流程
1. **建立连接**：基于 TCP/IP 建立连接（HTTP/2 基于二进制分帧，HTTP/3 基于 UDP/QUIC）。
2. **发送请求**：客户端发送包含 URL、HTTP 方法、请求头和可选请求体的报文。
3. **处理请求**：服务端解析请求并执行相应业务逻辑或读取静态资源。
4. **发送响应**：服务端返回包含状态码、响应头和响应体的数据。
5. **关闭连接**：完成交互后关闭连接（持久连接 `keep-alive` 可复用通道）。

### 5.3 报文格式

#### 请求数据格式
```http
请求行: 请求方法 [空格] URL [空格] 协议版本 [CRLF]
请求头: 头部字段名: 值 [CRLF]
请求头: 头部字段名: 值 [CRLF]
...
[CRLF]（空行）
请求体: (客户端提交的实体数据)
```

#### 响应数据格式
```http
状态行: 协议版本 [空格] 状态码 [空格] 状态描述 [CRLF]
响应头: 头部字段名: 值 [CRLF]
响应头: 头部字段名: 值 [CRLF]
...
[CRLF]（空行）
响应体: (服务端返回的实体数据)
```

### 5.4 请求方法对比
| 方法 | 语义 | 请求体 | 长度限制 | 安全性 | 幂等性 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **GET** | 查询数据 | 无（参数拼接在 URL 后） | 有限制（通常 ≤ 2KB） | 否 | 是 |
| **POST** | 新增数据 / 提交表单 | 有（支持多类型及文件上传） | 无限制 | 相对安全 | 否 |
| **PUT** | 更新数据 | 有（支持完整更新） | 无限制 | 相对安全 | 是 |
| **DELETE** | 删除数据 | 通常无 | 有限制 | 否 | 是 |

### 5.5 常见 HTTP 状态码
- **1xx（信息）**：接收中，继续处理。
- **2xx（成功）**：
  - `200 OK`：请求成功并返回对应数据。
- **3xx（重定向）**：
  - `302 Found`：临时重定向。
- **4xx（客户端错误）**：
  - `400 Bad Request`：参数格式或类型错误。
  - `403 Forbidden`：权限不足，拒绝访问。
  - `404 Not Found`：资源不存在 / 路径错误。
  - `405 Method Not Allowed`：请求方式与接口定义不匹配。
- **5xx（服务端错误）**：
  - `500 Internal Server Error`：服务端代码抛出异常。
  - `502 Bad Gateway`：网关错误。
  - `503 Service Unavailable`：服务过载或不可用。

---

## 6. SpringMVC 与 SpringBoot 集成
通过引入 `spring-boot-starter-web` 快速集成 SpringMVC，实现自动配置与内嵌 Tomcat 托管。

---

## 7. SpringMVC 核心注解

### 7.1 组件与映射注解
- `@SpringBootApplication`：主启动类复合注解。
- `@Controller`：声明表现层控制器，默认返回视图名称。
- `@RestController`：组合注解（`@Controller` + `@ResponseBody`），返回数据直接写入响应体（如 JSON）。
- `@RequestMapping`：配置 URL 映射路径及请求方式。
  - **精确匹配**：`@RequestMapping(value = {"/user/login", "/user/register"})`
  - **通配符匹配**：
    - `/*`：匹配单层路径（如 `/product/*` 匹配 `/product/list`）。
    - `/**`：匹配多层路径（如 `/product/**` 匹配 `/product/item/1`）。
  - **请求方式限制**：
    - `@RequestMapping(value = "list", method = RequestMethod.GET)`
    - 快捷方式：`@GetMapping`、`@PostMapping`、`@PutMapping`、`@DeleteMapping`（仅用于方法级别）。

### 7.2 参数绑定注解
- `@RequestParam`：用于绑定请求参数。
  - `value / name`：匹配前端参数名。
  - `required`：是否必填（默认 `true`）。
  - `defaultValue`：参数默认值。
  - 数组/集合接收示例：
    ```java
    @GetMapping("hobby")
    public String hobby(String[] hbs) { return Arrays.toString(hbs); }

    @GetMapping("hobby2")
    public String hobby2(@RequestParam List<String> hbs) { return hbs.toString(); }
    ```
- `@PathVariable`：接收 URL 路径中的动态占位符参数。
  ```java
  @GetMapping("/user/{id}")
  public SysUser getUserById(@PathVariable("id") Long id) { ... }
  ```
- `@RequestBody`：接收请求体中的 JSON 数据并反序列化为 Java 对象（常用于 POST / PUT）。
- `@ResponseBody`：指示方法返回值直接作为响应体输出，不经过视图解析器。
- `@RequestHeader`：获取指定的请求头信息（如 `@RequestHeader("Content-Type") String ct`）。
- `@CookieValue`：获取指定的 Cookie 值（如 `@CookieValue("token") String token`）。

---

## 8. JSON 格式规范

**优势**：结构紧凑、易读、解析效率高、天然支持跨语言传输。

### 语法结构示例
```json
// 1. 单个对象
{
  "id": 1,
  "username": "zhangsan",
  "age": 22
}

// 2. 对象数组
[
  { "id": 1, "username": "zhangsan", "age": 22 },
  { "id": 2, "username": "lisi", "age": 23 }
]

// 3. 嵌套复杂对象
{
  "id": 1,
  "username": "于谦",
  "age": 55,
  "dept": {
    "deptId": 1,
    "deptName": "德云社"
  },
  "hobby": ["抽烟", "喝酒", "烫头"]
}
```

---

## 9. 接口调试工具
- **Postman / Apifox**：用于模拟客户端发起 GET/POST 等各类 HTTP 调试请求。

---

## 10. 原生 Servlet API 常用方法

```java
@RequestMapping("testRequest")
public String testRequest(HttpServletRequest request, HttpServletResponse response) {
    // 获取请求参数
    String username = request.getParameter("username");
    
    // 获取请求头
    String token = request.getHeader("token");
    String contentType = request.getHeader("Content-Type");

    // 获取 Cookie
    Cookie[] cookies = request.getCookies();

    // 获取网络地址
    String remoteAddr = request.getRemoteAddr(); // 客户端 IP
    String localAddr = request.getLocalAddr();   // 服务端 IP

    // 获取请求路径
    StringBuffer requestURL = request.getRequestURL(); // 完整路径
    String requestURI = request.getRequestURI();       // 资源路径（不含域名端口）
    String queryString = request.getQueryString();     // 查询字符串

    // 获取 Session 和请求方式
    HttpSession session = request.getSession();
    String method = request.getMethod();

    return "ok";
}
```

---

## 11. 转发与重定向

| 特性 | 转发（Forward） | 重定向（Redirect） |
| :--- | :--- | :--- |
| **跳转位置** | 服务器内部资源跳转 | 任意外部或内部 URL |
| **请求次数** | 1 次请求 | 2 次请求 |
| **URL 变化** | 浏览器地址栏不变 | 浏览器地址栏变为新地址 |
| **数据共享** | request 域数据保留 | request 域数据丢失（需依赖 Session/URL） |

- **前后端分离架构**：直接使用 `@RestController` 返回 JSON，由前端控制路由与页面渲染。
- **前后端一体架构**：后端通过转发或重定向控制页面视图的跳转。

---

## 12. Web 共享域对象

### 共享域分类与生命周期
1. **Request 域 (`HttpServletRequest`)**：仅在**同一次请求**内部有效。
2. **Session 域 (`HttpSession`)**：在**同一个客户端会话**（登录到注销/超时）的多次请求中有效（默认 30 分钟）。
3. **Application 域 (`ServletContext`)**：在**整个应用生命周期**及所有客户端之间全局共享。

> **推荐使用原则**：作用域从小到大使用（`Request` > `Session` > `Application`）。  
> **分布式场景**：单机会话失效，通常使用 Redis 替代 Session 实现跨节点的全局状态共享。

### 客户端 vs 服务端存储
- **Cookie（客户端）**：容量有限（~4KB），明文易被篡改，可被用户禁用；
- **Session（服务端）**：安全性高，数据容量无限制，依赖 Cookie 中的 `JSESSIONID` 标识用户状态。

---

## 13. 静态资源与模板路径

### SpringBoot 默认静态资源加载路径
1. `classpath:/META-INF/resources/`
2. `classpath:/resources/`
3. `classpath:/static/`（最常用）
4. `classpath:/public/`

自定义静态资源目录：
```properties
spring.web.resources.static-locations=classpath:/my-static/
```

---

## 14 & 15. 响应结构与 ResponseEntity

### 统一返回对象封装（Result）
```java
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(String message, T data) { ... }
    public static <T> Result<T> fail(Integer code, String message) { ... }
}
```

### ResponseEntity 控制响应状态与响应头
```java
@RestController
@RequestMapping("/response-entity")
public class ResponseEntityController {

    @GetMapping("/user/{id}")
    public ResponseEntity<Result> getUser(@PathVariable Long id) {
        if (id == 1) {
            Result successResult = Result.success("查询成功", "用户ID：1的信息");
            return new ResponseEntity<>(successResult, HttpStatus.OK);
        } else {
            Result failResult = Result.fail(404, "用户不存在");
            return new ResponseEntity<>(failResult, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/text")
    public ResponseEntity<String> returnText() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "MyCustomValue");
        return new ResponseEntity<>("自定义响应体文本", headers, HttpStatus.OK);
    }
}
```

---

## 16. RESTful API 设计规范

1. **资源唯一标识**：URL 只定位资源，仅使用**名词**（如 `/users`），禁止出现动词（如 `/getUser`）。
2. **HTTP 动词表语义**：
   - `GET`：获取资源
   - `POST`：新增资源
   - `PUT`：全量更新资源
   - `DELETE`：删除资源
3. **数据格式标准化**：请求与响应统一使用标准 JSON 格式。
4. **无状态交互**：每次请求携带完整认证及上下文信息，服务端不保留客户端会话状态。

---

## 17. @ResponseBody 消息转换原理

标记 `@ResponseBody` 后，SpringMVC 通过 `HttpMessageConverter` 将返回值序列化输出：
- **返回 `String`**：调用 `StringHttpMessageConverter` 原样输出纯文本。
- **返回实体对象/集合**：调用 `MappingJackson2HttpMessageConverter`（底层基于 Jackson）将 Java 对象序列化为 JSON 字符串。

---

## 18. SpringMVC 高级扩展

### 18.1 全局异常处理
利用 `@RestControllerAdvice` + `@ExceptionHandler` 实现声明式异常拦截：
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 精确匹配空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointer(NullPointerException e) {
        return Result.fail(500, "空指针异常：" + e.getMessage());
    }

    // 兜底匹配所有未捕获异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.fail(500, "系统内部繁忙，请稍后再试");
    }
}
```
- **匹配规则**：优先匹配精准异常类型，未命中时进入通用 `Exception` 兜底。

### 18.2 参数校验（Validation）
在入参实体前添加 `@Valid`，并使用 `BindingResult` 接收错误列表：
```java
@PostMapping("/save")
public Result saveUser(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        String errorMsg = bindingResult.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return Result.fail(400, errorMsg);
    }
    return Result.success("保存成功", null);
}
```

### 18.3 拦截器（HandlerInterceptor）
- **核心方法**：
  - `preHandle()`：Controller 方法执行前触发（返回 `false` 则中断执行）。
  - `postHandle()`：Controller 执行完毕、视图渲染前触发。
  - `afterCompletion()`：整个请求处理完毕（包括渲染或异常）后触发。
- **多拦截器执行顺序**（以 Interceptor1 和 Interceptor2 全为 `true` 为例）：
  ```
  Interceptor1.preHandle -> Interceptor2.preHandle -> Controller 方法 
  -> Interceptor2.postHandle -> Interceptor1.postHandle 
  -> Interceptor2.afterCompletion -> Interceptor1.afterCompletion
  ```

---

## 19. MVC 与三层架构

```
[前端请求] 
    ↓
表现层 Controller (接收请求、参数校验、分发响应)
    ↓
业务逻辑层 Service (核心业务规则、事务控制)
    ↓
持久化层 DAO/Mapper (CRUD 数据库交互)
    ↓
[数据库 Database]
```

- **MVC**：表现层内部设计模式（Model 数据模型、View 视图渲染、Controller 控制分发）。
- **三层架构**：系统整体架构解耦分层（表现层、业务逻辑层、数据访问层）。

---

## 20. Spring 基础核心（IoC / DI）

### 20.1 核心概念
- **IoC（控制反转）**：将对象的创建、组装、生命周期交由 Spring 容器统一管理。
- **DI（依赖注入）**：容器在运行期将所依赖的对象注入到属性中。

### 20.2 容器接口与常用组件注解
- **容器接口**：
  - `BeanFactory`：顶层工厂，延迟加载（按需加载）。
  - `ApplicationContext`：高级容器接口，立即加载，提供国际化、事件传播等完整功能（推荐）。
- **分层组件注解**：
  - `@Controller` / `@RestController`：表现层组件。
  - `@Service`：业务逻辑层组件。
  - `@Repository`：数据持久层组件。
  - `@Component`：通用 Bean 组件。
- **作用域**：
  - `@Scope("singleton")`：单例模式（默认，内存占用低）。
  - `@Scope("prototype")`：原型多例模式（每次调用生成新实例）。

### 20.3 依赖注入与配置读取
- **注入注解对比**：
  - `@Autowired`（Spring 原生）：优先 `byType`，冲突时 `byName`，可结合 `@Qualifier("beanName")`。
  - `@Resource`（Jakarta/Java 标准）：优先 `byName`，未指定名称时降级为 `byType`。
- **配置属性绑定**：
  - `@Value("${app.title:默认值}")`：单属性值注入。
  - `@PropertySource`：加载非主配置文件。
  - `@ConfigurationProperties(prefix = "db")`：批量将前缀属性映射至实体类。

---

## 21. SpringBoot 单元测试

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloApi() {
        String result = restTemplate.getForObject("/hello/Test", String.class);
        Assertions.assertThat(result).isEqualTo("Hello, Test!");
    }
}
```

---

## 22. MyBatis 持久层框架

### 22.1 ORM 映射与传参
- **ORM 映射规则**：
  - 类 $\leftrightarrow$ 表
  - 属性 $\leftrightarrow$ 列
  - 关联关系 $\leftrightarrow$ 外键约束
- **参数占位符对比**：
  - `#{}`：预编译占位符（`?`），防止 SQL 注入（推荐）。
  - `${}`：字符串直接拼接，存在 SQL 注入风险（仅适用于动态表名/排序列）。

### 22.2 动态 SQL 标签
- `<if test="...">`、`<where>`
- `<set>`、`<trim>`
- `<choose> <when> <otherwise>`
- `<foreach collection="..." item="..." open="(" separator="," close=")">`
- `<sql id="...">` 与 `<include refid="...">`

---

## 23 & 24. MyBatis 多表关联与分步查询

### 24.1 关联映射（Association & Collection）
```xml
<!-- 对一关联（association） -->
<resultMap id="OrderResultMap" type="Order">
    <id column="order_id" property="orderId"/>
    <result column="order_name" property="orderName"/>
    <association property="customer" javaType="Customer">
        <id column="customer_id" property="customerId"/>
        <result column="customer_name" property="customerName"/>
    </association>
</resultMap>

<!-- 对多关联（collection） -->
<resultMap id="CustomerResultMap" type="Customer">
    <id column="customer_id" property="customerId"/>
    <result column="customer_name" property="customerName"/>
    <collection property="orderList" ofType="Order">
        <id column="order_id" property="orderId"/>
        <result column="order_name" property="orderName"/>
    </collection>
</resultMap>
```

### 24.2 分步查询与延迟加载
- **分步配置**：`<collection property="orderList" select="selectOrderListByCustomerId" column="customer_id"/>`
- **开启延迟加载**：
  ```properties
  mybatis.configuration.lazy-loading-enabled=true
  ```

---

## 25. MyBatis-Plus (MP)

### 25.1 常用注解与 CRUD 封装
```java
@Data
@TableName("t_tiger")
public class Tiger {
    @TableId(value = "tiger_id", type = IdType.AUTO)
    private Integer tigerId;

    @TableField("tiger_name")
    private String tigerName;

    @TableField("tiger_age")
    private Integer tigerAge;

    @TableField("tiger_salary")
    private Double tigerSalary;

    @TableLogic
    private Integer deleted; // 逻辑删除字段

    @TableField(exist = false)
    private String tempRemark;
}
```
- **核心组件**：`BaseMapper<T>`、`IService<T>`、`ServiceImpl<M, T>`
- **条件构造器**：`QueryWrapper`、`LambdaQueryWrapper`、`UpdateWrapper`

### 25.2 分页插件配置
```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

---

## 26. Spring 进阶：AOP & 声明式事务

### 26.1 AOP 核心概念与通知类型
- **连接点（Joinpoint）**：允许插入切面的方法或执行点。
- **切入点（Pointcut）**：通过表达式匹配到的具体连接点。
- **通知（Advice）**：
  - `@Before`：前置通知
  - `@AfterReturning`：返回通知（正常执行结束）
  - `@AfterThrowing`：异常通知（抛出异常时执行）
  - `@After`：后置通知（无论如何都会执行）
  - `@Around`：环绕通知（包裹目标方法，控制其是否执行）

### 26.2 代理模式
- **JDK 动态代理**：目标类必须实现接口，基于反射生成代理类。
- **CGLIB 动态代理**：通过生成目标类的子类字节码实现代理。

### 26.3 声明式事务（@Transactional）
- **事务 ACID 特性**：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）。
- **隔离级别**：
  1. `READ_UNCOMMITTED`（读未提交 - 脏读）
  2. `READ_COMMITTED`（读已提交 - 不可重复读）
  3. `REPEATABLE_READ`（可重复读 - 幻读，MySQL 默认）
  4. `SERIALIZABLE`（串行化）
- **传播属性（Propagation）**：
  - `REQUIRED`（默认）：有事务则加入，无事务则新建。
  - `REQUIRES_NEW`：挂起当前事务并开启独立新事务。
- **回滚规则**：默认仅对 `RuntimeException` 和 `Error` 回滚。若需对检查期异常回滚需配置 `@Transactional(rollbackFor = Exception.class)`。

---

## 27. Spring Boot 核心进阶

### 27.1 条件注解与自动装配原理
- **条件注解**：`@ConditionalOnClass`、`@ConditionalOnMissingBean`、`@ConditionalOnProperty` 等。
- **自动装配流程**：
  1. `@SpringBootApplication` 包含 `@EnableAutoConfiguration`。
  2. 加载 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 自动配置清单。
  3. 按条件注解筛选并加载 `XxxAutoConfiguration`。
  4. 从 `XxxProperties` 读取配置文件属性并向 IoC 容器注册 Bean。

### 27.2 项目监控与日志
- **Actuator 端点监控**：暴露 `/beans`、`/health`、`/metrics` 等运维状态端点。
- **日志抽象与实现**：门面选用 SLF4J，实现选用 Logback，通过 `@Slf4j` 注解注入 Logger。

---

## 28. 框架底层核心原理深度总结

### 28.1 Bean 生命周期
1. **加载 BeanDefinition**：解析配置与类元信息；
2. **实例化 Bean**：通过反射调用构造方法分配内存；
3. **属性赋值（Populate）**：通过 DI 注入关联依赖与配置值；
4. **初始化**：
   - 执行 `@PostConstruct` 注解方法；
   - 执行 `BeanPostProcessor.postProcessBeforeInitialization()`；
   - 执行 `InitializingBean.afterPropertiesSet()` 或自定义 `initMethod`；
   - 执行 `BeanPostProcessor.postProcessAfterInitialization()`（AOP 代理生成在此阶段）；
5. **就绪使用**；
6. **销毁**：执行 `@PreDestroy` $ightarrow$ `DisposableBean.destroy()` $ightarrow$ 自定义 `destroyMethod`。

### 28.2 三级缓存解决循环依赖
1. **一级缓存（`singletonObjects`）**：存放完全初始化好的成品 Bean。
2. **二级缓存（`earlySingletonObjects`）**：存放提前暴露的半成品 Bean（未完成属性注入与初始化）。
3. **三级缓存（`singletonFactories`）**：存放 `ObjectFactory` 函数式接口，用于在出现循环依赖时提早生成 AOP 代理对象。

### 28.3 事务失效常见排查场景
1. 方法非 `public` 修饰；
2. 目标类内部自调用方法（未经过代理对象）；
3. 异常被内部 `try-catch` 吞掉且未再次抛出；
4. 抛出非运行时异常（未指定 `rollbackFor = Exception.class`）；
5. 目标类未被 Spring 容器托管；
6. 多线程异步调用（不同线程持有独立数据库连接）。

### 28.4 Spring MVC 完整请求流转架构
```
Client Request
      ↓
[DispatcherServlet] (前端控制器)
      ↓ 1. 获取执行链
[HandlerMapping] (路径映射查找)
      ↓ 2. 调度处理器适配器
[HandlerAdapter] (执行参数解析与拦截器 preHandle)
      ↓ 3. 调用实际业务逻辑
[Controller (Handler)]
      ↓ 4. 业务处理与响应
[Service / DAO]
      ↓ 5. 序列化响应体 (HttpMessageConverter) 或 视图渲染 (ViewResolver)
Client Response
```

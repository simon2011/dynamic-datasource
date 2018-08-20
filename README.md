# dynamic-datasource
## 简介
spring-boot-starter-dynamic-datasource 一个基于springboot的快速集成多数据源的启动器

## 使用方法
### 1.配置方式：
- 一主多从配置方式
```
spring:
  datasource:
    dynamic:
      primary: master #设置默认的数据源或者数据源组,默认值即为master,如果你主从默认下主库的名称就是master可不定义此项。
      datasource:
        master:
          username: root
          password: 123456
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://47.100.20.186:3306/dynamic?characterEncoding=utf8&useSSL=false
          druid: #以下均为默认值
            initial-size: 3
            max-active: 8
            min-idle: 2
            max-wait: -1
            min-evictable-idle-time-millis: 30000
            max-evictable-idle-time-millis: 30000
            time-between-eviction-runs-millis: 0
            validation-query: select 1
            validation-query-timeout: -1
            test-on-borrow: false
            test-on-return: false
            test-while-idle: true
            pool-prepared-statements: true
            max-open-prepared-statements: 100
            filters: stat,wall
            share-prepared-statements: true
        slave_1:
          username: root
          password: 123456
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://47.100.20.186:3307/dynamic?characterEncoding=utf8&useSSL=false
        slave_2:
          username: root
          password: 123456
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://47.100.20.186:3308/dynamic?characterEncoding=utf8&useSSL=false
          #以上会配置一个默认库master，一个组slave下有两个子库slave_1,slave_2
```
- 多库配置方式
```
spring:
  datasource:
    dynamic:
      primary: mysql #记得设置一个默认数据源
      datasource:
        mysql:
        oracle:
        sqlserver: 
        h2:
```
### 2.代码中使用
@DS 可以注解在方法上和类上，没有注解，走默认数据源同时存在方法注解优先于类上注解,可注解在service实现或mapper接口方法上。


```
@Service
@DS("slave")
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> selectAll() {
    return  jdbcTemplate.queryForList("select * from user");
  }
  
  @Override
  @DS("slave_1")
  public List<Map<String, Object>> selectByCondition() {
    return  jdbcTemplate.queryForList("select * from user where age >10");
  }

}
```

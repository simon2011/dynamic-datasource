# dynamic-datasource
简介
一个基于springboot的快速集成多数据源的启动器

配置方式：
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
```

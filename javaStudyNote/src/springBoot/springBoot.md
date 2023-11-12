##1.SpringBoot概念
    SpringBoot提供了一种快速使用Spring的方式，基于约定优于配置的思想。
    SpringBoot功能
        1）自动配置
        SpringBoot的自动配置是一个运动时（更准确地说，是应用程序启动时）的过程，考虑了众多因素，才决定Spring配置应该用哪个，不该用哪个。该过程是SpringBoot自动完成的。
        2）起步依赖
        起步依赖本质上是一个Maven项目对象模型（Project Object Model,POM）,定义了对其他库的依赖传递，这些东西加在一起即支持某项功能。
        简单的说，起步依赖就是将具备某种功能的坐标打包到一起，并提供一些默认的功能。
        3）辅助功能
        提供了一些大项目中常见的非功能天特性，如嵌入式服务器、安全、指标，健康检测、外部配置等。
        
##2.SpringBoot起步依赖原理分析</br>
1)spring-boot-starter-parent
2)spring-boot-starter-web
·在spring-boot-starter-parent中定义了各种技术的版本信息，组合了一套最优搭配的技术版本。
·在各种starter中，定义了完成该功能需要的坐标合集，其中大部分版本信息来自父工程，
·我们的工程继承partner，引入start后，通过依赖传递，就可以简单方便获得需要的jar包，并且不会存在版本冲突等问题。

##3.SpringBoot配置
###3.1配置文件分类
SpringBoot是基于约定的，所以有很多配置都有默认值，但如果想使用自己的配置替换默认配置的话，就可以使用application.properties或者application.yml（application.yaml）进行配置。
·properties:
server.port=8080
·yml:
server:
    port: 8080

小结
·SpringBoot提供了2种配置文件类型：properties和yml/yaml
·默认配置文件名称：application
·在同一级目录下优先级：properties>yml>yaml

###3.2读取配置内容
1）@Value
2)Environment
3)@ConfigurationProperties

###3.3Profile-小结
1）profile是用来完成不同环境下，配置动态切换功能的。
2）profile配置方式
    ·多profile文件方式：提供多个配置文件，每个代表一种环境
        ·application-dev.properties/yml开发环境
        ·application-test.properties/yml测试环境
        ·application-pro.properties/yml生产环境
    ·yml多文档方式：
        ·在yml中使用---分隔不同配置
3）profile激活方式
    ·配置文件：在配置文件中配置：spring.profiles.acticve=dev
    ·虚拟机参数：在VM options指定：-Dspring.profiles.active=dev
    ·命令行参数：java-jar xxx.jar --spring.profiles.active=dev 
    
##4.SpringBoot自动配置
Condition是在Spring4.0增加的条件判断功能，通过这个功能可以实现选择性的创建Bean操作。
    
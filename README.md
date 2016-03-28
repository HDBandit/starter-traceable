# Purpose

It allows to you to log all interactions with your spring beans.

For example if you have this bean:

```java
@Component
public class MyBean {

   public void process1() {
       // process 1
   }
   
}
```

You can annotate which methods you want to trace, doing something like that:

```java
@Component
public class MyBean {

   @Traceable
   public void process1() {
       // process 1
   }
   
}
```

Then if you execute the following code: 

```java
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyApplication.class, args);
        MyBean myBean = (MyBean) context.getBean(MyBean.class);
        myBean.process1();
    }

}
```
After this execution, the following message is printed on console output:

> START trace: 28.03.2016 21:39:26:0000617 :: <Tracer: default tracer, Description: Empty description , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())>

> END trace: 28.03.2016 21:39:26:0000625 :: <Tracer: default tracer, Description: Empty description , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())>
END trace: 28.03.2016 21:39:26:0000625 :: <Tracer: default tracer, Description: Empty description , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())>

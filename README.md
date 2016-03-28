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

> START trace: 28.03.2016 21:39:26:0000617 :: Tracer: default tracer, Description: Empty description , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())

> END trace: 28.03.2016 21:39:26:0000625 :: Tracer: default tracer, Description: Empty description , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())

As you can see, @Traceable marks the method start and finish with a time stamp, the name of the tracer processor, and the description provided. Also it prints the join point intercepted.
In the showed example above, no arguments are provided to the @Traceable annotation, for this reason in the log trace you can see "default tracer" and "Empty description".
The default tracer, by default, prints the trace using the console.

In the next example you can see a more advanced configuration, providing a description and time stamp format:

```java
@Component
public class MyBean {

   @Traceable(dateFormat = "dd/MM/yyyy HH:mm:ss", description = "Processing client orders")
   public void process1() {
       // process 1
   }
   
}
```

Then the output will be:

> START trace: 28.03.2016 21:39:26:12 :: Tracer: default tracer, Description: Processing client orders , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())

> END trace: 28.03.2016 21:39:26:12 :: Tracer: default tracer, Description: Processing client orders , JoinPoint: execution(void com.hdbandit.example_traceable.MyBean.process1())

# Configuring custom tracers

Like I said before by default, the default tracer prints all by console, but you can configure your own tracer processors. Let's to see how with an example:

```java
@Component
public class MyBean {
   
   @Traceable(description = "My custom description", tracerQualifier = "Custom Tracer")
   public void process1() {
       // process 1
   }
}
```

After that, we must to create our tracer bean with the id name 'Custom Tracer'.

```java
@Component("Custom Tracer")
public class MyCustomTracer implements Tracer {

    @Override
    public void trace(String message) {
        // Here you can log the information provided using log4j, slf4j...
        // You can save the register into database
        // You can parse the trace to process the information...
        // Or whatever you want
    }

}
```

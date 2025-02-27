package com.farms4life2016.chapter02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import static org.testng.Assert.assertEquals;


public class SpringGreeterTest {
    @Test
    public void testHelloWorld() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Greeter greeter = context.getBean("helloGreeter", Greeter.class);
        ByteArrayOutputStream baos = context.getBean("baos", ByteArrayOutputStream.class);
        greeter.greet();
        String data = baos.toString(StandardCharsets.UTF_8);
        assertEquals(data, "Hello, World!");
        
    }
}
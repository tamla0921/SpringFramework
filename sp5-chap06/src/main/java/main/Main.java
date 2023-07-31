package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import Config.AppCtx;
import spring.Client;

public class Main {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        
        Client client1 = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        
        System.out.println(client1 == client2);
        
        ctx.close();
    }
}

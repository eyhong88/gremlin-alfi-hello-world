package com.example.helloworld;

import com.gremlin.*;

public class GremlinSimple {

    public void run(){
        System.out.println("Goodbye World");
        String hello = "Hello";

        final GremlinCoordinatesProvider coordinatesProvider = new GremlinCoordinatesProvider() {
            @Override
            public ApplicationCoordinates initializeApplicationCoordinates() {
                return new ApplicationCoordinates.Builder()
                        .withType("HelloWorldExample")
                        .withField("name", "eric h")
                        .build();
            }
        };
        final GremlinServiceFactory gremlinServiceFactory = new GremlinServiceFactory(coordinatesProvider);
        final GremlinService gremlinService = gremlinServiceFactory.getGremlinService();

        final TrafficCoordinates injectionPoint = new TrafficCoordinates.Builder()
                .withType("main")
                .withField("name", "eric")
                .build();
            gremlinService.applyImpact(injectionPoint);

            System.out.println("Hello World");

        final TrafficCoordinates injectionPoint2 = new TrafficCoordinates.Builder()
                .withType("mae")
                .withField("method", "getHello")
                .withField("name", "eric")
                .withField("hello", hello)
                .build();
    //        gremlinService.applyImpact(injectionPoint2);

            System.out.println(gremlinService.execute(injectionPoint2, () -> getHello(hello)));

            System.out.println("Hello World 2");
    }

    public static String getHello(String hello){
        if("Hello".equals(hello))
            return "World";

        return "Sad World";

    }
}

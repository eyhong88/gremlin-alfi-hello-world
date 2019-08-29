package com.example.helloworld;

import com.gremlin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class HelloWorldApplication {
	public static void main(String[] args) throws IOException {

//		GremlinSimple simple = new GremlinSimple();
//		simple.run();
		GremlinClient httpClient = new GremlinClient();
		httpClient.run();
	}
}

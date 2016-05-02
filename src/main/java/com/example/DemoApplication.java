package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@SpringBootApplication

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.example","com.example.controller"})
public class DemoApplication 
{
	/*
	 * curl -H "Accept: application/json" -H "Content-type: application/json" 
-X POST -d '{"name":"value"}' http://localhost:8080/myservice/process
	{
		  "timeStamp": "1458062848734", 
		  "value": "153"
	}
	* //c:\Program Files\MongoDB\Server\3.2\bin>mongod.exe --dbpath c:\\users\\sampath\\mongodbdata\\
	 * 
	 * 
	*/
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
}

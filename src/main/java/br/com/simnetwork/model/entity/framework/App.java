package br.com.simnetwork.model.entity.framework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
private static ClassPathXmlApplicationContext globalContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    
    public static ClassPathXmlApplicationContext getCon() {
    	return globalContext;
    }
    

	
}

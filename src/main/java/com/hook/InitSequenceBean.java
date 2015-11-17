package com.hook;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitSequenceBean implements InitializingBean {
	
	@Autowired
	TestSpringComponent testSpringComponent;
    
    public InitSequenceBean() {   
       System.out.println("InitSequenceBean: constructor");   
    }   
      
    @PostConstruct  
    public void postConstruct() {   
       System.out.println("InitSequenceBean: postConstruct");
    }
    
    @PreDestroy
    public void destory() {
    	System.out.println("InitSequenceBean: destory");  
    }
      
    public void initMethod() {   
       System.out.println("InitSequenceBean: init-method");   
    }   
      
    @Override  
    public void afterPropertiesSet() throws Exception {   
       System.out.println("InitSequenceBean: afterPropertiesSet");   
    }   
}
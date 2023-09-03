package com.example.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ads.repository")
public class AdsApplication {
	private static ApplicationContext applicationContext;
	@GetMapping(path = "/test")
	public String test(){
		System.out.println("success");
		return "hello";
	}

	public static void main(String[] args) {
		try {
			applicationContext = SpringApplication.run(AdsApplication.class, args);
			checkBeansPresence("patentController");
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " +
					applicationContext.containsBean(beanName));
		}
	}

}

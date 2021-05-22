package com.newland.boss.springbootactivemq;

import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringbootActivemqApplication.class)
@WebAppConfiguration
 public class SpringbootActivemqApplicationTests {
	@Autowired
	private Producer producer;



	void contextLoads() {
		while (true) {
			String name = producer.consumer("RB_CDR_QUEUE_100002");
			if (name != null) {
				System.out.println(name);
			}else {
				break;
			}

		}
	}



}

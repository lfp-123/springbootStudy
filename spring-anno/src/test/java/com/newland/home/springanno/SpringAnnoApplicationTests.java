package com.newland.home.springanno;

import com.newland.home.springanno.config.BaseConfig;
import com.newland.home.springanno.config.BaseConfig2;
import com.newland.home.springanno.config.BaseConfig3;
import com.newland.home.springanno.config.DataConfig;
import com.newland.home.springanno.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@SpringBootTest
class SpringAnnoApplicationTests {

	@Test
	void contextLoads() {
		AnnotationConfigApplicationContext annoBase = new AnnotationConfigApplicationContext(BaseConfig.class);
		String[] annoNames = annoBase.getBeanDefinitionNames();
		for (String annoName : annoNames) {
			System.out.println(annoName);
		}

	}
	@Test
	void TestConfig2(){
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(BaseConfig2.class);
		Object person1 = anno.getBean("person");
		Object person2 = anno.getBean("person");
		System.out.println(person1 == person2);
	}
	@Test
	void testConfig3(){
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(BaseConfig3.class);
		String[] beanNamesForType = anno.getBeanNamesForType(Person.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}
		ConfigurableEnvironment environment = anno.getEnvironment();
		String property = environment.getProperty("os.name");
		System.out.println(property);
	}
	AnnotationConfigApplicationContext annos = new AnnotationConfigApplicationContext(BaseConfig3.class);

	@Test
	void Test5(){
		printBeans(annos);
	}

	private void printBeans(AnnotationConfigApplicationContext a){
		String[] beanDefinitionNames = a.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}
	@Test
	public void Test(){
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext();
		//1创建容器
		//2获取激活代码
		anno.getEnvironment().setActiveProfiles("test","dev");
		//3注册住配置类
		anno.register(DataConfig.class);
		//4启动刷新容器
		anno.refresh();
			}
}


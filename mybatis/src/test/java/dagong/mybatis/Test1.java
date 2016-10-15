package dagong.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dagong.jms.PersonService;

public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-jms.xml");
		PersonService ps=(PersonService) ac.getBean("personServiceImpl");
		for( int i=0;i<10;i++){
            Thread t=new Thread(new User(ps,"汤"+i));
            t.start();
        }
		
	}
}

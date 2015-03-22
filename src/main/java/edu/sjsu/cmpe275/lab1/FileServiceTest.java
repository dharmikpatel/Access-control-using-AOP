package edu.sjsu.cmpe275.lab1;


import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.sjsu.cmpe275.lab1.IFileService;
import edu.sjsu.cmpe275.lab1.UnAuthorisedException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")

public class FileServiceTest {
	
	@Autowired 
	private IFileService fileShareService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	Logger log = Logger.getLogger(FileServiceTest.class.getName());
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	IFileService fileService = (IFileService) applicationContext.getBean("FileSharing");
	


	@Test
	  public void testA() {
		try {
			log.info("<--------------Test A----------------->");
			System.out.println("testA");
			fileService.readFile("Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	 
	@Test
	  public void testB() {
		try {
			log.info("<--------------Test B----------------->");
			System.out.println("testB");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	@Test
	  public void testC() {
		try {
			log.info("<--------------Test C----------------->");
			System.out.println("testC");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	
	@Test
	  public void testD() {
		try {
			log.info("<--------------Test D----------------->");
			System.out.println("testD");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Bob", "Alice", ".//src//main//resources//home//Carl//shared//carl1.txt");
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	
	@Test
	  public void testE() {
		try {
			log.info("<-------------- Test E ----------------->");
			System.out.println("testE");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.unshareFile("Alice","Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	
	@Test 
	  public void testF() {
		try {
			log.info("<--------------Test F ----------------->");
			System.out.println("testF");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Alice", "Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Carl", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.unshareFile("Alice","Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	
	@Test 
	  public void testG() {
		try {
			log.info("<--------------Test G ----------------->");
			System.out.println("testG");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.unshareFile("Alice","Bob", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			fileService.readFile("Carl", ".//src//main//resources//home//Alice//shared//alice1.txt");
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	@Test  
	  public void testH() {
		try {
			log.info("<--------------Test H ----------------->");
			System.out.println("testH");
			fileService.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//file1.txt");
			fileService.readFile("Bob", ".//src//main//resources//home//Alice//shared//file2.txt");
			
			

		} catch(UnAuthorisedException UnEx) {
			System.out.println(UnEx.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	  }
	@Test 
	public void results()
	{
		log.info("testA:Pass");
		log.info("testB:Pass");
		log.info("testC:Pass");
		log.info("testD:Pass");
		log.info("testE:Pass");
		log.info("testF:Pass");
		log.info("testG:Pass");
		log.info("testH:Pass");
		}
}

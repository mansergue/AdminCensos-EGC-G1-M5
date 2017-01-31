package services;



import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import utilities.RESTClient;


@ContextConfiguration(locations = { "classpath:spring/datasource.xml", 
									"classpath:spring/config/packages.xml" })

@RunWith(SpringJUnit4ClassRunner.class)

public class RestClientTest {
	
	// Service under test -----------------------

	private RESTClient restClient;
			
	// Tests ------------------------------------

	@Test
	public void testTodo() throws ParseException {
		restClient.getMapUSernameAndEmailByJsonAutentication();
	}

}

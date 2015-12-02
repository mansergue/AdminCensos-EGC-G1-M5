package services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import services.CensusService;
import utilities.PopulateDatabase;
import domain.Census;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", 
									"classpath:spring/config/packages.xml" })

@RunWith(SpringJUnit4ClassRunner.class)

public class CensusServiceTest {
	
	// Service under test -----------------------

	@Autowired
	private CensusService censusService;
	
	// Tests ------------------------------------

//	@Test
//	public void createCensus() throws ParseException {
//		
//		Census c_1;
//		Census c_2;
//		
//		c_1.setIdVotacion(1);
//		c_1.setUsername("Izquierda");
//		//c_1.set
//		
//		//c_1 = censusService.create();
//		Census c = censusService.create(1, "juan22", "1416502444473", "1916502444473", "votacion prueba");
//		
//		
//		Census c = censusService.create(1, "juan22", "1416502444473", "1916502444473", "votacion prueba");
//
//		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
//
//		vpo.put("pepe", true);
//		vpo.put("juan22", false);
//		vpo.put("maria", true);
//
//		c.setVoto_por_usuario(vpo);
//
//		censusService.save(c);
//
//		Census c2 = censusService.create(2, "juan22", "1416502444473", "1916502444473", "votacion prueba2");
//
//		HashMap<String, Boolean> vpo2 = c.getVoto_por_usuario();
//
//		c.setVoto_por_usuario(vpo2);
//
//		censusService.save(c2);
//
//		Census c3 = censusService.create(3, "juan23", "1416502444473", "1916502444473", "votacion prueba3");
//
//		HashMap<String, Boolean> vpo3 = c3.getVoto_por_usuario();
//
//		vpo3.put("juan22", false);
//
//		c.setVoto_por_usuario(vpo3);
//
//		censusService.save(c3);
	}
//}

package services;


import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CensusService;
import domain.Census;
import domain.Vote;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", 
									"classpath:spring/config/packages.xml" })

@RunWith(SpringJUnit4ClassRunner.class)

public class VoteServiceTest {
	
	// Service under test -----------------------

		@Autowired
		private VoteService voteService;
		
		// Tests ------------------------------------

		@Test
		public void testTodo() throws ParseException {
			
//			Vote vote=voteService.findOne(1);
//			Collection<Vote> votes=voteService.findAll();
//			Vote aux=voteService.findVoteByVote(99);
			voteService.popularVotaciones();

		}
		

}

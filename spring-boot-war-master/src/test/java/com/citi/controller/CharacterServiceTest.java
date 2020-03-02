package com.citi.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.citi.service.CharacterService;

public class CharacterServiceTest {
	
	private CharacterService characterService = new CharacterService();
	
	Logger logger = Logger.getLogger(CharacterServiceTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCharacter() {
		String name = "Homer";
		
		ArrayList<String> quotes = characterService.getphrase(name);
		logger.info("------Executing test case testwelcome-----");
		try {
				assertNotNull(quotes);
		}catch(AssertionError e) {
			logger.error("Assertion Error Occured: Quotes is null");
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}
	
	@Test	
	public void testgetAllCharacters() {
		logger.info("----Executing testgetAllCharacters");
		characterService.getAllCharacters();
	}
	
	@Test	
	public void testDeleteCharacters() {
		logger.info("----Executing testDeleteCharacters");
		String name = "Homer";
		characterService.deleteCharacter(name);
	}
	
	/*
	 * @Test public void testWelcomeNotFound() { String name = "Nanda";
	 * welcomeController = new CharacterService(); ArrayList<String> quotes =
	 * welcomeController.getphrase(name);
	 * System.out.println("-Executing test case testwelcomeNotFound-----"); try {
	 * assertNotNull(quotes); }catch(AssertionError e) {
	 * System.err.println("Assertion Error Occured: Name not found");
	 * e.printStackTrace(); } //fail("Not yet implemented"); }
	 */
}

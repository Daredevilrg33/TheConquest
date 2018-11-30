package com.conquest.window;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.model.GameModel;

public class LoadGameWindoweTest {
	public static String ASIA_MAP_FILE_PATH;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ASIA_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\three.map--1543590146329.bin";

	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
/**
 * testing for checking whether the file is loading is or not.
 */
	@Test
	public void test() {
		LoadGameWindow loadGameWindow= new LoadGameWindow();
		GameModel gameModel=loadGameWindow.loadGameModel(ASIA_MAP_FILE_PATH);
		assertEquals("true",String.valueOf(loadGameWindow.redirectGameToGameWindow(gameModel)));
	}

}

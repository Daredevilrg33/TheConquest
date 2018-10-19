package com.conquest.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.utilities.Utility;

public class GameWindowControllerTest {
Utility utility= new Utility();
private PlayerModel[] players;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap("E:\\Asia.map");
		GameWindowController gamewindow=new GameWindowController(null,3,mapHierarchyModel);
		gamewindow.initializingPlayerModels(3,mapHierarchyModel);
		System.out.println(players[0].getPlayerCountryList().size()+"heeeeeeeee");
	}

}

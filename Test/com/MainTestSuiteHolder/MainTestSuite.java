package com.MainTestSuiteHolder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.conquest.controller.ControllerTestSuite;
import com.conquest.controller.GameWindowControllerTest;
import com.conquest.mapdeditor.modeltest.ModelTestSuite;
import com.conquest.utilities.UtilityTest;
import com.conquest.window.NewGameMenuScreenTest;

/**
 * The Class MainTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ModelTestSuite.class, GameWindowControllerTest.class, UtilityTest.class,
		NewGameMenuScreenTest.class,ControllerTestSuite.class })
public class MainTestSuite {

}

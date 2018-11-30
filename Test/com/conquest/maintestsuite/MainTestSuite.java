package com.conquest.maintestsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.conquest.controller.ControllerTestSuite;
import com.conquest.controller.GameWindowControllerTest;
import com.conquest.mapdeditor.modeltest.ModelTestSuite;
import com.conquest.utilities.UtilityTest;
import com.conquest.window.NewGameMenuScreenTest;
import com.conquest.window.WindowTestSuite;

/**
 * The Class MainTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ModelTestSuite.class, UtilityTest.class, NewGameMenuScreenTest.class, ControllerTestSuite.class })
public class MainTestSuite {

}

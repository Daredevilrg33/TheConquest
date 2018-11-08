package MainTestSuiteHolder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.conquest.controller.GameWindowControllerTest;
import com.conquest.mapdeditor.modeltest.ControllerTestSuite;
import com.conquest.utilities.UtilityTest;
import com.conquest.window.NewGameMenuScreenTest;

/**
 * The Class MainTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ControllerTestSuite.class, GameWindowControllerTest.class, UtilityTest.class,
		NewGameMenuScreenTest.class })
public class MainTestSuite {

}

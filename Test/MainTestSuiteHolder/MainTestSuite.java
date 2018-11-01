package MainTestSuiteHolder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.conquest.controller.GameWindowControllerTest;
import com.conquest.mapdeditor.modeltest.ControllerTestSuite;
import com.conquest.utilities.UtilityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ControllerTestSuite.class, GameWindowControllerTest.class,UtilityTest.class})
public class MainTestSuite {

}

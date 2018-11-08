package com.conquest.mapdeditor.modeltest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * TEST Suite for Controller.
 *
 * @author Bhigya Pande
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ ContinentModelTest.class, CountryModelTest.class, MapHierarchyModelTest.class,
		PlayerModelTest.class })
public class ControllerTestSuite {

}

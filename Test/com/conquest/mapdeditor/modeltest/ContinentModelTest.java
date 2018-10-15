package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;

public class ContinentModelTest {
	ContinentModel cm=new ContinentModel("ASIA");
	 ArrayList<CountryModel> cl=new ArrayList<CountryModel>();


	
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
	public void searchCountryTest() {
		//fail("Not yet implemented");
		CountryModel c=new CountryModel("INDIA"); 
		cm.addCountry(c);
//		cl.add("INDIA");
//		cm.addCountry("INDIA");
		System.out.println(cm.searchCountry("INIDA"));
		assertEquals("india",cm.searchCountry("INDIA"));

		
	}

}

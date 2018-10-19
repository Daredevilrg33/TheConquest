package com.conquest.window;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;

public class GameHelp {

	public GameHelp()
	{
		try{
		File htmlFile = new File("/doc/index.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
		}
		catch(Exception exc)
		{
		exc.getMessage();	
		}
	}
}

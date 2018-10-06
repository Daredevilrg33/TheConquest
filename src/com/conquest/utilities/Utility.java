/**
 * 
 */
package com.conquest.utilities;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * @author Rohit Gupta
 *
 */
public class Utility {

	
	/**
	 * This method will provide the functionality to pick a map file.
	 * 
	 * @return : This will return the path of the map.
	 * 
	 */
	public static String pickFile() {
		// TODO Auto-generated method stub
		String filePath = "";
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select Map");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			filePath = jfc.getSelectedFile().getPath();
			System.out.println("File Path: " + filePath);
			return filePath;
		}else
			return "";
	}

}

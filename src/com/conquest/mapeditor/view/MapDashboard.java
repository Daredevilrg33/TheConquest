/**
 * 
 */
package com.conquest.mapeditor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;
import com.conquest.window.GameWindow;
import com.conquest.window.MainMenuScreen;

/**
 * The Class MapDashboard.
 * 
 * @author Nancy Goyal
 */
public class MapDashboard extends JFrame implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6731694007847910246L;

	/** The button new map. */
	private JButton btnNewMap;

	/** The button load map. */
	private JButton btnLoadMap;

	/** The file name. */
	private String filePath = "", fileName = "";

	/**
	 * MapDashboard Constructor Instantiates a new map dashboard.
	 */
	public MapDashboard() {

		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);

		btnNewMap = new JButton("New Map");
		btnNewMap.setBounds(Constants.WIDTH / 2 - 50, 50, 150, 30);
		btnNewMap.addActionListener(this);
		add(btnNewMap);

		btnLoadMap = new JButton("Load Existing Map");
		btnLoadMap.setBounds(Constants.WIDTH / 2 - 50, 120, 150, 30);
		btnLoadMap.addActionListener(this);
		add(btnLoadMap);

		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				MainMenuScreen initialScreen = new MainMenuScreen();
				initialScreen.setVisible(true);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == btnNewMap) {
			MapHierarchyModel mapHierarchyModel = new MapHierarchyModel();
			NewMapEditorView newMap = new NewMapEditorView(mapHierarchyModel);
			newMap.setVisible(true);
			dispose();
		} else if (event.getSource() == btnLoadMap) {
			loadFromFile();
		}

	}

	/**
	 * loadFromFile Method Method to implement response to Load Existing Map,
	 * provide GUI to input file's name.
	 */
	private void loadFromFile() {

		filePath = Utility.pickFile();
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] splitFilePath = filePath.split(pattern);
		fileName = splitFilePath[splitFilePath.length - 1];
		System.out.println("File Name : " + fileName);
		System.out.println("File Path : " + filePath);
		Utility utility = new Utility();
		MapHierarchyModel mapModel = utility.parseAndValidateMap(filePath);

		if (!mapModel.isValErrorFlag()) {
			dispose();
			NewMapEditorView newMap = new NewMapEditorView(mapModel);
			newMap.setVisible(true);
		} else {
			String valErrorMsg = mapModel.getErrorMsg();
			JOptionPane.showMessageDialog(this, valErrorMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
		}

	}
}

/**
 * 
 */
package com.conquest.mapeditor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.utilities.Constants;
import com.conquest.window.InitialScreen;

/**
 * @author Nancy Goyal
 *
 */
public class MapDashboard extends JFrame implements ActionListener{
	private JButton btnNewMap;
	private JButton btnLoadMap;
	
	public MapDashboard() {
		
		
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		btnNewMap  = new JButton("New Map");
		btnNewMap.setBounds(Constants.WIDTH/2-50, 50, 150, 30);
		btnNewMap.addActionListener(this);
		add(btnNewMap);
		
		btnLoadMap  = new JButton("Load Existing Map");
		btnLoadMap.setBounds(Constants.WIDTH/2-50, 120, 150, 30);
		btnLoadMap.addActionListener(this);
		add(btnLoadMap);
		
		addWindowListener(new WindowAdapter() {
			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				InitialScreen initialScreen = new InitialScreen();
				initialScreen.setVisible(true);
			}
			});
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == btnNewMap)
		{
			NewMapEditorView newMap = new NewMapEditorView();
			newMap.setVisible(true);
			dispose();
		}else if(event.getSource() == btnLoadMap)
		{
			loadFromFile();
		}else {
			System.exit(0);
		}
		
				
	}
	
	/**
	 * Method to implement response to Load Existing Map, provide GUI to input file's name
	 */
	private void loadFromFile(){
	
		String inputFileName;
		JFileChooser chooser;
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./src/map"));
		chooser.setDialogTitle("Choose Existing map file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileFilter(){
			@Override
			public boolean accept(File f){
				if(f.getName().endsWith(".map")||f.isDirectory())
					return true;
				else return false;
			}
			public String getDescription(){
				return "Map files(*.map)";
			}
		});
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}			
		inputFileName = chooser.getSelectedFile().getAbsolutePath().trim();//
		if (inputFileName.isEmpty()){
			JOptionPane.showMessageDialog(null,"Map file name invalid");
		}
		else{
			
		}
	}

}

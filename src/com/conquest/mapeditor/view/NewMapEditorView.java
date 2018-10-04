package com.conquest.mapeditor.view;

/**
 * @author Nancy Goyal
 *
 */
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.utilities.Constants;

public class NewMapEditorView extends JFrame {
	
	private JScrollPane treeScrollPane;
	private JScrollPane mappingScrollPane;
	private TreeRenderer treeView;
	
	private JLabel  labelConnectivity;
	
	private JButton addCountry;
	private JButton addContinent;

	public NewMapEditorView() {

		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		setTitle("Map Editor View");  
		setSize(Constants.MAP_EDITOR_WIDTH,Constants.MAP_EDITOR_HEIGHT);
		int screenWidth = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setLocation((screenWidth-1280)/2, (screenHeight-700)/2-30); 
		setResizable(false);
		setLayout(null); 
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK); //handle windows events	
		setDefaultCloseOperation(3); //set exit program when close the window  
		
		DefaultMutableTreeNode continentRoot = new DefaultMutableTreeNode("Continent Hierarchy");
		treeView= new TreeRenderer(continentRoot);
		
		MapEditorController mapEditorController =new MapEditorController(treeView);
		MapHierarchyModel mapHierarchyModel = new MapHierarchyModel();
		mapEditorController.intializeModel(mapHierarchyModel);
		
		labelConnectivity = new javax.swing.JLabel("Connectivity Between Countries");
		add(labelConnectivity);  
		Dimension size = labelConnectivity.getPreferredSize();
		labelConnectivity.setFont(new java.awt.Font("dialog",1,15));
		labelConnectivity.setBounds(15,8,size.width+200,size.height);   
		
		mappingScrollPane= new JScrollPane(null);
		add(mappingScrollPane);
		mappingScrollPane.setBounds(15,55,800,600);
        
		treeScrollPane= new JScrollPane(treeView,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(treeScrollPane);
		treeScrollPane.setBounds(mappingScrollPane.getBounds().x+(int)(mappingScrollPane.getBounds().getWidth()),55,300,600);
		
		addContinent = new javax.swing.JButton("Add Continent");
		add(addContinent);  
		addContinent.addActionListener(mapEditorController);
		addContinent.setBounds(treeScrollPane.getBounds().x,20,size.width-50,size.height+10);

		addCountry = new javax.swing.JButton("Add Country");
		add(addCountry);  
		addCountry.addActionListener(mapEditorController);
		addCountry.setBounds(addContinent.getBounds().x+(int)addContinent.getBounds().getWidth()+10,20,size.width-50,size.height+10);
		
		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.
			 * WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

	}
}

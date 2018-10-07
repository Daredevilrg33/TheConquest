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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
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
	
	MapHierarchyModel mapHierarchyModel;

	public NewMapEditorView() {
		
		setLocationRelativeTo(null);

		setTitle("Map Editor View");
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setLocation((screenWidth - 1280) / 2, (screenHeight - 700) / 2 - 30);
		setResizable(false);
		setLayout(null);
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK); //handle windows events	
		setDefaultCloseOperation(3); //set exit program when close the window  
		
		DefaultMutableTreeNode continentRoot = new DefaultMutableTreeNode("Continent Hierarchy");
		treeView= new TreeRenderer(continentRoot);
		
		MapEditorController mapEditorController = new MapEditorController(this);
		mapHierarchyModel = new MapHierarchyModel();
		setMapHierarchyModel(mapHierarchyModel);
		mapEditorController.addModel(this.mapHierarchyModel);
		
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
		addContinent.addActionListener(mapEditorController);
		add(addContinent);  
		
		addContinent.setBounds(treeScrollPane.getBounds().x,20,size.width-50,size.height+10);

		addCountry = new javax.swing.JButton("Add Country");
		addCountry.addActionListener(mapEditorController);
		add(addCountry);  
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
				dispose();
				MapDashboard mapDashboard = new MapDashboard();
				mapDashboard.setVisible(true);
			}
		});

	}
	
	
	
	/**
	 * @return the mapHierarchyModel
	 */
	public MapHierarchyModel getMapHierarchyModel() {
		return mapHierarchyModel;
	}



	/**
	 * @param mapHierarchyModel the mapHierarchyModel to set
	 */
	public void setMapHierarchyModel(MapHierarchyModel mapHierarchyModel) {
		this.mapHierarchyModel = mapHierarchyModel;
	}
	
	
	/**
	 * Method to refresh and update the continent hierarchy tree on adding new continent
	 * or new country
	 */

	public void updateHierarchyTree() {

		DefaultMutableTreeNode tRoot = new DefaultMutableTreeNode(
				"Map - " + mapHierarchyModel.getConquestMapName() + " ");
		for (ContinentModel continentObj : mapHierarchyModel.getContinentsList()) {
			ArrayList<CountryModel> loopCountriesList = continentObj.getCountriesList();
			DefaultMutableTreeNode continentNode = new DefaultMutableTreeNode(continentObj.getContinentName());
			for (CountryModel loopCountry:loopCountriesList){
				continentNode.add(new DefaultMutableTreeNode(loopCountry.getCountryName()+" "));
			}
			tRoot.add(continentNode);
		}
		treeView =new TreeRenderer(tRoot);
		treeView.setShowsRootHandles(true);
		treeScrollPane.getViewport().removeAll();
		treeScrollPane.getViewport().add(treeView);
	}

}

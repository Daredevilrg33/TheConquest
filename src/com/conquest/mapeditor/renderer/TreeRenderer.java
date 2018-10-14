package com.conquest.mapeditor.renderer;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
public class TreeRenderer extends JTree 
{
	private static final long serialVersionUID = 1977697232621553087L;
	private JTree tree;
    
    
    public TreeRenderer(DefaultMutableTreeNode continentRoot) {
		super(continentRoot);
		
        //create the tree by passing in the root node
        tree = new JTree(continentRoot);
        //ImageIcon imageIcon = new ImageIcon(TreeRenderer.class.getResource("/resources/continents.png"));
       // DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        //renderer.setLeafIcon(imageIcon);
        add(tree);
       
        
        this.setVisible(true);
	}

}

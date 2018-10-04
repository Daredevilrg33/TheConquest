package com.conquest.mapeditor.renderer;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
public class TreeRenderer extends JTree 
{
    private JTree tree;
    
    
    public TreeRenderer(DefaultMutableTreeNode continentRoot) {
		super(continentRoot);
		
        //create the tree by passing in the root node
        tree = new JTree(continentRoot);
        add(tree);
        System.out.println("\n\n\n tree "+tree);
       
        this.setVisible(true);
	}

}

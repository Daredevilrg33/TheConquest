package com.conquest.mapeditor.renderer;

import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * The Class TreeRenderer.
 * 
 * @author Nancy Goyal
 */
public class TreeRenderer extends JTree {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 1977697232621553087L;

	/** The tree. */
	private JTree tree;

	/**
	 * TreeRenderer Parameterized Constructor Instantiates a new tree renderer.
	 * 
	 * @param continentRoot the continent root
	 */
	public TreeRenderer(DefaultMutableTreeNode continentRoot) {
		super(continentRoot);

		tree = new JTree(continentRoot);
		add(tree);

		this.setVisible(true);
	}

	/**
	 * Method to expand or collapse all tree structure.
	 * 
	 * @param parent the parent node
	 * @param mode   1 - expand, other number - collapse
	 */
	public void expandAll(TreePath parent, int mode) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				this.expandAll(path, mode);
			}
		}
		if (mode == 1)
			this.expandPath(parent);
		else
			this.collapsePath(parent);
	}

}

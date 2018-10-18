package com.conquest.mapeditor.renderer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The Class TableRenderer.
 */
public class TableRenderer extends JTable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of class.
	 * 
	 * @param table the data modal of table
	 */
	public TableRenderer(DefaultTableModel table) {
		super(table);

	}
}

package com.conquest.mapeditor.renderer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableRenderer extends JTable{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of class.
	 * @param table the data modal of table
	 */
	public TableRenderer(DefaultTableModel table){
		super(table);
		
	}
}

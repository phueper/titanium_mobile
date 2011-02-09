/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.ui;

import java.util.ArrayList;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;

@Kroll.proxy(creatableInModule=UIModule.class)
public class TableViewSectionProxy extends TiViewProxy
{
	protected ArrayList<TableViewRowProxy> rows;

	public TableViewSectionProxy(TiContext tiContext) {
		super(tiContext);
		rows = new ArrayList<TableViewRowProxy>();
	}

	@Override
	public TiUIView createView(Activity activity) {
		return null;
	}

	@Kroll.method @Kroll.getProperty
	public TableViewRowProxy[] getRows()
	{
		return rows.toArray(new TableViewRowProxy[rows.size()]);
	}

	@Kroll.getProperty @Kroll.method
	public double getRowCount() {
		return rows.size();
	}

	@Kroll.method
	public void add(TableViewRowProxy rowProxy)
	{
		if (rowProxy != null) {
			rows.add(rowProxy);
			if (rowProxy.getParent() == null) {
				rowProxy.setParent(this);
			}
		}
	}

	@Kroll.method
	public void remove(TableViewRowProxy rowProxy) {
		if (rowProxy != null) {
			rows.remove(rowProxy);
			if (rowProxy.getParent() == this) {
				rowProxy.setParent(null);
			}
		}
	}

	@Kroll.method
	public TableViewRowProxy rowAtIndex(int index)
	{
		TableViewRowProxy result = null;
		if (index > -1 && index < rows.size()) {
			result = rows.get(index);
		}

		return result;
	}

	@Kroll.method
	public void insertRowAt(int index, TableViewRowProxy row) {
		rows.add(index, row);
	}

	@Kroll.method
	public void removeRowAt(int index) {
		rows.remove(index);
	}
	@Kroll.method
	public void updateRowAt(int index, TableViewRowProxy row) {
		rows.set(index, row);
	}
	
	@Override
	public String toString() {
		return "[object TiUITableViewSection]";
	}

	@Override
	public void releaseViews()
	{
		super.releaseViews();
		if (rows != null) {
			for (TableViewRowProxy row : rows) {
				row.releaseViews();
			}
		}
	}
}

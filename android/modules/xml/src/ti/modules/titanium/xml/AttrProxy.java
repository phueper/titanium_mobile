/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.xml;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;

@Kroll.proxy
public class AttrProxy extends NodeProxy {

	private Attr attr;
	public AttrProxy(TiContext context, Attr attr) {
		super(context, attr);
		this.attr = attr;
	}
	
	public Attr getAttr() {
		return attr;
	}
	
	@Kroll.getProperty @Kroll.method
	public String getName() {
		return attr.getName();
	}
	
	@Kroll.getProperty @Kroll.method
	public ElementProxy getOwnerElement() {
		return getProxy(attr.getOwnerElement());
	}
	
	@Kroll.getProperty @Kroll.method
	public boolean getSpecified() {
		return attr.getSpecified();
	}
	
	@Kroll.getProperty @Kroll.method
	public String getValue() {
		return attr.getValue();
	}
	
	@Kroll.setProperty @Kroll.method
	public void setValue(String value) throws DOMException {
		attr.setValue(value);
	}
}

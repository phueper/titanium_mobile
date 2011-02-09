/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package org.appcelerator.kroll;

import java.util.HashMap;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.kroll.KrollBridge;

public abstract class KrollProxyBinding {
	protected HashMap<String, Object> bindings = new HashMap<String, Object>();
	
	public boolean hasBinding(String name) {
		// Subclasses will need to initialize all bindings to null
		return bindings.containsKey(name);
	}
	
	public abstract boolean isModule();
	public abstract Class<? extends KrollProxy> getProxyClass();
	
	public abstract Object getBinding(String name);
	
	public abstract void bindContextSpecific(KrollBridge bridge, KrollProxy proxy);
	
	public abstract String getAPIName();
	public abstract String getShortAPIName();
}

/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.android;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.proxy.IntentProxy;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiConvert;

import android.app.PendingIntent;
import android.content.Context;

@Kroll.proxy(creatableInModule=AndroidModule.class)
public class PendingIntentProxy extends KrollProxy 
{
	private static final String TAG = "TiPendingIntent";
	private static boolean DBG = TiConfig.LOGD;

	protected PendingIntent pendingIntent;
	protected IntentProxy intent;
	protected Context pendingIntentContext;
	protected int flags;

	public PendingIntentProxy(TiContext tiContext)
	{
		super(tiContext);
	}

	@Override
	public void handleCreationArgs(KrollModule createdInModule, Object[] args)
	{
		if (args.length >= 1 && args[0] instanceof IntentProxy) {
			intent = (IntentProxy) args[0];
			if (args.length >= 2) {
				flags = TiConvert.toInt(args[1]);
			}
		}

		super.handleCreationArgs(createdInModule, args);

		pendingIntentContext = this.context.getActivity();
		if (context == null) {
			pendingIntentContext = this.context.getRootActivity();
		}
		if (context == null) {
			pendingIntentContext = TiApplication.getInstance().getApplicationContext();
		}
		if (pendingIntentContext == null || intent == null) {
			throw new IllegalStateException("Creation arguments must contain intent");
		}
		switch (intent.getType()) {
			case IntentProxy.TYPE_ACTIVITY : {
				pendingIntent = PendingIntent.getActivity(
					pendingIntentContext, 0, intent.getIntent(), flags);
				break;
			}
			case IntentProxy.TYPE_BROADCAST : {
				pendingIntent = PendingIntent.getBroadcast(
					pendingIntentContext, 0, intent.getIntent(), flags);
				break;
			}
			case IntentProxy.TYPE_SERVICE : {
				pendingIntent = PendingIntent.getService(
					pendingIntentContext, 0, intent.getIntent(), flags);
				break;
			}
		}
	}

	public void handleCreationDict(KrollDict dict)
	{
		if (dict.containsKey(TiC.PROPERTY_INTENT)) {
			intent = (IntentProxy) dict.get(TiC.PROPERTY_INTENT);
		}
		if (dict.containsKey(TiC.PROPERTY_FLAGS)) {
			flags = dict.getInt(TiC.PROPERTY_FLAGS);
		}
		super.handleCreationDict(dict);
	}

	public PendingIntent getPendingIntent()
	{
		return pendingIntent;
	}
}

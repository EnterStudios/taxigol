/*
Copyright 2009-2011 Urban Airship Inc. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE URBAN AIRSHIP INC ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL URBAN AIRSHIP INC OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.taxigol.taxi.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class AUReceiver extends BroadcastReceiver {

    private static final String logTag = "AUReceiver";

    public static String APID_UPDATED_ACTION_SUFFIX = ".apid.updated";
    
    public static String EXTRA_ACTION = "action";
    public static String EXTRA_SERVICE_ID = "action";
    
    public final static String ACTION_DEFAULT = "default";
    public final static String ACTION_SERVICE_SAVED = "service_saved";

    @Override
    public void onReceive(Context context, Intent intent) {
        
        String action = intent.getAction();
        String intentAction = intent.getStringExtra(EXTRA_ACTION);
        
        if (action.equals(PushManager.ACTION_PUSH_RECEIVED)) {
        	if (intentAction.equals(ACTION_DEFAULT)){
        		sendNewServiceArrivedIntent(intent, context);
        	}else if (intentAction.equals(ACTION_SERVICE_SAVED)){
        		sendServiceSavedIntent(intent, context);
        	}

        } else if (action.equals(PushManager.ACTION_NOTIFICATION_OPENED)) {

           //Nothing to do here

        } else if (action.equals(PushManager.ACTION_REGISTRATION_FINISHED)) {
            Log.d(logTag, "Registration complete. APID:" + intent.getStringExtra(PushManager.EXTRA_APID)
                    + ". Valid: " + intent.getBooleanExtra(PushManager.EXTRA_REGISTRATION_VALID, false));
            Intent launch = new Intent(UAirship.getPackageName() + APID_UPDATED_ACTION_SUFFIX);
            UAirship.shared().getApplicationContext().sendBroadcast(launch);

        } else if (action.equals(PushManager.ACTION_GCM_DELETED_MESSAGES)) {
            Log.d(logTag, "The GCM service deleted "+intent.getStringExtra(PushManager.EXTRA_GCM_TOTAL_DELETED)+" messages.");
        }

    }

	private void sendServiceSavedIntent(Intent intent, Context context) {
		int serviceId = Integer.parseInt(intent.getStringExtra("service_id").trim());
		Intent i  = new Intent(ServicesBroadcastReceiver.INTENT_FILTER_ACTION);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra(ServicesBroadcastReceiver.EXTRA_NAME_ACTION, ServicesBroadcastReceiver.EXTRA_ACTION_SERVICE_SAVED);
		i.putExtra(ServicesBroadcastReceiver.EXTRA_SERVICE_ID, serviceId);
		context.sendBroadcast(i);
	}

	private void sendNewServiceArrivedIntent(Intent intent, Context context) {
		int serviceId = Integer.parseInt(intent.getStringExtra("service_id").trim());
		Intent i  = new Intent(ServicesBroadcastReceiver.INTENT_FILTER_ACTION);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra(ServicesBroadcastReceiver.EXTRA_NAME_ACTION, ServicesBroadcastReceiver.EXTRA_ACTION_NEW_TAXI_SERVICE);
		i.putExtra(ServicesBroadcastReceiver.EXTRA_SERVICE_ID, serviceId);
		context.sendBroadcast(i);
	}

}

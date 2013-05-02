package com.taxigol.pubnubtest;

import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

public class MainActivity extends Activity {

	private Pubnub pubnub;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pubnub = new Pubnub(
			    "pub-c-e2b0950a-c130-4495-8f85-0872dfa6f091",  // PUBLISH_KEY   (Optional, supply "" to disable)
			    "sub-c-baf063da-b32e-11e2-a387-02ee2ddab7fe",  // SUBSCRIBE_KEY (Required)
			    "",      // SECRET_KEY    (Optional, supply "" to disable)
			    "",      // CIPHER_KEY    (Optional, supply "" to disable)
			    false    // SSL_ON?
			);
		
		Hashtable<String, String> args = new Hashtable<String, String>();
		args.put("channel", "taxigol-pedir-taxi");
		try {
			pubnub.subscribe(args, new Callback() {
				@Override
				public void connectCallback(final String channel,final Object message) {
					System.out.println("Successfuly connected to channel:"+channel + " with message "+message);
				}
				public void successCallback(final String channel,
                        final Object message) {
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(), channel+": "+message.toString(), Toast.LENGTH_LONG).show();
			                
						}
					});
                }
			});
		} catch (PubnubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package com.taxigol.taxi.model;

import java.util.Hashtable;

import com.google.common.eventbus.EventBus;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import com.taxigol.taxi.events.AsyncCallback;
import com.taxigol.taxi.events.ServiceReceivedEvent;


public class MessageReceiver {

	private Pubnub pubnub;
	private EventBus eventBus;
	
	public MessageReceiver(EventBus bus) {
		pubnub = new Pubnub(
			    "pub-c-e2b0950a-c130-4495-8f85-0872dfa6f091",  // PUBLISH_KEY   (Optional, supply "" to disable)
			    "sub-c-baf063da-b32e-11e2-a387-02ee2ddab7fe",  // SUBSCRIBE_KEY (Required)
			    "",      // SECRET_KEY    (Optional, supply "" to disable)
			    "",      // CIPHER_KEY    (Optional, supply "" to disable)
			    false    // SSL_ON?
			);
		this.eventBus = bus;
		
		Hashtable<String, String> args = new Hashtable<String, String>();
		args.put("channel", "taxigol-pedir-taxi");
		try {
			pubnub.subscribe(args, new Callback() {
				@Override
				public void connectCallback(final String channel,final Object message) {
					System.out.println("-------------------------------------------------------------------");
					System.out.println("Successfuly connected to channel:"+channel + " with message "+message);
				}
				public void successCallback(final String channel,
                        final Object message) {

					System.out.println("----- "+message+" -----");
					postEvent(channel, message);
					
                }
			});
		} catch (PubnubException e) {
			e.printStackTrace();
		}
	}

	protected void postEvent(String channel, Object message) {

		ServiceReceivedEvent event = new ServiceReceivedEvent(message.toString(), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				System.out.println("Message successfuly posted");
			}
		});
		eventBus.post(event);
	}
	
	
	
}

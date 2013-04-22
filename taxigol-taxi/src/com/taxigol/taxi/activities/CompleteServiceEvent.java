package com.taxigol.taxi.activities;

import android.util.Pair;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.events.CallBackEvent;

public class CompleteServiceEvent extends CallBackEvent<Void, Pair<String, String>> {

	public CompleteServiceEvent(Pair<String, String> data, OnSuccess<Void> onSuccess) {
		super(data, onSuccess);
	}

}

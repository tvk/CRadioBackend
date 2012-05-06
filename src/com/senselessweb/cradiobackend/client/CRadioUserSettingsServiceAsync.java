package com.senselessweb.cradiobackend.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.senselessweb.cradiobackend.shared.model.UserSettings;

public interface CRadioUserSettingsServiceAsync 
{
	public void get(AsyncCallback<UserSettings> callback);
	
	public void update(UserSettings userSettings, AsyncCallback<Void> callback);
}

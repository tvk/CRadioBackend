package com.senselessweb.cradiobackend.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.senselessweb.cradiobackend.shared.model.UserSettings;

@RemoteServiceRelativePath("cRadioStorage")
public interface CRadioUserSettingsService extends RemoteService
{
	public UserSettings get();
	
	public void update(UserSettings userSettings);
}

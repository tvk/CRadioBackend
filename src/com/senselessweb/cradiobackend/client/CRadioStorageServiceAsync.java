package com.senselessweb.cradiobackend.client;

import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CRadioStorageServiceAsync 
{
	void getPresets(AsyncCallback<List<String>> callback);
	
	public void getAllGenres(AsyncCallback<SortedMap<String, Boolean>> callback);

	void updatePresets(List<String> presets, AsyncCallback<Void> callback);
	
	public void updateGenres(Collection<String> genres, AsyncCallback callback);
}

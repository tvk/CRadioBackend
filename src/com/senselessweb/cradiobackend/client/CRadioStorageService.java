package com.senselessweb.cradiobackend.client;

import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("cRadioStorage")
public interface CRadioStorageService extends RemoteService
{
	public SortedMap<String, Boolean> getAllGenres();
	
	public void updateGenres(Collection<String> genres);

	public List<String> getPresets();
	
	public void updatePresets(List<String> presets);
}

package com.senselessweb.cradiobackend.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.senselessweb.cradiobackend.client.CRadioStorageService;
import com.senselessweb.cradiobackend.shared.model.UserSettings;

public class CRadioStorageServiceImpl  extends RemoteServiceServlet implements CRadioStorageService
{
	
    private final UserService userService = 
    		UserServiceFactory.getUserService();
	
    private static final PersistenceManagerFactory pmf = 
    		JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private static final Set<String> genres = new HashSet<String>();
	static {
		genres.add("Electronic");
		genres.add("Trance");
		genres.add("Alternative");
		genres.add("R&B/Urban");
		genres.add("Reggae");
		genres.add("Reggaeton");
		genres.add("Metal");
		genres.add("Rock");
		genres.add("Pop");
		genres.add("Blues");
		genres.add("Easy Listening");
		genres.add("Trance");
	}

	@Override
	public SortedMap<String, Boolean> getAllGenres() 
	{
		final SortedMap<String, Boolean> result = new TreeMap<String, Boolean>();
		for (final String genre : genres) 
			result.put(genre, false);
		for (final String genre : this.getUserSettings().getGenres()) 
			result.put(genre, true);
				
		return result;
	}

	@Override
	public void updateGenres(final Collection<String> genres) 
	{
		final PersistenceManager persistenceManager = pmf.getPersistenceManager();
		
		final UserSettings userSettings = this.getUserSettings();
		userSettings.setGenres(genres);
		persistenceManager.makePersistent(userSettings);
		persistenceManager.close();
	}
	

	@Override
	public List<String> getPresets() 
	{
		return this.getUserSettings().getPresets();
	}

	@Override
	public void updatePresets(List<String> presets) 
	{
		final PersistenceManager persistenceManager = pmf.getPersistenceManager();
		
		final UserSettings userSettings = this.getUserSettings();
		userSettings.setPresets(presets);
		persistenceManager.makePersistent(userSettings);
		persistenceManager.close();
	}	
	
	private UserSettings getUserSettings()
	{
		final PersistenceManager persistenceManager = pmf.getPersistenceManager();
		
		final User user = userService.getCurrentUser();
		if (user == null) throw new IllegalStateException("No user is logged in");

		final Query q = persistenceManager.newQuery(UserSettings.class);
		q.setFilter("userId == paramUserId");
		q.declareParameters("String paramUserId");
		
		final List<UserSettings> userSettings = (List<UserSettings>) q.execute(user.getUserId());
		final UserSettings result = userSettings.isEmpty() ? new UserSettings(user.getUserId()) : userSettings.get(0);
		
		persistenceManager.close();
		return result;
	}

	
}

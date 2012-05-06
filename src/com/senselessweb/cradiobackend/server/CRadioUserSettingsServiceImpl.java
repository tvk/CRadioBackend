package com.senselessweb.cradiobackend.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.senselessweb.cradiobackend.client.CRadioUserSettingsService;
import com.senselessweb.cradiobackend.shared.model.UserSettings;

public class CRadioUserSettingsServiceImpl  extends RemoteServiceServlet implements CRadioUserSettingsService
{
	
    private final UserService userService = 
    		UserServiceFactory.getUserService();
	
    private static final PersistenceManagerFactory pmf = 
    		JDOHelper.getPersistenceManagerFactory("transactions-optional");

	@Override
	public UserSettings get()
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

	@Override
	public void update(final UserSettings userSettings) 
	{
		final PersistenceManager persistenceManager = pmf.getPersistenceManager();
		
		persistenceManager.makePersistent(userSettings);
		persistenceManager.close();
	}
	
}

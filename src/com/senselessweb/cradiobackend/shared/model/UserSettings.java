package com.senselessweb.cradiobackend.shared.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class UserSettings 
{

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent 
	private String userId;

	@Persistent 
	private Collection<String> genres = Collections.emptySet();

	@Persistent 
	private List<String> presets = Collections.emptyList();
	
	public UserSettings(final String userId) 
	{
		this.userId = userId;
	}	
	
	public Long getId() 
	{
		return this.id;
	}
	
	public String getUserId() 
	{
		return this.userId;
	}
	
	public Collection<String> getGenres() {
		return genres;
	}
	
	public void setGenres(Collection<String> genres) {
		this.genres = genres;
	}
	
	public List<String> getPresets() {
		return presets;
	}
	
	public void setPresets(List<String> presets) {
		this.presets = presets;
	}
}

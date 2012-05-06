package com.senselessweb.cradiobackend.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class UserSettings implements Serializable
{

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent 
	private String userId;

	@Persistent 
	private String[] genres = new String[0];

	@Persistent 
	private String[] presets = new String[6];
	
	public UserSettings() {
		// TODO Auto-generated constructor stub
	}
	
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
		return Arrays.asList(this.genres);
	}
	
	public void setGenres(Collection<String> genres) {
		this.genres = genres.toArray(new String[genres.size()]);
	}
	
	public String[] getPresets() {
		return this.presets;
	}
	
	public void setPresets(String[] presets) {
		this.presets = presets;
	}
}

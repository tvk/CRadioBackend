package com.senselessweb.cradiobackend.client;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.senselessweb.cradiobackend.shared.model.UserSettings;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

@SuppressWarnings("javadoc")
public class CRadioBackend implements EntryPoint, ClickHandler
{
	private final TabPanel tabPanel = new TabPanel();
	private final AbsolutePanel presetsPanel = new AbsolutePanel();
	private final Label label = new Label("Preset 1:");
	private final TextBox textBoxPreset1 = new TextBox();
	private final Label label_1 = new Label("Preset 2:");
	private final TextBox textBoxPreset2 = new TextBox();
	private final Label label_2 = new Label("Preset 3:");
	private final TextBox textBoxPreset3 = new TextBox();
	private final Label label_3 = new Label("Preset 4:");
	private final TextBox textBoxPreset4 = new TextBox();
	private final Label label_4 = new Label("Here you can modify your presets:");
	private final Label label_5 = new Label("Preset 5:");
	private final TextBox textBoxPreset5 = new TextBox();
	private final TextBox textBoxPreset6 = new TextBox();
	private final Label label_6 = new Label("Preset 6:");
	private final AbsolutePanel genresPanel = new AbsolutePanel();
	private final Label label_7 = new Label("Here you can select your genres:");
	private final ListBox listBoxGenres = new ListBox(true);
	private final Label label_8 = new Label("Hold <CTRL> to select multiple genres");
	private final Button btnSave = new Button("Save");

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

	private final CRadioUserSettingsServiceAsync storageService = 
			GWT.create(CRadioUserSettingsService.class);
	
	
	private UserSettings userSettings;
	private final AbsolutePanel welcomePanel = new AbsolutePanel();
	private final Label lblWelcomeToThe = new Label("Welcome to the CRadio backend application");
	private final Label lblInThisApplication = new Label("In this application you can manage the presets and desired genres for your CRadio android application");
	private final Label lblYourCradioApplication = new Label("Your CRadio application id is:");
	private final Label lblApplicationId = new Label("");
	private final Label lblNewLabel = new Label("CRadio Backend");
	
	@Override
	public void onModuleLoad() 
	{
		RootPanel rootPanel = RootPanel.get("modifyPresetsContainer");
		rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
		this.lblNewLabel.setStyleName("cradio-title");
		
		rootPanel.add(this.lblNewLabel, 10, 10);
		this.lblNewLabel.setSize("570px", "15px");
		
		rootPanel.add(this.tabPanel, 10, 31);
		this.tabPanel.setSize("570px", "385px");
		this.tabPanel.add(this.welcomePanel, "Welcome", false);
		this.welcomePanel.setSize("551px", "337px");
		this.lblWelcomeToThe.setStyleName("cradio-headline");
		this.lblWelcomeToThe.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		this.welcomePanel.add(this.lblWelcomeToThe, 10, 10);
		this.lblWelcomeToThe.setSize("531px", "38px");
		
		this.welcomePanel.add(this.lblInThisApplication, 10, 54);
		
		this.welcomePanel.add(this.lblYourCradioApplication, 10, 90);
		
		this.welcomePanel.add(this.lblApplicationId, 187, 90);
		this.lblApplicationId.setSize("104px", "15px");
		
		this.tabPanel.add(this.presetsPanel, "Presets", false);
		this.presetsPanel.setSize("524px", "334px");
		
		this.presetsPanel.add(this.label, 10, 67);
		
		this.presetsPanel.add(this.textBoxPreset1, 69, 62);
		this.textBoxPreset1.setSize("431px", "15px");
		
		this.presetsPanel.add(this.label_1, 10, 98);
		this.label_1.setSize("53px", "15px");
		
		this.presetsPanel.add(this.textBoxPreset2, 69, 93);
		this.textBoxPreset2.setSize("431px", "15px");
		
		this.presetsPanel.add(this.label_2, 10, 129);
		this.label_2.setSize("53px", "15px");
		
		this.presetsPanel.add(this.textBoxPreset3, 69, 124);
		this.textBoxPreset3.setSize("431px", "15px");
		
		this.presetsPanel.add(this.label_3, 10, 160);
		this.label_3.setSize("53px", "15px");
		
		this.presetsPanel.add(this.textBoxPreset4, 69, 155);
		this.textBoxPreset4.setSize("431px", "15px");
		
		this.presetsPanel.add(this.label_4, 10, 10);
		
		this.presetsPanel.add(this.label_5, 10, 191);
		this.label_5.setSize("53px", "15px");
		
		this.presetsPanel.add(this.textBoxPreset5, 69, 186);
		this.textBoxPreset5.setSize("431px", "15px");
		
		this.presetsPanel.add(this.textBoxPreset6, 69, 217);
		this.textBoxPreset6.setSize("431px", "15px");
		
		this.presetsPanel.add(this.label_6, 10, 222);
		this.label_6.setSize("53px", "15px");
		
		this.tabPanel.add(this.genresPanel, "Genres", false);
		this.genresPanel.setSize("520px", "334px");
		
		this.genresPanel.add(this.label_7, 10, 10);
		this.label_7.setSize("200px", "15px");
		this.listBoxGenres.setVisibleItemCount(5);
		
		this.genresPanel.add(this.listBoxGenres, 10, 31);
		this.listBoxGenres.setSize("235px", "272px");
		
		this.genresPanel.add(this.label_8, 10, 309);
		
		rootPanel.add(this.btnSave, 10, 422);
		rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
		
		this.init();
	}
	
	/**
	 * Custom initialization
	 */
	private void init()
	{
		this.tabPanel.selectTab(0);
		this.btnSave.addClickHandler(this);
		
		this.storageService.get(new AsyncCallback<UserSettings>() 
		{
			@Override
			public void onSuccess(final UserSettings result) 
			{
				CRadioBackend.this.userSettings = result;
				lblApplicationId.setText(String.valueOf(result.getId()));
				
				// Init genres
				final SortedSet<String> allGenres = new TreeSet<String>();
				allGenres.addAll(genres);
				allGenres.addAll(result.getGenres());
				for (final String genre : genres)
				{
					listBoxGenres.addItem(genre, genre);
					listBoxGenres.setItemSelected(listBoxGenres.getItemCount() - 1, result.getGenres().contains(genre));
				}
				
				// Init presets
				textBoxPreset1.setText(result.getPresets()[0]);
				textBoxPreset2.setText(result.getPresets()[1]);
				textBoxPreset3.setText(result.getPresets()[2]);
				textBoxPreset4.setText(result.getPresets()[3]);
				textBoxPreset5.setText(result.getPresets()[4]);
				textBoxPreset6.setText(result.getPresets()[5]);
				
			}
			
			@Override
			public void onFailure(final Throwable caught) 
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onClick(final ClickEvent event) 
	{
		if (event.getSource() == this.btnSave)
		{
			final Collection<String> genres = new HashSet<String>();
			for (int i = 0; i < this.listBoxGenres.getItemCount(); i++)
				if (this.listBoxGenres.isItemSelected(i)) genres.add(this.listBoxGenres.getValue(i));
			this.userSettings.setGenres(genres);
			
			final String[] presets = new String[6];
			presets[0] = this.textBoxPreset1.getText();
			presets[1] = this.textBoxPreset2.getText();
			presets[2] = this.textBoxPreset3.getText();
			presets[3] = this.textBoxPreset4.getText();
			presets[4] = this.textBoxPreset5.getText();
			presets[5] = this.textBoxPreset6.getText();
			this.userSettings.setPresets(presets);
			
			this.storageService.update(userSettings, new AsyncCallback<Void>() 
			{
				@Override
				public void onSuccess(final Void result) 
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFailure(final Throwable caught) 
				{
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	private void raiseMessage(final String message)
	{
		
	}
}

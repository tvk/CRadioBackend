package com.senselessweb.cradiobackend.client;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;

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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CRadioBackend implements EntryPoint
{

	private final CRadioStorageServiceAsync storageService = 
			GWT.create(CRadioStorageService.class);

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("modifyPresetsContainer");
		rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
		
		TabPanel tabPanel = new TabPanel();
		rootPanel.add(tabPanel, 10, 31);
		tabPanel.setSize("540px", "385px");
		
		AbsolutePanel presetsPanel = new AbsolutePanel();
		tabPanel.add(presetsPanel, "Presets", false);
		presetsPanel.setSize("524px", "334px");
		
		Label lblPreset_1 = new Label("Preset 1:");
		presetsPanel.add(lblPreset_1, 10, 67);
		
		TextBox textBox = new TextBox();
		presetsPanel.add(textBox, 69, 62);
		textBox.setSize("431px", "15px");
		
		Label lblPreset_2 = new Label("Preset 2:");
		presetsPanel.add(lblPreset_2, 10, 98);
		lblPreset_2.setSize("53px", "15px");
		
		TextBox textBox_2 = new TextBox();
		presetsPanel.add(textBox_2, 69, 93);
		textBox_2.setSize("431px", "15px");
		
		Label lblPreset_3 = new Label("Preset 3:");
		presetsPanel.add(lblPreset_3, 10, 129);
		lblPreset_3.setSize("53px", "15px");
		
		TextBox textBox_3 = new TextBox();
		presetsPanel.add(textBox_3, 69, 124);
		textBox_3.setSize("431px", "15px");
		
		Label lblPreset_4 = new Label("Preset 4:");
		presetsPanel.add(lblPreset_4, 10, 160);
		lblPreset_4.setSize("53px", "15px");
		
		TextBox textBox_4 = new TextBox();
		presetsPanel.add(textBox_4, 69, 155);
		textBox_4.setSize("431px", "15px");
		
		Label headline = new Label("Here you can modify your presets:");
		presetsPanel.add(headline, 10, 10);
		
		Label lblPreset_5 = new Label("Preset 5:");
		presetsPanel.add(lblPreset_5, 10, 191);
		lblPreset_5.setSize("53px", "15px");
		
		TextBox textBox_5 = new TextBox();
		presetsPanel.add(textBox_5, 69, 186);
		textBox_5.setSize("431px", "15px");
		
		TextBox textBox_6 = new TextBox();
		presetsPanel.add(textBox_6, 69, 217);
		textBox_6.setSize("431px", "15px");
		
		Label lblPreset_6 = new Label("Preset 6:");
		presetsPanel.add(lblPreset_6, 10, 222);
		lblPreset_6.setSize("53px", "15px");
		
		AbsolutePanel genresPanel = new AbsolutePanel();
		tabPanel.add(genresPanel, "Genres", false);
		genresPanel.setSize("520px", "334px");
		
		Label lblHereYouCan = new Label("Here you can select your genres:");
		genresPanel.add(lblHereYouCan, 10, 10);
		lblHereYouCan.setSize("200px", "15px");
		
		final ListBox listBox = new ListBox(true);
		genresPanel.add(listBox, 10, 31);
		listBox.setSize("496px", "272px");
		listBox.setVisibleItemCount(5);
		
		Label lblHoldToSelect = new Label("Hold <CTRL> to select multiple genres");
		genresPanel.add(lblHoldToSelect, 10, 309);
		
		Button btnNewButton = new Button("Save");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				final Collection<String> selectedGenres = new HashSet<String>();
				for (int i = 0; i < listBox.getItemCount(); i++)
					if (listBox.isItemSelected(i)) 
						selectedGenres.add(listBox.getValue(i));
				storageService.updateGenres(selectedGenres, new AsyncCallback() {
					@Override public void onFailure(Throwable caught) {
						throw new RuntimeException(caught);
					}

					@Override public void onSuccess(Object result) {
						// TODO Auto-generated method stub
					}
				});
			}
		});
		rootPanel.add(btnNewButton, 10, 424);
		
		this.storageService.getAllGenres(new AsyncCallback<SortedMap<String, Boolean>>() 
		{
			
			@Override
			public void onSuccess(SortedMap<String, Boolean> result) 
			{
				for (final Map.Entry<String, Boolean> genre : result.entrySet())
				{
					listBox.addItem(genre.getKey(), genre.getKey());
					listBox.setItemSelected(listBox.getItemCount()-1, genre.getValue());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) 
			{
				throw new RuntimeException(caught);
			}
		});
		
	}	
}

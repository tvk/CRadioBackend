package com.senselessweb.cradiobackend.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.senselessweb.cradiobackend.server.CRadioUserSettingsServiceImpl;
import com.senselessweb.cradiobackend.shared.model.UserSettings;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

public class SettingsServlet extends HttpServlet 
{
	
	private final CRadioUserSettingsServiceImpl userSettingsService = new CRadioUserSettingsServiceImpl();

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException 
	{
		if (req.getParameter("appId") == null)
			throw new IllegalArgumentException("No param \"appId\" specified");
		final UserSettings userSettings = this.userSettingsService.get(Long.valueOf(req.getParameter("appId")));
		if (userSettings == null)
			throw new IllegalArgumentException("No settings found");
		
		try 
		{
			final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			final Element root = (Element) doc.appendChild(doc.createElement("root"));
			
			final Element genres = (Element) root.appendChild(doc.createElement("genres"));
			final Element presets = (Element) root.appendChild(doc.createElement("presets"));
			
			for (final String genre : userSettings.getGenres())
			{
				final Element genreElement = doc.createElement("genre");
				genreElement.setTextContent(genre);
				genres.appendChild(genreElement);
			}

			for (final String preset : userSettings.getPresets())
			{
				final Element presetElement = doc.createElement("preset");
				presetElement.setTextContent(preset);
				presets.appendChild(presetElement);
			}
			
			final DOMSource source = new DOMSource(doc);
			
			final Transformer transformer = TransformerFactory.newInstance().newTransformer();
			final StreamResult result = new StreamResult(resp.getOutputStream());
			
			transformer.transform(source, result);			
		} 
		catch (final Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

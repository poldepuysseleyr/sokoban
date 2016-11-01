package gui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Thomas
 */
public class Taal
{

    private Locale locale;
    public static ResourceBundle resourceBundle;
    private String taal;

    public Taal(String taal)
    {
	if ("nl".equals(taal))
	{
	    this.locale = new Locale("nl");
	} else
	{
	    if ("en".equals(taal))
	    {
		this.locale = new Locale("en");
	    } else
	    {
		if ("fr".equals(taal))
		{
		    this.locale = new Locale("fr");
		}
	    }
	}

	resourceBundle = ResourceBundle.getBundle("sokoban_resource", locale);

	this.setTaal(taal);
    }

    public String getText(String tekst)
    {
	return resourceBundle.getString(tekst);
    }

    public void setTaal(String gekozenTaal)
    {
	this.taal = gekozenTaal;
    }

    public String getTaal()
    {
	return taal;
    }
}

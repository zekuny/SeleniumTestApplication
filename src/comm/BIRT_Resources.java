package comm;

import java.util.ResourceBundle;

import com.anugraha.birt.app.BIRT_AppProperty;

public class BIRT_Resources
{
	private static ResourceBundle rbAppResourceBundle;
	
	
	public static final ResourceBundle getRbAppResourceBundle()
	{
		return initializeRbAppResourceBundle();
	}
	private static ResourceBundle initializeRbAppResourceBundle()
	{
		if(null==rbAppResourceBundle)
		{
			rbAppResourceBundle=java.util.ResourceBundle.getBundle(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_RESOURCE_BUNDLE));
		}
		return rbAppResourceBundle;
	}
	public static final void setRbAppResourceBundle(ResourceBundle rbAppResourceBundle)
	{
		BIRT_Resources.rbAppResourceBundle = rbAppResourceBundle;
	}
}

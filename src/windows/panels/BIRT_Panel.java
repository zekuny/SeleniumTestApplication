package windows.panels;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import comm.BIRT_Resources;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.windows.panels.BIRT_FieldErrorException;

public abstract class BIRT_Panel extends JPanel
{

	private static final long	serialVersionUID	= 1L;
	protected ResourceBundle	objResourceBundle;

	public BIRT_Panel()
	{
		objResourceBundle = BIRT_Resources.getRbAppResourceBundle();
		try
		{
			// Set cross-platform Java L&F (also called "Metal")
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.javax.swing.plaf.metal");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		}
		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
		}
		initComponents();
	}

	protected final void initComponents()
	{
		initHeader();
		initPanelComponents();
		setPanelComponentText();
		setPanelComponentActionCommands();
		addPanelComponentActionListeners();
		setLayout();
		setVisible(true);
	}

	// Set Layout of Panel
	protected abstract void setLayout();

	// Initialize Header Section of Panel
	protected abstract void initHeader();

	// Initialize Components of Panel
	protected abstract void initPanelComponents();

	// Set Text of Each Component
	protected abstract void setPanelComponentText();

	// Set ActionCommands for Each Component
	protected abstract void setPanelComponentActionCommands();

	// Add Action Listener for Each Component
	protected abstract void addPanelComponentActionListeners();

	public void setVisible(boolean bVisibility)
	{
		try
		{
			updateContents();

			if (!bVisibility)
				resetContents();
			else

			super.setVisible(bVisibility);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}
	}

	public void setTemporaryHidden(boolean bTemporaryHidden)
	{
		super.setVisible(bTemporaryHidden);
	}

	// Reset All Components to avoid Data leakage
	public abstract void resetContents();

	// Refresh values in Components when they are set to Visible after being
	// Hidden
	public abstract void updateContents() throws BIRT_Exception;

	// To check whether all fields are valid before saving or editing else
	// display appropriate messages.
	public boolean ensureFieldsProper()
	{
		if (isFieldsProper())
			return true;
		return false;
	}

	/*
	 * To check whether all fields are valid before saving or editing Use
	 * handleFieldProperError(to display messages)
	 */
	protected abstract boolean isFieldsProper();

	// Display appropriate messages if fields are not proper
	protected boolean handleFieldProperError(String strMessage)
	{

		BIRT_Logger.error(strMessage);
		BIRT_ExceptionHandler.handleError(new BIRT_FieldErrorException(strMessage));
		return false;
	}

}

package windows.frames.mainwindow;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_Panels;
import comm.BIRT_Resources;

/**
 * 
 * @author Arun_Thomas07
 */

public class BIRT_MainWindow extends JFrame
{

	// Serialization
	private static final long		serialVersionUID	= 1L;

	// Window Components
	private JMenu					jmAdministration;
	private JMenu					jmDBConnection;
	private JMenu					jmProject;
	private JMenu					jmQuery;
	private JMenu					jmTestCase;
	private JMenu					jmTestScript;
	private JMenu					jmTestSuite;
	private JMenuItem				jmiAppConfiguration;
	private JMenuItem				jmiDBConnection_Browse;
	private JMenuItem				jmiDBConnection_Create;
	private JMenuItem				jmiExit;
	private JMenuItem				jmiProject_ChangeProject;
	private JMenuItem				jmiProject_Create;
	private JMenuItem				jmiProject_Delete;
	private JMenuItem				jmiProject_Edit;
	private JMenuItem				jmiProject_View;
	private JMenuItem				jmiQuery_Browse;
	private JMenuItem				jmiQuery_Create;
	private JMenuItem				jmiTestCase_Browse;
	private JMenuItem				jmiTestCase_Create;
	private JMenuItem				jmiTestScript_Browse;
	private JMenuItem				jmiTestScript_Create;
	private JMenuItem				jmiTestSuite_Browse;
	private JMenuItem				jmiTestSuite_Create;
	private JMenuItem				jmiTestSuite_RunnViewHistory;
	private JMenuItem				jmiUser_Browse;
	private JMenuItem				jmiUser_Create;
	private JMenuBar				mbMainMenuBar;

	private static ResourceBundle	rbAppBundle			= BIRT_Resources.getRbAppResourceBundle();

	public BIRT_MainWindow()
	{
		try
		{
			// Set cross-platform Java L&F (also called "Metal")
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.javax.swing.plaf.metal");

		}
		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
		}

		initComponents();

	}

	private void initComponents()
	{

		// Main Menu Bar
		initMainMenuBar();

		// Administration Menu
		initAdministrationMenu();

		// DB Connection Menu
		initDBConnectionMenu();

		// Query Menu
		initQueryMenu();

		// TestScript Menu
		initTestScriptMenu();

		// TestCase Menu
		initTestCaseMenu();

		// TestSuite Menu
		initTestSuiteMenu();

		// Project Menu
		initProjectMenu();

		// Add Menus
		addMenus();

		// initialize Main Window
		initMainWindow();

		// Disable Menu - According to User Roles
		initSecurity();

		packNShow();
	}

	private void initSecurity()
	{

		// Disable Menu - According to User Roles

	}

	private void initMainMenuBar()
	{
		// Main Menu Bar
		mbMainMenuBar = new JMenuBar();
	}

	private void initAdministrationMenu()
	{
		// Administration Menu
		jmAdministration = new JMenu();
		jmAdministration.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_ADMINISTRATION));

		// Administration Menu Items
		jmiAppConfiguration = new JMenuItem();
		jmiAppConfiguration.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_ADMINISTRATION_MENUITEM_APP_CONFIGURATION));
		jmiAppConfiguration.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_APP_CONFIG);
		jmiAppConfiguration.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiProject_Create = new JMenuItem();
		jmiProject_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_ADMINISTRATION_MENUITEM_PROJECT_CREATE));

		jmiProject_Delete = new JMenuItem();
		jmiProject_Delete.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_ADMINISTRATION_MENUITEM_PROJECT_DELETE));

		jmiUser_Create = new JMenuItem();
		jmiUser_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_ADMINISTRATION_MENUITEM_USER_CREATE));

		jmiUser_Browse = new JMenuItem();
		jmiUser_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_ADMINISTRATION_MENUITEM_USER_VW_EDT_DEL));

		// Adding Menu Items
		jmAdministration.add(jmiAppConfiguration);
		//jmAdministration.add(jmiProject_Create);
		//jmAdministration.add(jmiProject_Delete);
		//jmAdministration.add(jmiUser_Create);
		//jmAdministration.add(jmiUser_Browse);

	}

	private void initDBConnectionMenu()
	{
		// DB Connection Menu
		jmDBConnection = new JMenu();
		jmDBConnection.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_DBCONNECTION));

		// DB Connection Menu Items
		jmiDBConnection_Create = new JMenuItem();
		jmiDBConnection_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CREATE));
		jmiDBConnection_Create.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_CREATE);
		jmiDBConnection_Create.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiDBConnection_Browse = new JMenuItem();
		jmiDBConnection_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_GENERAL_VIEW_EDIT_DELETE));
		jmiDBConnection_Browse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_BROWSE);
		jmiDBConnection_Browse.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		// Adding Menu Items
		jmDBConnection.add(jmiDBConnection_Create);
		jmDBConnection.add(jmiDBConnection_Browse);

	}

	private void initQueryMenu()
	{
		// Query Menu
		jmQuery = new JMenu();
		jmQuery.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_QUERY));

		// Query Menu Items
		jmiQuery_Create = new JMenuItem();
		jmiQuery_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CREATE));
		jmiQuery_Create.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_CREATE);
		jmiQuery_Create.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiQuery_Browse = new JMenuItem();
		jmiQuery_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_GENERAL_VIEW_EDIT_DELETE));
		jmiQuery_Browse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_BROWSE);
		jmiQuery_Browse.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		// Adding Menu Items
		jmQuery.add(jmiQuery_Create);
		jmQuery.add(jmiQuery_Browse);

	}

	private void initTestScriptMenu()
	{
		// TestScript Menu
		jmTestScript = new JMenu();
		jmTestScript.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_TEST_SCRIPT));

		// TestScript Menu Items

		jmiTestScript_Create = new JMenuItem();
		jmiTestScript_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CREATE));
		jmiTestScript_Create.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_CREATE);
		jmiTestScript_Create.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiTestScript_Browse = new JMenuItem();
		jmiTestScript_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_GENERAL_VIEW_EDIT_DELETE));
		jmiTestScript_Browse.setActionCommand(BIRT_ActionListener.aPP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_BROWSE);
		jmiTestScript_Browse.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());
		// Adding Menu Items

		jmTestScript.add(jmiTestScript_Create);
		jmTestScript.add(jmiTestScript_Browse);
	}

	private void initTestCaseMenu()
	{
		// TestCase Menu
		jmTestCase = new JMenu();
		jmTestCase.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_TEST_CASE));

		// Test Case Menu Items

		jmiTestCase_Create = new JMenuItem();
		jmiTestCase_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CREATE));
		jmiTestCase_Create.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_CREATE);
		jmiTestCase_Create.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiTestCase_Browse = new JMenuItem();
		jmiTestCase_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_GENERAL_VIEW__COPY_EDIT_DELETE));
		jmiTestCase_Browse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_BROWSE);
		jmiTestCase_Browse.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		// Adding Menu Items

		jmTestCase.add(jmiTestCase_Create);
		jmTestCase.add(jmiTestCase_Browse);

	}

	private void initTestSuiteMenu()
	{
		// TestSuite Menu
		jmTestSuite = new JMenu();
		jmTestSuite.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_TEST_SUITE));

		// Test Suite Menu Items

		jmiTestSuite_Create = new JMenuItem();
		jmiTestSuite_Create.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CREATE));
		jmiTestSuite_Create.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_CREATE);
		jmiTestSuite_Create.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiTestSuite_Browse = new JMenuItem();
		jmiTestSuite_Browse.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_GENERAL_VIEW_EDIT_DELETE));
		jmiTestSuite_Browse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSE);
		jmiTestSuite_Browse.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		jmiTestSuite_RunnViewHistory = new JMenuItem();
		jmiTestSuite_RunnViewHistory.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_TEST_SUITE_MENUITEM_RN_VW_HIST));
		jmiTestSuite_RunnViewHistory.setActionCommand(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSERUNNVIEWHSTRY);
		jmiTestSuite_RunnViewHistory.addActionListener(BIRT_ActionListeners.getObjActionListener_MainWindow());

		// Adding Menu Items

		jmTestSuite.add(jmiTestSuite_Create);
		jmTestSuite.add(jmiTestSuite_Browse);
		jmTestSuite.add(jmiTestSuite_RunnViewHistory);

	}

	private void initProjectMenu()
	{
		// Project Menu
		jmProject = new JMenu();
		jmProject.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_PROJECT));

		// Project Menu Items

		jmiProject_View = new JMenuItem();
		jmiProject_View.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_VIEW));

		jmiProject_Edit = new JMenuItem();
		jmiProject_Edit.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_EDIT));

		jmiProject_ChangeProject = new JMenuItem();
		jmiProject_ChangeProject.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_MENU_ADMINISTRATION_MENU_TEST_SUITE_MENUITEM_CHANGE_PRJ));

		jmiExit = new JMenuItem();
		jmiExit.setText(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_EXIT));

		// Adding Menu Items

		jmProject.add(jmiProject_View);
		jmProject.add(jmiProject_Edit);
		jmProject.add(jmiProject_ChangeProject);
		jmProject.add(jmiExit);

	}

	private void addMenus()
	{
		// Add Menu to Main MenuBar
		mbMainMenuBar.add(jmAdministration);
		mbMainMenuBar.add(jmDBConnection);
		mbMainMenuBar.add(jmQuery);
		mbMainMenuBar.add(jmTestScript);
		mbMainMenuBar.add(jmTestCase);
		mbMainMenuBar.add(jmTestSuite);
		//mbMainMenuBar.add(jmProject);

		setJMenuBar(mbMainMenuBar);

	}

	private void initMainWindow()
	{
		setMainWindowDefaultBehaviour();
		setMainWindowLayout();
	}

	private void packNShow()
	{
		pack();
		this.repaint();
		setVisible(true);
	}

	private void setMainWindowLayout()
	{
		setLayout(new FlowLayout());

	}

	private void setMainWindowDefaultBehaviour()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(Screensize);
		setTitle(rbAppBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_FRAMES_WINDOW_APP_TITLE));
	}

	public void resetNAdd(JPanel objPanelObject)
	{
		getContentPane().removeAll();
		getContentPane().add(objPanelObject);
		packNShow();
	}

	public void reset()
	{
		BIRT_Panels.destroyPanels();
		getContentPane().removeAll();
		packNShow();
	}

	public void notifyUserOfError(String strErrorMessage)
	{
		JOptionPane.showMessageDialog(this, strErrorMessage, BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_ERROR), JOptionPane.ERROR_MESSAGE);
	}

	public void notifyUserOfFatalError(String strErrorMessage)
	{
		JOptionPane.showMessageDialog(this, strErrorMessage, BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_ERROR), JOptionPane.ERROR_MESSAGE);
		reset();
	}

	public void notifyUserOfSuccess(String strSuccessMessage)
	{
		JOptionPane.showMessageDialog(this, strSuccessMessage, BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_SUCCESS),
				JOptionPane.INFORMATION_MESSAGE);
		reset();
	}

	public boolean getUserConfirmation(String strConfirmMessage)
	{
		int response = JOptionPane.showConfirmDialog(this, strConfirmMessage, BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CONFIRM),
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION)
			return true;
		return false;
	}

}

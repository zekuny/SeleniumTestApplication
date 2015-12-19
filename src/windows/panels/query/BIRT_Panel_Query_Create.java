package windows.panels.query;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import resources.resourcebundle.BIRT_Resource_PropertyNames;
import windows.panels.BIRT_Panel;

import actionlistener.BIRT_ActionListener;

import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Resources;

public class BIRT_Panel_Query_Create extends BIRT_Panel
{

	private static final long serialVersionUID = 1L;
	
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlHeader;
    private javax.swing.JLabel jlQuery;
    private javax.swing.JLabel jlQueryDescription;
    private javax.swing.JLabel jlQueryName;
    private javax.swing.JSeparator jsHeaderSeparator;
    private javax.swing.JScrollPane jspQuery;
    private javax.swing.JScrollPane jspQueryDescription;
    private javax.swing.JTextField jtQueryName;
    private javax.swing.JTextArea jtaQuery;
    private javax.swing.JTextArea jtaQueryDescription;
	
                        
    protected void initHeader()
	{
    	jlHeader = new javax.swing.JLabel();
    	jsHeaderSeparator = new javax.swing.JSeparator();
 	}

	protected void initPanelComponents()
	{

		jlQueryName = new javax.swing.JLabel();
		
		jtQueryName = new javax.swing.JTextField();
		
        jlQueryDescription = new javax.swing.JLabel();
        
        jtaQueryDescription = new javax.swing.JTextArea();
        jtaQueryDescription.setColumns(20);
        jtaQueryDescription.setRows(5);
        
        jspQueryDescription = new javax.swing.JScrollPane();
        jspQueryDescription.setViewportView(jtaQueryDescription);
        
        jlQuery = new javax.swing.JLabel();
        
        jtaQuery = new javax.swing.JTextArea();
        jtaQuery.setColumns(20);
        jtaQuery.setRows(5);
        
        jspQuery = new javax.swing.JScrollPane();
        jspQuery.setViewportView(jtaQuery);
        
        jbSave = new javax.swing.JButton();
        
        jbCancel = new javax.swing.JButton();
		
	}

	protected void setPanelComponentText()
	{
		jlHeader.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_CREATEQUERY));
		
		jlQueryName.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_QUERYNAME));
		
	    jlQueryDescription.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_QUERYDESCRIPTION));
	    
	    jlQuery.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_QUERY));
        
	    jbSave.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_SAVE));
	    
	    jbCancel.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CANCEL));
	}

	protected void setPanelComponentActionCommands()
	{
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_CREATE_SAVE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_CREATE_CANCEL);
		
	}

	protected void addPanelComponentActionListeners()
	{
		ActionListener objActionListener = BIRT_ActionListeners.getObjActionListener_Panel_Query_Create();
		jbSave.addActionListener(objActionListener);
		jbCancel.addActionListener(objActionListener);
		
	}

	public void resetContents()
	{
		jtQueryName.setText(BIRT_DataObject.NULL);
		jtaQueryDescription.setText(BIRT_DataObject.NULL);
		jtaQuery.setText(BIRT_DataObject.NULL);
	
	}

	protected void setLayout()
	{
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlQueryDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jspQueryDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlQueryName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtQueryName, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlQuery)
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbSave)
                                    .addComponent(jspQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jlHeader)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jlHeader)
                .addGap(18, 18, 18)
                .addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlQueryName)
                    .addComponent(jtQueryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlQueryDescription)
                    .addComponent(jspQueryDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlQuery)
                    .addComponent(jspQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSave)
                    .addComponent(jbCancel))
                .addContainerGap())
        );

		
	}

	 public String getQueryName()
	 {
		 return jtQueryName.getText();
	 }
	 
	 public String getQueryDescription()
	 {
		 return jtaQueryDescription.getText();
	 }
	 
	 public String getQuery()
	 {
		 return jtaQuery.getText();
	 }

	public void updateContents()
	{
		
		
	}

	protected boolean isFieldsProper()
	{
		ResourceBundle objcurrentResourceBundle= BIRT_Resources.getRbAppResourceBundle();
		if(getQueryName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError(objcurrentResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_CREATE_QUERY_NAME_NULL));
		else if (getQueryDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError(objcurrentResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_CREATE_QUERY_DESC_NULL));
		else if (getQuery().equals(BIRT_DataObject.NULL))
			return handleFieldProperError(objcurrentResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_CREATE_QUERY_NULL));
		return true;
	}

               

}

package exception;

public abstract class BIRT_Exception extends Exception
{
	private static final long serialVersionUID = 1L;
	protected String strDisplayableMessage;
	
	public BIRT_Exception(String strDisplayableMessage)
	{
		this.strDisplayableMessage=strDisplayableMessage;
	}
	
	public String getDisplayableMessage()
	{
		return this.strDisplayableMessage;
	}

}

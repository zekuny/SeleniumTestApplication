package exception.persistence;

import exception.BIRT_Exception;

public class BIRT_QueryNotFoundException extends BIRT_Exception
{
	
	private static final long serialVersionUID = 1L;

	public BIRT_QueryNotFoundException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

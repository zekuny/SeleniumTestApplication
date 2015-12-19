package exception.persistence;

import exception.BIRT_Exception;

public class BIRT_ImproperQueryUpdateException extends BIRT_Exception
{
	
	private static final long serialVersionUID = 1L;

	public BIRT_ImproperQueryUpdateException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

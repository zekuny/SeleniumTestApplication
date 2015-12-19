package exception.windows.panels.query;

import exception.BIRT_Exception;

public class BIRT_DuplicateQueryNameException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_DuplicateQueryNameException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

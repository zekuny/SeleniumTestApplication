package exception.windows.panels;

import exception.BIRT_Exception;

public class BIRT_FieldErrorException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_FieldErrorException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

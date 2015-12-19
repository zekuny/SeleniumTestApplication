package exception.utility.file;

import exception.BIRT_Exception;

public class BIRT_NotValidExtensionException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_NotValidExtensionException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

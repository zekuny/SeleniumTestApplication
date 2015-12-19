package exception.utility.file;

import exception.BIRT_Exception;

public class BIRT_NotDirectoryException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_NotDirectoryException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

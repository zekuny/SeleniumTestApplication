package exception.utility.file;

import exception.BIRT_Exception;

public class BIRT_NotFileException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_NotFileException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

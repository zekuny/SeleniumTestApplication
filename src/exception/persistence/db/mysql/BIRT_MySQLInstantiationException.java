package exception.persistence.db.mysql;

import exception.BIRT_Exception;

public class BIRT_MySQLInstantiationException extends BIRT_Exception
{

	private static final long serialVersionUID = 1L;

	public BIRT_MySQLInstantiationException(String strDisplayableMessage)
	{
		super(strDisplayableMessage);
	}

}

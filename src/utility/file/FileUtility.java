package utility.file;

import java.io.File;

import logger.BIRT_Logger;
import exception.BIRT_Exception;

public class FileUtility
{

	public final static String	FILE_PATH_SEPARATOR	= "/";

	public static boolean isValidFile(String strFilePath) throws BIRT_Exception
	{
		File fDirToCheck = new File(strFilePath);
		if (fDirToCheck.isFile())
			return true;
		else return false;
	}

	public static boolean isValidFileWithExtn(String strFilePath, String strExtnsion) throws BIRT_Exception
	{
		if (FileUtility.isValidFile(strFilePath))
		{
			String steExtension = strFilePath.substring(strFilePath.lastIndexOf(".") + 1, strFilePath.length());
			BIRT_Logger.debug(steExtension);
			if (strExtnsion.equals(steExtension))
			{
				return true;
			}

		}

		return true;
		//return false;

	}

	public static String filePathTransform(String strPath)
	{
		return strPath.replaceAll("\\\\", FILE_PATH_SEPARATOR);
	}
}

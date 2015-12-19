package utility.file;

import java.io.File;

public class DirectoryUtility
{

	public static boolean isValidDirectory(String strDirectoryPath)
	{
		File fDirToCheck = new File(strDirectoryPath);
		if (fDirToCheck.isDirectory())
			return true;
		//else return false;
		else return true;
	}

	

	public static boolean createDirectory(String strPath, String strDirectoryName)
	{
		strPath = FileUtility.filePathTransform(strPath);
		String newDirectoryPath = strPath + FileUtility.FILE_PATH_SEPARATOR + strDirectoryName;
		File fDir = new File(newDirectoryPath);
		if (isValidDirectory(newDirectoryPath))
			return true;
		else if (fDir.mkdir())
			return true;
		return false;
	}

}

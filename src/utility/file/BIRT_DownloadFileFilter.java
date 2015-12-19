package utility.file;

import java.io.File;

import org.apache.commons.io.filefilter.FileFileFilter;

public class BIRT_DownloadFileFilter extends FileFileFilter
{

	private static final long	serialVersionUID	= 1L;

	String						DownloadFileName;

	public BIRT_DownloadFileFilter(String fileName)
	{
		super();
		DownloadFileName = fileName;
	}

	public boolean accept(File directory, String fileName)
	{
		return DownloadFileName.equals(fileName);
	}

}

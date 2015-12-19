package utility.file;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class FileFinder {

	public static File[] search(File directory, String regex) {
		if (!directory.isDirectory()) {
			// System.out.println(" is no directory.");
			throw new IllegalArgumentException(directory + " is no directory.");

		}

		final Pattern pattern = Pattern.compile(regex); // Caution: could also
														// throw an exception!
		// System.out.println("pattern " + pattern);
		return directory.listFiles(new FileFilter() {
			int count = 0;

			public boolean accept(File file) {
				System.out.println("---" + count++ + "---");
				System.out.println("file name " + file.getName());
				return pattern.matcher(file.getName()).matches();
			}
		});
	}

}

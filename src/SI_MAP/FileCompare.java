package SI_MAP;


import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

public class FileCompare {
	
	public boolean Compare(Path file1,Path file2) throws IOException {

		
		boolean compare = FileUtils.contentEquals(file1.toFile(), file2.toFile());
		
		return compare;
		
		

	}

}

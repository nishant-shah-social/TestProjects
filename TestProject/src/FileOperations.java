
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperations {
	
	public void writeToFile(String folderSetName, int fileCount) throws IOException {
		Long currentMs;
		File dir = new File(folderSetName);
		if(!dir.exists()) {
			dir.mkdir();
		}
		for(int i=0 ; i<fileCount ; i++) {
			currentMs = System.currentTimeMillis();
			FileOutputStream msFile = new FileOutputStream(new File(folderSetName+"/ms"+i+".txt"));
			msFile.write(currentMs.toString().getBytes());
			msFile.close();
	}
	}


}

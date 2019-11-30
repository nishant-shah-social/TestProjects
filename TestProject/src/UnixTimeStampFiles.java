import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnixTimeStampFiles {

	//Generate file every unix timestamp second, containing that particular timestamp
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		FileOperations fo = new FileOperations();
		fo.writeToFile("Files/Set1", 60);
		fo.writeToFile("Files/Set2", 60);
		fo.writeToFile("Files/Set3", 60);

		Gzip gz = new Gzip();
		gz.createtar("Files/Set1");
		
		gz.gzipFile("Files/Set1.tar", "Files/Set1.tar.gz");
		
		List<Long> msList = gz.readGzipFile("Files/Set1.tar.gz");
		Long sum = (long) 0;
		for(Long ms : msList) {
			
			sum+=ms;
			
		}
		
		System.out.println("The average of time is :- " + sum/msList.size());

}

	
}

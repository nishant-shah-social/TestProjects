import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Gzip {
	
	String tarPath = "";
	
	public void createtar(String parentFolder) throws IOException {
		
		File file = new File(parentFolder);
		tarPath = new File (file.getParent()).getAbsolutePath();
		
		String fileName = file.getName();
		String parentOfFile = file.getParent();
		
		String destinationPath = parentOfFile + File.separator + fileName + ".tar";
		
		TarArchiveOutputStream tos = new TarArchiveOutputStream(new FileOutputStream(new File(destinationPath)));
		archiveFiles(file, tos);
	}

	private void archiveFiles(File file, TarArchiveOutputStream tos) throws IOException {
		
		File[] files = file.listFiles();
		
		for(File f : files) {
			TarArchiveEntry newEntry = new TarArchiveEntry(f.getName());
			newEntry.setSize(f.length());
			tos.putArchiveEntry(newEntry);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			
			byte byteData[]= new byte[(int) f.length()];
			while(bis.read(byteData)!= -1) {
				tos.write(byteData);
			}
			
			bis.close();
			tos.closeArchiveEntry();
		}
			
	}
	
	 public void gzipFile(String source_filepath, String destinaton_zip_filepath) {
		 
		 			
	        byte[] buffer = new byte[1024];
	 
	        try {
	             
	            FileOutputStream fileOutputStream =new FileOutputStream(new File(destinaton_zip_filepath).getAbsolutePath());
	 
	            GZIPOutputStream gzipOuputStream = new GZIPOutputStream(fileOutputStream);
	 
	            FileInputStream fileInput = new FileInputStream(source_filepath);
	 
	            int bytes_read;
	             
	            while ((bytes_read = fileInput.read(buffer)) > 0) {
	                gzipOuputStream.write(buffer, 0, bytes_read);
	            }
	 
	            fileInput.close();
	 
	            gzipOuputStream.finish();
	            gzipOuputStream.close();
	 
	            System.out.println("The file was compressed successfully!");
	 
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	 
	 public List<Long> readGzipFile(String filePath) throws IOException{
		 		
		 		List<Long> msList = new ArrayList<Long>();
		 		
		 		File file = new File(filePath);
				TarArchiveInputStream tarInput = new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(file.getAbsolutePath())));
				TarArchiveEntry currentEntry = tarInput.getNextTarEntry();
				BufferedReader br = null;
				while (currentEntry != null) {
				    br = new BufferedReader(new InputStreamReader(tarInput)); // Read directly from tarInput
				    System.out.println("For File = " + currentEntry.getName());
				    String line;
				    while ((line = br.readLine()) != null) {
				    	msList.add(Long.parseLong(line));
				        System.out.println("line="+line);
				    }
				    currentEntry = tarInput.getNextTarEntry(); // You forgot to iterate to the next file
				}
				tarInput.close();
				return msList;
	 }
}

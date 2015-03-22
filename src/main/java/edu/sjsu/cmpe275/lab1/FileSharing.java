
package edu.sjsu.cmpe275.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FileSharing implements IFileService {
	public static HashMap<String, HashSet<String>> Users ;//To keep track of users and file service
	public FileSharing() {
		Users = new HashMap<String, HashSet<String>>();
		Users.put("Alice", new HashSet<String>());
		Users.put("Bob", new HashSet<String>());
		Users.put("Carl", new HashSet<String>());
	}

	//For Sharing a File
	public void shareFile(String userId, String targetUserID, String filePath) throws Exception{
		Users.get(targetUserID).add(filePath);
	}

	//For Unsharing a file
	public void unshareFile(String userId, String targetUserID, String filePath) throws Exception {
		Users.get(targetUserID).remove(filePath);
	}

	//Reading a text file
	public byte[] readFile(String userId, String filePath) throws Exception {

		File file = new File(filePath);
		byte fileContent[] = new byte[(int)file.length()];//byte array to return the content of the file

		FileInputStream fin = null;

		try {

			fin = new FileInputStream(file);//File InputStream to read the content of the text file
			fin.read(fileContent);

			String s = new String(fileContent);//Storing the content of the file 

			System.out.println("File content: " + s);//Displaying the content of the file to console

		}
		catch (FileNotFoundException e) {

			System.out.println("File not found" + e);//Text File Exception

		}
		catch (IOException ioe) {

			System.out.println("Exception while reading file " + ioe);// Text file reading exception
		}
		return fileContent;
	}

}

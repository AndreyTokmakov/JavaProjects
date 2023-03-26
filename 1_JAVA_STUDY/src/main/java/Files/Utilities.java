//============================================================================
// Name        : Utiliteis.java
// Created on  : December 05, 2018
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Utilities test class
//============================================================================

package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/** @author a.tokmakov  **/
/** @class  Utilities.  **/
public class Utilities {
	/** StringWriter instance: **/
	private final static StringWriter stringWriter = new StringWriter();
	/** PrintWriter instance: **/
	private final static PrintWriter printWriter = new PrintWriter(stringWriter);
    
	// TODO: Add comments for method description
	/** List files : 
	 * @throws IOException **/
	public static String stackTraceToString(final Exception exception) {
	    exception.printStackTrace(printWriter);
	    return stringWriter.toString();
	}
	
	// TODO: Add comments for method description
	/** List files : 
	 * @throws IOException **/
	public static String stackTraceToString(final Throwable throwable) {
		throwable.printStackTrace(printWriter);
	    return stringWriter.toString();
	}	
	
	// TODO: Add comments for method description
	/** List files : 
	 * @throws IOException **/
	public static List<String> GetFileList(final String dirPath, 
									       boolean recursive) throws IOException {
		 File dir = new File(dirPath);  
		 ArrayList<String> files = new ArrayList<String>();
		 ListFiles(dir, files, recursive);
		 return files;
	}

	// TODO: Add comments for method description
	/** List files : 
	 * @throws IOException **/
	public static void ListFiles(final File folder,
								 ArrayList<String> fileList, 
								 boolean recursive) throws IOException {
	    for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
	        if (fileEntry.isDirectory()) {
	        	if (recursive) {
	        		ListFiles(fileEntry, fileList, recursive);
	        	}
	        } else {
	        	//System.out.println(fileEntry.getAbsolutePath());
	        	//fileList.add(fileEntry.getAbsolutePath());
	        	fileList.add(fileEntry.getPath());
	        }
	    }
	}
	
	// TODO: Add comments for method description
	/** List files : 
	 * @throws IOException **/
	public List<String> GetFileLines(final String fileName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		try (Stream<String> linesStream = Files.lines(new File(fileName).toPath())) {
			linesStream.forEach(lines::add);
		}
		return lines;
	}
	
	// TODO: Add comments for method description
	/** List files : 
	 * @return 
	 * @throws IOException **/	
	public ArrayList<String> GetFileLinesEx(final String fileName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} return lines;
	}
	
	// TODO: Add comments for method description
	/** readFileToString : 
	 * @return 
	 * @throws IOException **/	
	public static String readFileToString(final String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		return IOUtils.toString(fileReader);
	}
	
	// TODO: Add comments for method description
	/** readFileToString : 
	 * @return 
	 * @throws IOException **/	
	public static String resolveAndReadFileToString(final String fileName) throws IOException {
		FileReader fileReader = new FileReader(resolveString(fileName));
		return IOUtils.toString(fileReader);
	}
	
	// TODO: Add comments for method description
	/** readFileLines : 
	 * @return 
	 * @throws IOException **/	
	public static List<String> readFileLines(final String fileName) throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		return IOUtils.readLines(stream, "UTF-8");
	}
	
    /**
     * Resolves the input String.
     * Evaluates all contained system environment variables; replace
     * them all with correspond values.
     * 
	 * @param  str The input string to be evaluated.
	 * @return String with all variables replaced by their value
     * @throws NullPointerException if the specified input string is null
     */
	public static String resolveString(final String str) {
		String resolvedString = str;
		final Map<String,String> sysVars = System.getenv();
		for (Entry<String, String> entry : sysVars.entrySet()) 
			resolvedString = resolvedString.replace("%" + entry.getKey() + "%", entry.getValue());
		return resolvedString;
	}
}

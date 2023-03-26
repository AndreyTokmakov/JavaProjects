//============================================================================
// Name        : DirectoryFilesLister.java
// Created on  : April 04, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Directory files lister class
//============================================================================

package Directory_Listing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/** DirectoryFilesLister class: **/
public class DirectoryFilesLister {
	/** Browser folders list:**/
	protected List<String> browserFolders = new ArrayList<String>();
	/** Chromium folders list:**/
	protected List<String> chromiumFolders = new ArrayList<String>();	

    public void listFilesInDirectory(final File folder, List<File> filesList) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            //filesList.add(fileEntry);
            if (fileEntry.isDirectory()) {
                    filesList.add(fileEntry);
                    listFilesInDirectory(fileEntry, filesList);
            } else {
                //System.out.println(fileEntry.getName());
            }
        }
    }

    public List<File> listFilesInDirectory(final String dirPath) {
            List<File> list = new ArrayList<File>();
            listFilesInDirectory(new File(dirPath), list);
            return list;
    }

	public void GetBrowserAndChromiumFolders() {
		final String browserDirPath = "R:\\Projects\\browser\\src";
		final String chromiumDirPath = "R:\\Projects\\chromium\\src";
        for (File file : listFilesInDirectory(browserDirPath)) {
    		String path = file.getAbsolutePath().replace(browserDirPath, "");
    		browserFolders.add(path);      
    	}
    	for (File file : listFilesInDirectory(chromiumDirPath)) {
    		String path = file.getAbsolutePath().replace(chromiumDirPath, "");
    		chromiumFolders.add(path);      
    	}
    }
    
    public void CompareBrowserAndChromiumFolders()
    {
    	Consumer<String> printer = (String value) -> System.out.println(value);	
    	Predicate<String> filter = (String value) -> (value.contains(".git") || 
    												  value.contains("out\\Debug") ||
    												  value.contains("out\\Debug2") || 
    												  value.contains("out\\Release"));
    	
        System.out.println(browserFolders.size());
        System.out.println(chromiumFolders.size());

        browserFolders.removeIf(filter);
        chromiumFolders.removeIf(filter);
        
        System.out.println();
        System.out.println(browserFolders.size());
        System.out.println(chromiumFolders.size());
        
        chromiumFolders.removeAll(browserFolders);
        System.out.println(chromiumFolders.size());
        
        chromiumFolders.forEach(printer);
    }


    ///////////////////////////////////////////////////////////



    public void listFilesForFolder1(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (true == fileEntry.isDirectory()) {
                    System.out.println(fileEntry.getAbsolutePath());
                listFilesForFolder1(fileEntry);
            } else {
                //System.out.println(fileEntry.getName());
            }
        }
    }

    public void TestDi1r() {
            final File folder = new File("C:/Temp");
            listFilesForFolder1(folder);
    }

    public void ListFiles(final File folder) throws IOException {
            try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
                paths.filter(Files::isRegularFile).forEach(System.out::println);
            } 
    }

    public void ListDirectoryFiles_1(final String dirPath) throws IOException {
            Files.list(Paths.get(dirPath)).forEach(System.out::println);
    }

    // List only files inside directory using filter expression
    public void ListDirectoryFiles_2(final String dirPath) throws IOException {
            Files.list(Paths.get(dirPath)).filter(Files::isRegularFile)		
                                                                      .forEach(System.out::println);
    }

    public void ListDirectoryFiles_3(final String dirPath) throws IOException {
            Files.newDirectoryStream(Paths.get(dirPath)).forEach(System.out::println);
    }

    //  List only files with Files.newDirectoryStream()
    public void ListDirectoryFiles_4(final String dirPath) throws IOException {
            Files.newDirectoryStream(Paths.get(dirPath), path -> path.toFile().isFile()).forEach(System.out::println);
    }

    // List files of certain extention with
    public void ListDirectoryFiles_5(final String dirPath, final String ext) throws IOException {
            Files.newDirectoryStream(Paths.get(dirPath),path -> path.toString().endsWith(ext)).forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException
    {
    	//String atomTestRunLogs = "C:\\Temp\\11111\\atom_unit_tests_all.log";
    	// String chromeTestRunLogs = "C:\\Temp\\11111\\trace.log";

    	// List<String> atomLogs = readFileInList2(atomTestRunLogs);
    	// List<String> chromeLogs = readFileInList2(chromeTestRunLogs);

        // System.out.println(atomLogs.size());
        // System.out.println(chromeLogs.size());

        final String tempDir = "C:/Temp";
        DirectoryFilesLister fileLister = new DirectoryFilesLister();

        fileLister.ListDirectoryFiles_1(tempDir);
        //fileLister.ListDirectoryFiles_2(tempDir);
        //fileLister.ListDirectoryFiles_3(tempDir);
        //fileLister.ListDirectoryFiles_4(tempDir);
        //fileLister.ListDirectoryFiles_5(tempDir, ".jpg");

        //TestDi1r();
        
        // fileLister.GetBrowserAndChromiumFolders();
        // fileLister.CompareBrowserAndChromiumFolders();
    }
}
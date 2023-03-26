/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* FilenameFilter tests class
*
* @name    : FilenameFilter_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 22, 2020
****************************************************************************/ 

package Files;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilter_Tests 
{
	static class MyFileNameFilter implements FilenameFilter { 
        private String ext;
         
        public MyFileNameFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
	}
	
    private static void findFiles(String dir, String ext) {
        File file = new File(dir);
        if (false == file.exists()) {
        	System.out.println(dir + " folder do not exists.");
        	return;
        }
        
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if (listFiles.length == 0){
            System.out.println(dir + " do not contains files with extenstion " + ext);
        } else{
            for (File f : listFiles)
            	System.out.println("File: " + dir + File.separator + f.getName());
        }
    }
	
	public static void main(String[] args) 
	{
		String dir = "S:\\Temp\\Folder_For_Testing\\XML_Files";
		String ext = ".json";
		findFiles(dir, ext);

	}
}
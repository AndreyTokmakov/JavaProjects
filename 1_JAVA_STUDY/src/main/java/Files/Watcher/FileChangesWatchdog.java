package Files.Watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import Files.Utilities;

public class FileChangesWatchdog {
	/* */
	private final static String prefFileName = "Preferences";
	/* */
	private final static String prefFilePath = "S:\\Temp\\Folder_For_Testing\\Monitor";
	/* */
	private static final String preferencesFileDir = Utilities.resolveString(FileChangesWatchdog.prefFilePath);

	/**************************************************************************
	 * @class WatchDog.
	 * 
	 * TODO: Add description
	 *
	 *************************************************************************/
	private class WatchDog implements Runnable {

		/** WatchDog class constructor. **/
		protected WatchDog() {
			// TODO
		}

		@Override
		public void run() {
			final Path direcoty = Paths.get(FileChangesWatchdog.preferencesFileDir);
			WatchService watcher = null;
			try {
				watcher = FileSystems.getDefault().newWatchService();
				direcoty.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
					  	   				   StandardWatchEventKinds.ENTRY_DELETE, 
					  	   				   StandardWatchEventKinds.ENTRY_MODIFY);
			} catch (final IOException exc) {
				// TODO: Add logging
				exc.printStackTrace();
			}
			
			System.out.println("Watch Service registered for dir: " + direcoty.getFileName());
	        while (true) {
	            WatchKey key;
	            try {
	                key = watcher.take();
	            } catch (InterruptedException ex) {
	                return;
	            }
	             
	            for (final WatchEvent<?> event : key.pollEvents()) {
	                WatchEvent.Kind<?> kind = event.kind();
	                
	                // This key is registered only for ENTRY_CREATE events, but an 
	                // OVERFLOW event can occur regardless if events are lost or discarded.
	                if (kind == StandardWatchEventKinds.OVERFLOW) {
	                    continue;
	                }
	                
	                @SuppressWarnings("unchecked")
	                WatchEvent<Path> ev = (WatchEvent<Path>) event;
	                Path fileName = ev.context();
	                 
	                // System.out.println(kind.name() + ": " + fileName);
	                 
	                if (kind == StandardWatchEventKinds.ENTRY_MODIFY && fileName.toString().equals(FileChangesWatchdog.prefFileName)) {
	                    System.out.println("Preferences has been modified");
	                }
	            }
	            
	            // Reset the key -- this step is critical if you want to
	            // receive further watch events.  If the key is no longer valid,
	            // the directory is inaccessible so exit the loop.
	            boolean valid = key.reset();
	            if (!valid) {
	                break;
	            }
	        }
		}
	}
	
	public void WatchFileChanges() {
		Thread thread = new Thread(new WatchDog());
		thread.start();
	}
	
	public static void main(String[] args) throws Exception {
		FileChangesWatchdog prefs = new FileChangesWatchdog();
		prefs.WatchFileChanges();
	}
}

package Files.Watcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Files.Utilities;

class FileWatcherEx extends Thread {
    private final File file;
    private AtomicBoolean stop = new AtomicBoolean(false);

    public FileWatcherEx(final File file) {
        this.file = file;
    }

    public boolean isStopped() { 
    	return stop.get(); 
    }
    
    public void stopThread() { 
    	stop.set(true); 
    }

    public void doOnChange() {
        // Do whatever action you want here
    }

    @Override
    public void run() {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = file.toPath().getParent();
            path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
            while (!isStopped()) {
                WatchKey key;
                try { 
                	key = watcher.poll(25, TimeUnit.MILLISECONDS); 
                } catch (final InterruptedException exc) { 
                	System.err.println(exc);
                	return; 
                }
                if (key == null) { 
                	Thread.yield(); 
                	continue; 
                }

                for (final WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        Thread.yield();
                        continue;
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY
                            && filename.toString().equals(file.getName())) {
                        doOnChange();
                    }
                    boolean valid = key.reset();
                    if (!valid) { break; }
                }
                Thread.yield();
            }
        } catch (Throwable e) {
            // Log or rethrow the error
        }
    }
}

public class Files_MonitorFileChanges {
	/* */
	private final static String prefFilePath = "%LOCALAPPDATA%\\Mail.Ru\\Atom\\User Data\\Default\\Preferences";
	
	/**
	 * @throws Exception **/
	public void readPreferencesFile() throws Exception {
		
		String jsopPrefs = Utilities.resolveAndReadFileToString(prefFilePath);
		
		JSONObject jsonMessage = null;
		try {
			jsonMessage = (JSONObject) (new JSONParser().parse(jsopPrefs));
		} catch (ParseException exc) {
			throw new Exception(exc);
		} 
		
		JSONObject web_apps = (JSONObject)jsonMessage.get("web_apps");
		System.out.println(web_apps);
	}
	
	public void WatchFileChanges() throws IOException {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		
		final Path direcoty = Paths.get("R:\\Temp\\FILES");
		direcoty.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
							  	   StandardWatchEventKinds.ENTRY_DELETE, 
							  	   StandardWatchEventKinds.ENTRY_MODIFY);
		
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
                 
                System.out.println(kind.name() + ": " + fileName);
                 
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY && fileName.toString().equals("Preferences")) {
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

	/**************************************************************************
	 *  MAIN
	 * @throws Exception 
	**************************************************************************/
	public static void main(String[] args) throws Exception {
		Files_MonitorFileChanges fileWatcher = new Files_MonitorFileChanges();
		
		// fileWatcher.readPreferencesFile();
		fileWatcher.WatchFileChanges();
	}
}

package markphd.org.android_tlx.util;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * A simple logger for participant data. Log files are written to the
 * root of the storage directory. Filenames take the following format:
 * 'P[id][descriptor].csv', where participant id and a descriptor are
 * passed to the constructor. If a null descriptor is passed, this
 * argument is omitted from the filename.
 * 
 * @author Euan Freeman
 */
public class ParticipantDataLog {
	private static final String LOG_SUFFIX = ".csv";
	private static final String LOG_PREFIX = "P";
	public static String TLX_DIR = "grahams-likert-logs";
	
	private String filename;
	private String dirname;
	private File file;
	private File dir;
	private File tlxDir;

	public ParticipantDataLog(String participant, String fileDescriptor) {
		filename = String.format("%s%s-%s%s",
			LOG_PREFIX, participant, fileDescriptor == null ? "" : fileDescriptor, LOG_SUFFIX);
		
		dirname = String.format("P%s", participant);
	}
	 
	/**
	 * Open the file for this participant's data to be written to,
	 * creating a directory to store files in if required.
	 */
	private void open() {
		// 1. Check if TLX directory exists. If it doesn't then create it.
		
		try {
			if (FileUtils.directoryExists(TLX_DIR)) {
				tlxDir = FileUtils.getFile(TLX_DIR);
			} else {
				if (FileUtils.createDirectory(TLX_DIR)) {
					tlxDir = FileUtils.getFile(TLX_DIR);
				}
			}
		} catch (StorageUnavailableException e) {
			e.printStackTrace();
		}
		
		if (tlxDir == null)
			return;
		
		// 2. Check if the directory for this participant exists. If
		//    it doesn't then create it.
		try {
			if (FileUtils.directoryExists(tlxDir, dirname)) {
				dir = FileUtils.getFile(tlxDir, dirname);
			} else {
				if (FileUtils.createDirectory(tlxDir, dirname)) {
					dir = FileUtils.getFile(tlxDir, dirname);
				}
			}
		} catch (StorageUnavailableException e) {
			e.printStackTrace();
		}
		
		if (dir == null)
			return;
		
		// 3. Finally, get the file for this participant's data.
		file = FileUtils.getFile(dir, filename);
	}
	
	/**
	 * Write the given message to the log file.
	 */
	public void write(String message) {
		if (file == null)
			open();
		Log.v("Write", "Write called");
		try {
			FileWriter fw = new FileWriter(file, true);
			 String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			fw.append(mydate + "," + message + "\n");
			fw.flush();
			fw.close();
			Log.v("Write", "Write done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			android.util.Log.i("ParticipantDataLog", message);
		}
	}
}
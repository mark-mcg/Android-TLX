package markphd.org.android_tlx.util;

import android.os.Environment;

import java.io.File;

/**
 * This class provides some basic file utilities.
 * 
 * @author Euan Freeman
 */
public class FileUtils {
	/**
	 * Returns the root of the external storage directory.
	 * 
	 * @return
	 * 		File representing the external storage root.
	 * 
	 * @throws StorageUnavailableException
	 * 		Thrown if storage either unavailable or is only available in read-only mode.
	 */
	public static File getStorageDirectory() throws StorageUnavailableException {
		String state = Environment.getExternalStorageState();

		boolean available = false;
		boolean writeable = false;

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			available = true;
			writeable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			throw new StorageUnavailableException("External storage is only available in read-only mode.");
		} else {
			throw new StorageUnavailableException("External storage is unavailable.");
		}
		
		if (!available && !writeable) {
			return null;
		}
		
		File storage = Environment.getExternalStorageDirectory();
		
		return storage;
	}
	
	/**
	 * Returns the requested file from external storage, if available.
	 * 
	 * @param filename
	 * 		Name of the file being requested.
	 * 
	 * @return
	 * 		File.
	 * 
	 * @throws StorageUnavailableException
	 * 		Thrown if storage is either unavailable or is only available in read-only mode.
	 */
	public static File getFile(String filename) throws StorageUnavailableException {
		File storage = getStorageDirectory();
		
		return new File(storage, filename);
	}
	
	/**
	 * Returns the requested file from the given parent directory.
	 */
	public static File getFile(File parent, String filename) {
		return new File(parent, filename);
	}
	
	/**
	 * Checks if a directory with the given name exists.
	 */
	public static boolean directoryExists(String filename) throws StorageUnavailableException {
		File file = getFile(filename);
		
		return file.exists() && file.isDirectory();
	}
	
	/**
	 * Checks if a directory with the given name exists.
	 */
	public static boolean directoryExists(File parent, String filename) throws StorageUnavailableException {
		File file = getFile(parent, filename);
		
		return file.exists() && file.isDirectory();
	}
	
	/**
	 * Checks if a file with the given name exists.
	 */
	public static boolean fileExists(String filename) throws StorageUnavailableException {
		File file = getFile(filename);
		
		return file.exists() && file.isFile();
	}
	
	/**
	 * Checks if a file with the given name exists.
	 */
	public static boolean fileExists(File parent, String filename) throws StorageUnavailableException {
		File file = getFile(parent, filename);
		
		return file.exists() && file.isFile();
	}
	
	/**
	 * Creates a directory, and any required parent directories, with the given name.
	 */
	public static boolean createDirectory(String name) throws StorageUnavailableException {
		File storage = getStorageDirectory();
		
		return createDirectory(storage, name);
	}
	
	/**
	 * Creates a directory, and any required parent directories, with the given name.
	 */
	public static boolean createDirectory(File parent, String name) throws StorageUnavailableException {
		File newDir = new File(parent, name);
		return newDir.mkdirs();
	}
}

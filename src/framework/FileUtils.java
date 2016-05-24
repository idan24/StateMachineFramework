package framework;
import java.io.*;

public class FileUtils {
	public static Object loadEntityFromFile(String filePath) {
		Object entity;
		
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        entity = in.readObject();
	        in.close();
	        fileIn.close();
		}
		catch(IOException i) {
			entity = null;
		}
		catch(ClassNotFoundException c) {
			entity = null;
		}
		
		return entity;
	}
	
	public static boolean saveEntityToFile(String filePath, Object entity) {
		try {
			File lastSavedStateFile = new File(filePath);
			if (!lastSavedStateFile.exists()) {
				lastSavedStateFile.createNewFile();
			}
			
			FileOutputStream fileOut = new FileOutputStream(lastSavedStateFile, false);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(entity);
	        out.close();
	        fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
			return false;
		}
		
		return true;
	}
}

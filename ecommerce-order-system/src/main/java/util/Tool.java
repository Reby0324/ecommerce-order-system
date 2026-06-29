package util;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tool {
	
	//儲存物件到檔案
	public static void saveFile(String fileName,Object object) {
		try {
			FileOutputStream fos=new FileOutputStream(fileName);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			
			oos.writeObject(object);
			oos.close();
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		//從檔案讀取物件
		public static Object readFile(String fileName) {
			Object object=null;
			
			try {
			FileInputStream fis=new FileInputStream(fileName);
			ObjectInputStream ois=new ObjectInputStream(fis);
			
			object=ois.readObject();
			
			ois.close();
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}		
		return object;
	}

}

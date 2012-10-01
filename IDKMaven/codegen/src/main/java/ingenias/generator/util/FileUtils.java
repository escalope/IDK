package ingenias.generator.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class FileUtils {
	public static byte[] readFileAsBytes(String filename)
			throws FileNotFoundException, IOException {
		FileInputStream fis=new FileInputStream(filename);
		Vector<Byte> sb=new Vector<Byte>();
		int read=0;
		while (read!=-1){
			read=fis.read();
			if (read!=-1)
				sb.add((byte)read);
		}
		fis.close();
		byte[] array=new byte[sb.size()];
		for (int k=0;k<array.length;k++)
			array[k]=sb.elementAt(k);
		return array;
	}

	public static StringBuffer readFile(String filename)
			throws FileNotFoundException, IOException {
		FileInputStream fis=new FileInputStream(filename);
		StringBuffer sb=new StringBuffer();
		int read=0;
		byte[] buffer =new byte[1000];
		while (read!=-1){
			read=fis.read(buffer);
			if (read!=-1){
				for (int k=0;k<read;k++)
					sb.append((char)buffer[k]);
			}
		}
		fis.close();
		return sb;
	}
}

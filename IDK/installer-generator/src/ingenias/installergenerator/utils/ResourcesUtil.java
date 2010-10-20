/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

/**
 *
 * @author Carlos
 */
public class ResourcesUtil {

    public static void writeToFile(String text, File dest) throws IOException {
        FileWriter fw = new FileWriter(dest);
        StringReader sr = new StringReader(text);
        char[] data = new char[1024];
        int len = sr.read(data);
        while (len > 0) {
            fw.write(data, 0, len);
            len = sr.read(data);
        }
        fw.close();
        sr.close();
    }

    public static void copy(String res, File dest) throws FileNotFoundException, IOException, URISyntaxException {
        //TODO arreglar chapuzoide
        InputStream is = getResource(res);
        BufferedInputStream bis = new BufferedInputStream(is);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        byte[] buf = new byte[1024];
        int len = bis.read(buf);
        while (len > 0) {
            bos.write(buf, 0, len);
            len = bis.read(buf);
        }
        bos.flush();
        bos.close();
        bis.close();
    }

    public static String reader(String resource) throws IOException, URISyntaxException {

        InputStream is = getResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        StringBuffer result = new StringBuffer("");
        while (line != null) {
            result.append(line);
            result.append("\n");
            line = reader.readLine();
        }
        reader.close();
        return result.toString();
    }

    public static InputStream getResource(String resource) throws MalformedURLException, IOException {
 
        if (resource.substring(0, 1).equals("/")) {
            resource = resource.substring(1);
        }

        InputStream is = null;
        //TODO: arreglar chapuza
        JarFile jar = new JarFile("ext/modinstallergenerator.jar", true);
        JarEntry entry = jar.getJarEntry(resource);
        jar.close();
        if (entry != null) {
            JarInputStream jis = new JarInputStream(new FileInputStream("ext/modinstallergenerator.jar"));
            boolean found = false;
            JarEntry entryIS = null;
            byte[] b = null;
            while (!found && ((entryIS = jis.getNextJarEntry()) != null)) {
                if (entryIS.getName().equals(resource)) {
                    long size = entryIS.getSize();
                    b = new byte[(int) size];
                    int rb = 0;
                    int chunk = 0;
                    while (((int) size - rb) > 0) {
                        chunk = jis.read(b, rb, (int) size - rb);
                        if (chunk == -1) {
                            break;
                        }
                        rb += chunk;
                    }
                    found = true;
                }
            }

            if (found) {
                System.out.println("found!");
                is = new ByteArrayInputStream(b);
            }
            jis.close();
        } else {
            throw new IOException(resource + " entry was not found");
        }
        return is;
    }
}

package es.client.test.common;

import java.io.*;
import java.util.Vector;

public class FileHandle {
    @Deprecated
    public static Vector<String> readByBufferedReader(String fileName) {
        Vector<String> buffer = new Vector<String>();
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            String[] eachread;
            bufread = new BufferedReader(new FileReader(file));
            int line = 0;
            while ((read = bufread.readLine()) != null) {
                buffer.add(read);
            }
            bufread.close();
            return buffer;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return buffer;
    }

}

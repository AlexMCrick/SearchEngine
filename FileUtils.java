import java.io.*;
import java.util.List;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class FileUtils {


    public List<Page> getPageList(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Page> obj = (List<Page>) ois.readObject();
            if (obj == null)
                return null;
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Word> getWordList(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Word> obj = (List<Word>) ois.readObject();
            if (obj == null)
                return null;
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean savePageTable(List<Page> pageTable, String filePath) {
        try {
            if (pageTable == null)
                return false;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(pageTable);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveWordTable(List<Word> wordTable, String filePath) {
        try {
            if (wordTable == null)
                return false;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(wordTable);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
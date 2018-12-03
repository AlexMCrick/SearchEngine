import java.io.Serializable;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */
public class Page implements Serializable, Comparable<Page> {

    String url;
    int urlID;
    public static final long serialVersionUID = -1827677255104766839L;


    Page(String url, int urlID) {
        this.url = url;
        this.urlID = urlID;
    }

    public int compareTo(Page candidate) {
        if (urlID < candidate.getURLID())
            return -1;
        if (urlID == candidate.getURLID())
            return 0;
        return 1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Page) {
            if (((Page) obj).getURL().equals(url) || ((Page) obj).getURLID() == urlID) {
                return true;
            }
        }
        return false;
    }

    String getURL() {
        return url;
    }

    int getURLID() {
        return urlID;
    }



}
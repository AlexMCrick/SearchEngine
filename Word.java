/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */
import java.io.*;
import java.util.*;
public class Word implements Serializable {
    private ArrayList<Integer> postings = new ArrayList<Integer>();
    public static final long serialVersionUID = -3696191086353573895L;
    private String word;
    public Word(String word, int urlID) {
        this.word = word;
        this.addURLID(urlID);
    }
    public void addURLID(int urlID) {
        this.postings.add(urlID);
    }
    public String getWord() {
        return this.word;
    }
    public List<Integer> getList() {
        return this.postings;
    }
    public boolean equals(Object obj) {
        //checks to see if object is word object, if it is, it typecasts and then compares strings
        if (obj instanceof Word) {
            Word o = (Word) obj;
            if (this.getWord().equals(o.getWord())) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
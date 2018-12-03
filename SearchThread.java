import java.util.*;

/**
 * CS180 Project 4
 * @author Columbus Holt L06
 * @author Alex Crick L06
 */

public class SearchThread {
    int finish;
    int start;
    String[] terms;

    public SearchThread(int start, int finish, String[] terms) {
        this.start = start;
        this.finish = finish;
        this.terms = terms;
    }

    Word findTerm(String term) {
        boolean found = false;
        Word bird = null;
        for (Word word : Search.wordList.subList(start, finish)) {
            if (word.getWord().toLowerCase().equals(term.toLowerCase())) {
                found = true;
                bird = word;
            }
        }

        if (found)
            return bird;
        return null;
    }

    void run() {
        ArrayList<Word> words = new ArrayList<Word>();
        for (String s: terms) {
            words.add(findTerm(s));
        }
        for (Word w: words) {
            if (w != null) {
                List<Integer> urlIDs = w.getList();
                for (Integer i: urlIDs) {
                    int URL = i;
                    boolean found = false;
                    for (Result r: Search.resultSet) {
                        if (r.getURLID() == URL) {
                            r.incrementScore();
                            found = true;
                        }
                    }
                    // if URL ID is not found, create new Result with that URLID
                    if (!found) {
                        String urlName = Search.pageList.get(URL).getURL();
                        Result newResult = new Result(urlName, URL);
                        Search.resultSet.add(newResult);
                    }
                }
            }
        }
    }
}
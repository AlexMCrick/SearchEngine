import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class Search {

    static List<Page> pageList = new ArrayList<Page>();
    private String pageListFile;
    static List<Result> resultSet = new ArrayList<Result>();
    static List<Word> wordList = new ArrayList<Word>();
    private String wordListFile;
    static FileUtils f = new FileUtils();

    public Search(String wordListFile, String pageListFile) {
        wordListFile = wordListFile;
        pageListFile = pageListFile;
        setup(wordListFile, pageListFile);
    }

    List<Result> executeQuery(String query) {
        nullCheck();
        String[] terms = query.split(" ");
        resultSet = new ArrayList<Result>();
        resultSet = Collections.synchronizedList(resultSet);
        SearchThread[] threads = new SearchThread[5];
        for (int i=0; i<threads.length;i++) {
            threads[i] = new SearchThread((int) (wordList.size()/5) *i, (int) (wordList.size()/5) * (i+1), terms);
        }
        for (SearchThread thread : threads) {
            thread.run();
        }
        sort();
        return resultSet;
    }

    void nullCheck() {
        if (pageList == null || wordList == null) {
            setup(wordListFile, pageListFile);
            if (pageList == null || wordList == null) {
                System.out.println("exiting");
                System.exit(0);
            }
        }
    }

    void setup(String wordListFile, String pageListFile) {
        pageList = f.getPageList(pageListFile);
        wordList = f.getWordList(wordListFile);
    }

    void sort() {
        resultSet.sort(Result::compareTo);
    }

}
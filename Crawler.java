import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class Crawler {
    static int currentID;
    static String domain;
    static boolean seeded = false;
    static int limit;
    static List<Page> parsed = new ArrayList<Page>();
    static Parser parser = new Parser();
    MyQueue toParse = new MyQueue();
    static int totalURLs = 0;
    static List<String> visited = new ArrayList<String>();
    static List<Word> words = new ArrayList<Word>();

    public Crawler(String seed, String domain, int limit) {
        Crawler.domain = domain;
        Crawler.limit = limit;
        currentID = 0;
        totalURLs = 0;
        toParse.add(seed);
    }

    void addPageToList(Page p) {
        parsed.add(p);
    }

    void addToQueue(String url) {
        toParse.add(url);
        totalURLs++;
    }

    void addWordToList(String word, int id) {
        Word w = new Word(word, id);
        words.add(w);
    }

    void crawl() {
        do {
            String url = (String) toParse.remove().getData();

            if (!visited.contains(url)) {
                try {
                    Document d = parser.getDocument(url);
                    visited.add(url);
                    if (parse(d, currentID)) {
                        Page p = new Page(url, currentID);
                        parsed.add(p);
                        currentID++;
                    }
                } catch (ParseException p) {
                }
            }
        }
        while (currentID <= limit && totalURLs > 0);
    }

    boolean isInDomain(String url) {
        return url.contains(domain);
    }

    boolean isValidURL(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    boolean parse(Document doc, int id) {
        try {
            parseLinks(doc);
            parseText(doc, id);
            return true;
        } catch (ParseException p) {
            return false;
        }
    }

    void parseLinks(Document doc) throws ParseException {
        Elements l = parser.getLinks(doc);
        for (Element e : l) {
            String link = e.attr("abs:href");
            if (!visited.contains(link) && isInDomain(link) && isValidURL(link)) {
                addToQueue(link);
            }
        }
    }

    void parseText(Document doc, int id) throws ParseException {

        if (parser.getBody(doc) != null) {
            String body = parser.getBody(doc);
            String[] wordlist = body.split(" ");
            boolean status;
            for (String s : wordlist) {
                status = false;
                for (Word w : words) {
                    if (w.getWord().toLowerCase().equals(s.toLowerCase())) {
                        w.addURLID(id);
                        status = true;
                    }
                }
                if (!status) {
                    addWordToList(s.toLowerCase(), id);
                }
            }
        }
    }
}
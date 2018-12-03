import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.Jsoup;
import java.io.IOException;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class Parser {

    public Document getDocument(String url) throws ParseException {

        if (url == null)
            throw new ParseException("getDocument() failed. String url is null.");

        if (url.equals(""))
            throw new ParseException("getDocument() failed. String url is empty.");
        try {
            try {
                Document d = Jsoup.connect(url).timeout(3000).get();
                if (d == null)
                    throw new ParseException("getDocument() failed. Document is null.");
                return d;
            } catch (IOException e) {
                throw new ParseException("getDocument() failed. Connection failed.");
            }
        } catch (IllegalArgumentException e) {
            throw new ParseException("getDocument() failed. Connection failed.");
        }
    }

    public Elements getLinks(Document doc) throws ParseException {

        if (doc != null) {
            Elements links = doc.select("a[href]");
            return links;
        }
        else {
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }
    }

    public String getBody(Document doc) throws ParseException {

        if (doc == null)
            throw new ParseException("getBody() failed. Document parameter is null.");
        else {
            if (doc.body() != null) {
                Element body = doc.body();
                if (body.text() != null) {
                    return body.text();
                } else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
    }
}

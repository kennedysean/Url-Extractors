import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for scraping a url of its links.
 */
public final class UrlStripper {

    /**
     * The regex that will be used to find the links.
     */
    private static final String REGEX = "(https?://[a-zA-Z0-9]+\\.[a-zA-Z0-9]+[a-zA-Z0-9._+/~-]*)";
    /**
     * The compiled regular expression.
     */
    private static final Pattern WEBSITE_PATTERN = Pattern.compile(REGEX);

    /**
     * Private constructor.
     */
    private UrlStripper() {
    }

    /**
     * Main method.
     * @param args
     *          system arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a site to strip of links: ");
        StringBuilder webText = getWebsiteText(in.nextLine());
        List<String> links = getLinks(webText);
        links.sort(String::compareTo);
        for (String link : links) {
            System.out.println(link);
        }
    }

    /**
     * Attempts to create and return a BufferedReader from the given URL.
     * @param url
     *          the user entered URL
     * @return
     *          the buffered reader
     * @throws IOException
     *          throws an IOException if the URL is malformed or otherwise faulty
     */
    private static BufferedReader getWebsiteReader(String url) throws IOException {
        URL websiteURL = new URL(url);
        InputStreamReader inStream = new InputStreamReader(websiteURL.openStream());
        return new BufferedReader(inStream);
    }

    /**
     * Reads the input from the given URL and returns a StringBuilder containing
     * all text from the source.
     * @param url
     *          the user entered URL
     * @return a StringBuilder containing the input text
     */
    private static StringBuilder getWebsiteText(String url) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = getWebsiteReader(url);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }
    /**
     * Extracts the links from the given StringBuilder.
     * @param websiteText
     *          the StringBuilder object containing the website's text
     * @return list of links
     */
    private static List<String> getLinks(StringBuilder websiteText) {
        List<String> links = new ArrayList<>();
        Matcher webMatcher = WEBSITE_PATTERN.matcher(websiteText);
        while (webMatcher.find()) {
            String toAdd = webMatcher.group();
            if (!links.contains(toAdd)) {
                links.add(toAdd);
            }
        }
        return links;
    }
}

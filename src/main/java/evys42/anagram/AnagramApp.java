package evys42.anagram;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.jsoup.Jsoup;

/**
 * Accepted arguments:<ul>
 * <li>A mix of "words" and "phrases":<ul>
 *     <li>words are simple, like: <tt>apt but tap tub pat</tt>
 *     <li>phrases are double-quoted, like: <tt>"Madam Curie" "Radium came"</tt>
 *     </ul>
 * <li>a URL: <pre>-url https://en.wikipedia.org/wiki/Anagram</pre>
 * <li>a file path: <pre>-file path/to/local.file</pre>
 * </ul>
 */
public class AnagramApp {

	public static void main(String[] args) {
		Reader input = null;
        if (args.length == 0) {
        	System.out.println("Nothing to process.");
        	return;
        } else if ("-url".equalsIgnoreCase(args[0])) {
        	if (args[1] == null) {
        		System.out.println("A URL is missing.");
            	return;
        	}
        	try {
        		String text = Jsoup.connect(args[1]).get().text();
        		input = new StringReader(text);
        	} catch (IOException ioe) {
        		System.out.printf("Failed to read from %s" + args[1]);
        		ioe.printStackTrace();
        		return;
        	}
        } else if ("-file".equalsIgnoreCase(args[0])) {
        	if (args[1] == null) {
        		System.out.println("A file is missing.");
            	return;
        	}
        	try {
        		input = new FileReader(args[1]);
        	} catch (IOException ioe) {
        		System.out.printf("Failed to read from %s" + args[1]);
        		ioe.printStackTrace();
        		return;
        	}
		} else {
			if (args.length == 1) {
				input = new StringReader(args[0]);
			} else {
				StringBuilder sb = new StringBuilder();
				for (String s : args) {
					if (sb.length() > 0) {
						sb.append(' ');
					}
					sb.append(s);
				}
				input = new StringReader(sb.toString());
			}
		}
        Controller.digest(input);
    }
}

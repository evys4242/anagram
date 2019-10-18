package evys42.anagram;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ControllerInputTests {

	@Test
	void testURL() throws IOException {
		try {
			Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Anagram").get();
			String text = doc.text();
			Controller.digest(new StringReader(text));
		} catch (Throwable t) {
			Assertions.fail(t);
		}
	}

	@Test
	void testFile() throws IOException {
		try {
			Reader r = new FileReader("src\\test\\resources\\wiki-anagram-fixed.txt");
			Controller.digest(r);
		} catch (Throwable t) {
			Assertions.fail(t);
		}
	}
}

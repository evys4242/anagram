package evys42.anagram;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

	private Processor p;
	
	public void digest(Reader input) {
		System.out.println("...");
		
		List<String> words = parseInput(input);
		if (words == null || words.isEmpty()) {
			System.out.println("Nothing to process.");
			return;
		}
		System.out.printf("Input: %d (words).", words.size());
		System.out.println();
		
		p = new Processor();
		words.forEach(w -> p.accept(w));
		List<Bucket> anagrams = p.getResult().stream()
			.filter(b -> !b.isEmpty())
			.collect(Collectors.toList())
		;
		p = null;
		
		if (anagrams.isEmpty()) {
			System.out.println("No anagrams has been detected.");
			return;
		} else {
			System.out.printf("Anagrams: %d (groups)", anagrams.size());
			System.out.println();
		}
		printResult(anagrams);
		return;
	}
	
	private List<String> parseInput(Reader input) {
		List<String> words = new ArrayList<>(4096);
		StreamTokenizer tokenizer = new StreamTokenizer(input);
		tokenizer.ordinaryChar('\'');
		try {
			while (tokenizer.ttype != StreamTokenizer.TT_EOF) {
				tokenizer.nextToken();
				if (tokenizer.ttype == StreamTokenizer.TT_WORD 
						|| tokenizer.ttype == '"') {
					String token = tokenizer.sval;
					if (token.charAt(token.length() - 1) == '.') {
						token = token.substring(0, token.length() - 1);
					}
					if (token != null && !"".equals(token)) {
						words.add(token);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error parsing input into words. Terminated.");
			e.printStackTrace();
			words = null;
		}
		return words;
	}
	
	private void printResult(List<Bucket> anagrams) {
		System.out.println();
		anagrams.forEach(b -> {
			StringBuilder sb = new StringBuilder();
			sb.append('"').append(b.getSubject()).append('"');
			b.getAnagrams().forEach(a -> {
				sb.append(", ").append('"').append(a).append('"');
			});
			System.out.println(sb);
		});
	}
}

package evys42.anagram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Collects words into "buckets":<ol>
 * <li>if accepted word's has a not null signature, use it to look up bucket in a processed cache;
 * <li>if no bucket found, creates one with a signature and a word as a subject. Puts it into cache;
 * <li>if bucket found, adds accepted word to it (as an anagram).
 * </ol>
 * 
 * @author evys42
 */
public class Collector implements Consumer<String> {

	private Map<Signature, Bucket> processed = new HashMap<>(1024);

	@Override
	public void accept(String word) {
		String trimmed = word.strip();
		if (trimmed == null || trimmed.isBlank()) {
			return;
		}
		Signature s = Signature.from(trimmed);
		if (s == null) {
			return;
		}
		Bucket b = this.processed.get(s);
		if (b == null) {
			b = new Bucket(trimmed, s);
			this.processed.put(s, b);
		} else {
			b.add(trimmed);
		}
	}

	public Collection<Bucket> getResult() {
		return this.processed.values();
	}
}

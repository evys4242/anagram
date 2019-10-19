package evys42.anagram;

import java.util.Set;
import java.util.TreeSet;

/**
 * A container of subject's anagrams.
 * 
 * @author evys42
 */
public final class Bucket {

	private final String subject;
	private final Signature signature;
	private final Set<String> anagrams;
	
	public Bucket(String subj) {
		this(subj, Signature.from(subj));
	}

	public Bucket(String subj, Signature sig) {
		if (subj == null || subj.length() < 2) {
			throw new IllegalArgumentException("A subject is too short");
		}
		if (sig == null) {
			throw new IllegalArgumentException("Subject's signature can't be null");
		}
		this.subject = subj;
		this.signature = sig;
		this.anagrams = new TreeSet<>();
	}

	public String getSubject() {
		return subject;
	}

	public Signature getSignature() {
		return signature;
	}

	public Set<String> getAnagrams() {
		return anagrams;
	}
	
	public void add(String anagram) {
		if (anagram == null 
			|| anagram.isBlank() 
			|| subject.equalsIgnoreCase(anagram)
			) {
			return;
		}
		this.anagrams.add(anagram);
	}
	
	public boolean isEmpty() {
		return this.anagrams.isEmpty();
	}
}

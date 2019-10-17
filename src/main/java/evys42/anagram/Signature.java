package evys42.anagram;

import java.util.Arrays;
import java.util.TreeMap;
//import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An anagram "signature" is an array of distinct original letters 
 * (as Unicode code points) accompanied by counters.
 * <p>According to wikipedia, an <a href="https://en.wikipedia.org/wiki/Anagram">anagram</a> is a
 * "word or phrase formed by rearranging the letters of a different word or phrase, 
 * typically using all the original letters exactly once".
 * 
 * @author evys42
 */
public final class Signature {

	private final int[] value;
	private final int hash;
	
	/**
	 * A factory method. 
	 * Returns immutable "signature", when it make sense, or null.
	 */
	//	public static Signature build(String word) {
	//		if (word == null || word.length() < 2) {
	//			return null;
	//		}
	//		final int[] values = word.codePoints()
	//			.filter(Character::isLetter)
	//			.map(Character::toUpperCase)
	//			.boxed()
	//			.collect(
	//				Collectors.groupingBy(
	//					cp -> cp,
	//					TreeMap::new,
	//					Collectors.summingInt(cp -> 1)
	//				)
	//			).entrySet().stream()
	//			.flatMap(e -> Stream.of(e.getKey(), e.getValue()))
	//			.mapToInt(i -> i)
	//			.toArray()
	//		;
	//		return (values.length < 4)? null : new Signature(values);
	//	}
	public static Signature build(String word) {
		if (word == null || word.length() < 2) {
			return null;
		}

		// Collect distinct letters with corresponding counts
		// into a sorted map.
		final TreeMap<Integer, Integer> countMap = new TreeMap<>();
		word.codePoints()
			.filter(Character::isLetter)
			.map(Character::toUpperCase)
			.forEach(cp -> countMap.merge(cp, 1, Integer::sum))
		;
		if (countMap.size() < 2) {
			return null;
		}

		// Flatten map into array
		final int[] values = countMap.entrySet().stream()
				.flatMap(e -> Stream.of(e.getKey(), e.getValue()))
				.mapToInt(i -> i)
				.toArray()
		;
		return new Signature(values);
	}
	
	/**
	 * Private constructor 
	 * (use public factory to instantiate).
	 */
	private Signature(int[] value) {
		this.value = value;
		this.hash = Arrays.hashCode(value);
	}

	public int[] getValue() {
		return this.value;
	}
	
	@Override
	public int hashCode() {
		return this.hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if((o == null) || !(o instanceof Signature)) { 
			return false; 
		}
		return Arrays.equals(this.value, ((Signature) o).value);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.value.length; i += 2) {
			sb.appendCodePoint(this.value[i]).append(this.value[i + 1]);
		}
		return sb.toString();
	}
}

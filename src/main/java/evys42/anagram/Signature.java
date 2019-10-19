package evys42.anagram;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An anagram "signature" is an array of distinct original letters (as Unicode code points)
 * accompanied by counters. Pairs are sorted in natural order.
 * 
 * <p><i>According to wikipedia, an <a href="https://en.wikipedia.org/wiki/Anagram">anagram</a> is a
 * "word or phrase formed by rearranging the letters of a different word or phrase, 
 * typically using all the original letters exactly once".</i>
 * 
 * @author evys42
 */
public final class Signature {

	private static final Function<String, int[]> BUILD_STRATEGY = Signature::pureStreamStrategy; 
	//private static final Function<String, int[]> BUILD_STRATEGY = Signature::mixedStreamStrategy;

	private final int[] value;
	private final int hash;

	/**
	 * A factory method. 
	 * Returns immutable "signature", when it make sense, or null.
	 */
	public static Signature from(String word) {
		if (word == null || word.length() < 2) {
			return null;
		}

		int[] values = BUILD_STRATEGY.apply(word);
		
		return (values.length < 4)? null : new Signature(values);
	}

	/**
	 * A "pure" stream strategy to build a signature value.
	 */
	@SuppressWarnings("unused")
	private static int[] pureStreamStrategy(String word) {
		return word.codePoints()
				.filter(Character::isLetter)
				.map(Character::toUpperCase)
				.boxed()
				.collect(
					Collectors.groupingBy(
						cp -> cp,
						TreeMap::new,
						Collectors.summingInt(cp -> 1)
					)
				).entrySet().stream()
				.flatMap(e -> Stream.of(e.getKey(), e.getValue()))
				.mapToInt(i -> i)
				.toArray()
		;
	}
	
	/**
	 * A "mixed" stream strategy to build a signature value.
	 */
	@SuppressWarnings("unused")
	private static int[] mixedStreamStrategy(String word) {
		// Collect distinct letters and counts into a sorted map.
		final TreeMap<Integer, Integer> countMap = new TreeMap<>();
		word.codePoints()
			.filter(Character::isLetter)
			.map(Character::toUpperCase)
			.forEach(cp -> countMap.merge(cp, 1, Integer::sum))
		;
		// Flatten map into array when it makes sense.
		return (countMap.size() < 2)? new int[0] : 
			countMap.entrySet().stream()
				.flatMap(e -> Stream.of(e.getKey(), e.getValue()))
				.mapToInt(i -> i)
				.toArray()
		;
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

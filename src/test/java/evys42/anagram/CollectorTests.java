package evys42.anagram;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CollectorTests {

	@Test
	void testNone() {
		Collector p = new Collector();
		Assertions.assertNotNull(p.getResult());
		Assertions.assertTrue(p.getResult().isEmpty());
	}

	@Test
	void testOne() {
		String subj = "word";
		Collector p = new Collector();
		p.accept(subj);
		Assertions.assertNotNull(p.getResult());
		Assertions.assertFalse(p.getResult().isEmpty());
		Assertions.assertEquals(1, p.getResult().size());
		Optional<Bucket> ob = p.getResult()
				.stream()
				.findFirst()
		;
		Assertions.assertTrue(ob.isPresent());
		Bucket b = ob.get();
		Assertions.assertNotNull(b.getSubject());
		Assertions.assertEquals(subj, b.getSubject());
		Assertions.assertTrue(b.isEmpty());
	}

	@Test
	void testStream() {
		Collector p = new Collector();
		Stream.of("silent", "world", "listen").forEach(p);
		Assertions.assertNotNull(p.getResult());
		Assertions.assertFalse(p.getResult().isEmpty());
		Assertions.assertEquals(2, p.getResult().size());
		Optional<Bucket> ob = p.getResult()
				.stream()
				.filter(b -> !b.isEmpty())
				.findAny()
		;
		Assertions.assertTrue(ob.isPresent());
		Bucket b = ob.get();
		Assertions.assertEquals("silent", b.getSubject());
		Assertions.assertFalse(b.isEmpty());
		Assertions.assertEquals(1, b.getAnagrams().size());
		Optional<String> oa = b.getAnagrams()
				.stream()
				.findFirst()
		;
		Assertions.assertTrue(oa.isPresent());
		Assertions.assertEquals("listen", oa.get());
	}
}

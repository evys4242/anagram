package evys42.anagram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SignatureTests {

	@Test
	void testSimpleWord() {
		Signature s = Signature.from("ABC");
		int[] expected = {'A', 1, 'B', 1, 'C', 1};
		Assertions.assertArrayEquals(expected, s.getValue());
	}
	
	@Test
	void testMixedCase() {
		Signature s = Signature.from("xY");
		int[] expected = {'X', 1, 'Y', 1};
		Assertions.assertArrayEquals(expected, s.getValue());
	}

	@Test
	void testMixedNoLetter() {
		Signature s = Signature.from(" Vc1-09fc, ");
		int[] expected = {'C', 2, 'F', 1, 'V', 1};
		Assertions.assertArrayEquals(expected, s.getValue());
	}

	@Test
	void testSingleLetter() {
		Signature s = Signature.from("a");
		Assertions.assertNull(s, "Expecting no signature for a single letter input");
	}
	
	@Test
	void testNoLetter() {
		Signature s = Signature.from("1!2+3@4_5#6)7$8(9%0*^&");
		Assertions.assertNull(s, "Expecting no signature for a non-letter input");
	}
	
	@Test
	void testNull() {
		Signature s = Signature.from(null);
		Assertions.assertNull(s, "Expecting no signature for a null input");
	}

	@Test
	void testEmpty() {
		Signature s = Signature.from("");
		Assertions.assertNull(s, "Expecting no signature for an empty input");
	}
	
	@Test
	void testToString() {
		Signature s = Signature.from(" Ha1-2fA, ");
		Assertions.assertEquals("A2F1H1", s.toString());
	}
}

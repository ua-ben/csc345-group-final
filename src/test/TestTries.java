package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import main.Trie;

class TestTries {

	@Test
	void testAddSearchSimple() {
		Trie trie = new Trie();

		trie.insert("test");
		trie.insert("train");
		trie.insert("television");

		assert (trie.search("test") == true);
		assert (trie.search("orange") == false);
		assert (trie.search("tele") == false);
	}

	@Test
	void testDeletionSimple() {
		Trie trie = new Trie();

		trie.insert("test");
		trie.insert("tv");
		assert (trie.search("test") == true);

		trie.delete("test");
		assert (trie.search("test") == false);
		assert (trie.search("tv") == true);
	}

	@Test
	void testAddDelete() {
		int maxElements = 10;
		String alpha0 = "abcdefghijklmnopqrstuvwxyz";
		String alpha1 = "qwertyuiopasdfghjklzxcvbnm";
		String alpha2 = alpha1.toUpperCase();

		Trie trie = new Trie();

		List<String> randomStrings = new ArrayList<String>(maxElements);

		// Add @maxElements random strings
		for (int i = 0; i < maxElements; i++) {
			String string = generateRandomString(i, alpha1, 1, 50);
			System.out.println(string);

			trie.insert(string);
			randomStrings.add(string);

			assertTrue(trie.search(string));
		}

		Collections.shuffle(randomStrings);

		// Remove the added strings
		for (int i = 0; i < maxElements; i++) {
			String string = randomStrings.get(i);
			System.out.println(string);

//			assertTrue(trie.search(string));
			trie.search(string);
			trie.delete(string);
			assertFalse(trie.search(string));
		}
	}

	private String generateRandomString(int seed, String alphabet, int minLength, int maxLength) {
		String output = "";
		Random random = new Random(seed);
		int length = random.nextInt(minLength, maxLength);

		return generateRandomString(seed, alphabet, length);
	}

	private String generateRandomString(int seed, String alphabet, int length) {
		String output = "";
		Random random = new Random(seed);

		for (int i = 0; i < length; i++) {
			output += alphabet.charAt(random.nextInt(alphabet.length()));
		}

		return output;
	}

}

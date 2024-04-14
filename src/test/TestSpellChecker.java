package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import main.SpellChecker;
import main.SpellCheckerTrie;
import main.Trie;
import main.TrieNode;

class TestSpellChecker {

	@RepeatedTest(10)
	void testWords() {
		SpellChecker sc = new SpellChecker("words.txt");

		int wordPoolSize = 100;
		List<String> correctWords = getRandomWordsFromFile("words.txt", wordPoolSize);

		for (int i = 0; i < wordPoolSize; i++) {
			String correctWord = correctWords.get(i);
			boolean misspelled = sc.isWordMispelled(correctWord);

			assertFalse(misspelled);
		}
	}

	List<String> getRandomWordsFromFile(String filePath, int count) {
		List<String> output = new ArrayList<String>(count);
		List<String> words = new ArrayList<String>();

		FileReader file;
		try {
			file = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(file);
			String line = reader.readLine();

			while (line != null) {
				words.add(line);
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		Random random = new Random();

		for (int i = 0; i < count; i++) {
			int index = random.nextInt(words.size());
			output.add(words.get(index));
		}

		return output;
	}

	private String generateRandomString(int seed, String alphabet, int length) {
		String output = "";
		Random random = new Random(seed);

		for (int i = 0; i < length; i++) {
			output += alphabet.charAt(random.nextInt(alphabet.length()));
		}

		return output;
	}

	@Test
	void findDivergentNode() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		SpellCheckerTrie t = new SpellCheckerTrie(alphabet);
		t.insert("because");

		StringBuilder prefix = new StringBuilder();
		TrieNode d = t.findDivergentNode("becuase", prefix);

		assertNotEquals(d, null);
		assertNotEquals(d.debugGetChildLetters(alphabet), "a");
		assertEquals("bec", prefix.toString());
		assertTrue(d.hasChildren());

		t.getSpellingSuggestions(d, null);
	}

	@Test
	void getSpellingSuggestions_0() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		SpellCheckerTrie t = new SpellCheckerTrie(alphabet);
		t.insert("because");

		List<String> suggestions = new ArrayList<String>();
		t.getSpellingSuggestions("becuase", suggestions);

		assertEquals(suggestions.get(0), "because");
	}

	@Test
	void getSpellingSuggestions_1() {
		SpellChecker t = new SpellChecker("words.txt");

		List<String> misspellings = Arrays.asList("becvausze", "hapoiness", "trespasdasdss");
		List<String> correctWords = Arrays.asList("because", "happiness", "trespass");
		List<String> suggestions;

		for (int i = 0; i < misspellings.size(); i++) {
			suggestions = t.getSpellingSuggestions(misspellings.get(i));

			System.out.println("Word: " + misspellings.get(i));
			for (int j = 0; j < suggestions.size(); j++) {
				System.out.println("suggestion: " + suggestions.get(j));
			}

			assertTrue(suggestions.contains(correctWords.get(i)));
		}
	}
}

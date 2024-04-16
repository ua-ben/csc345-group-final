package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
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

	@RepeatedTest(25)
	void testAddDelete() {
		int maxElements = 1000;
		String alpha1 = "qwertyuiopasdfghjklzxcvbnm";
		String alphaInvalid = alpha1.toUpperCase();

		Trie trie = new Trie();

		List<String> randomStrings = new ArrayList<String>(maxElements);
		List<String> randomInvalidStrings = new ArrayList<String>(maxElements);

		// Add @maxElements random strings
		for (int i = 0; i < maxElements; i++) {
			String string = generateRandomString(i, alpha1, 1, 50);
			String invalidString = generateRandomString(i, alphaInvalid, 1, 50);

			if (!trie.search(string)) {
				trie.insert(string);
				randomStrings.add(string);
			}
			randomInvalidStrings.add(string);

			assertTrue(trie.search(string));
			assertFalse(trie.search(invalidString));
		}

		Collections.shuffle(randomStrings);

		// Remove the added strings
		for (int i = 0; i < randomStrings.size(); i++) {
			String string = randomStrings.get(i);

			assertTrue(trie.search(string));
			trie.search(string);
			trie.delete(string);
			assertFalse(trie.search(string));
		}

		// Attempt to remove string that are not in the array just to make sure that
		// works!
		for (int i = 0; i < randomInvalidStrings.size(); i++) {
			String string = randomInvalidStrings.get(i);

			assertFalse(trie.search(string));
			trie.search(string);
			trie.delete(string);
			assertFalse(trie.search(string));
		}
	}

	@Test
	void testWordList() {
		Trie trie = new Trie();
		String words[] = new String[100];
		int trieTotalWords = 0;
		int numWords = 0;
		Random rng = new Random();
		boolean test = false;

		try {
			// Read words.txt and add all words to Trie
			BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
			String line = reader.readLine();

			// Fill list with ~300k words
			System.out.println("Filling Trie...");
			while (line != null) {
				trie.insert(line);
				trieTotalWords++;
				// 1/3000 chance to add to word list to check against later, max 100.
				if (rng.nextInt(3000) == 1) {
					if (numWords < 100) {
						words[numWords] = line;
						numWords++;
					}
				}

				line = reader.readLine();
			}
			reader.close();
			System.out.println("Added " + trieTotalWords + " words to the Trie!");

			System.out.println("-------------------------------");
			System.out.println("Searching for words...");
			System.out.println("-------------------------------");

			for (int i = 0; i < numWords; i++) {

				test = trie.search(words[i]);
				assertTrue(test);
				if (test) {
					System.out.println("Successfully found word: " + words[i]);
				} else {
					System.out.println("Expected, but did not find: " + words[i]);
				}
			}

			System.out.println("-------------------------------");
			System.out.println("Removing words...");
			System.out.println("-------------------------------");

			int index = rng.nextInt(numWords - 8);
			for (int j = 0; j < 7; j++) {
				String check = words[index + j];
				System.out.println("Removing: " + check + "...");
				trie.delete(check);

				test = !(trie.search(check));
				assertTrue(test);
				if (test) {
					System.out.println("Succesfully Removed " + check + "!\n");
				} else {
					System.out.println(check + " was not removed.");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Source Words File Not Found");
			fail();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}

	}

	private String generateRandomString(int seed, String alphabet, int minLength, int maxLength) {
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

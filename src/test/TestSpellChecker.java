package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import main.SpellChecker;
import main.Trie;

class TestSpellChecker {

	@Test
	void testWords() {
		SpellChecker sc = new SpellChecker("words.txt");

		int wordPoolSize = 100;
		List<String> correctWords = getRandomWordsFromFile("words.txt", wordPoolSize);

		for (int i = 0; i < wordPoolSize; i++) {
			String correctWord = correctWords.get(i);
			System.out.println(correctWord);
			boolean misspelled = sc.isWordMispelled(correctWord);

			if (misspelled) {
				misspelled = misspelled;
			}

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

}

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An algorithm to suggest possible complete words given one which is incomplete
 * or non-existant.
 */
public class SpellChecker {

	/**********************************************************************************************
	 * Data
	 */

	// Internal trie used to store our word bank
	private SpellCheckerTrie trie;

	// Alphabet this spell checker operates within
	private String alphabet;

	// Count of words in the trie
	private int words;

	/**********************************************************************************************
	 * Constructors
	 */

	/**
	 * Constructs an instance of SpellChecker using a default alphabet (lower case
	 * a-z).
	 * 
	 * @param dictonaryFilePath Location of a file containing a set of new-line
	 *                          separated words to compare against for spell
	 *                          checking.
	 */
	public SpellChecker(String dictonaryFilePath) {
		this("abcdefghijklmnopqrstuvwxyz", dictonaryFilePath);
	}

	/**
	 * Constructs an instance of SpellChecker using a custom alphabet.
	 * 
	 * @param alphabet          String containing all characters that this
	 *                          dictionary will process. Used for its internal Trie
	 *                          structure.
	 * @param dictonaryFilePath Location of a file containing a set of new-line
	 *                          separated words to compare against for spell
	 *                          checking.
	 */
	public SpellChecker(String alphabet, String dictonaryFilePath) {
		this.alphabet = alphabet;
		trie = new SpellCheckerTrie(alphabet);
		words = 0;

		try {
			loadWordsFromFile(dictonaryFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**********************************************************************************************
	 * External Methods
	 */

	/**
	 * Determines if {@link #query} is a valid word according to the loaded
	 * dictionary.
	 * 
	 * @param query Query word to search the list of words for.
	 * @return Returns false if {@link #query} was not able to be found in the list
	 *         of words
	 */
	public boolean isWordMispelled(String query) {
		String validatedWord = validateWord(query);
		return !trie.search(validatedWord);
	}

	/***
	 * Given a word, returns a list of spelling suggestions based on the queries
	 * correctly spelled prefix. Suggestions will only be populated if the query is
	 * misspelled.
	 * 
	 * @param query The word to find spelling suggestions for.
	 * @return A list of spelling suggestions based on the queries correctly spelled
	 *         prefix
	 */
	public List<String> getSpellingSuggestions(String query) {
		List<String> suggestions = new ArrayList<String>();
		String validatedWord = validateWord(query);

		trie.getSpellingSuggestions(validatedWord, suggestions);

		return suggestions;
	}

	/**
	 * Determines the number of words in the dictionary used by this spell checker.
	 * 
	 * @return The number of words in the dictionary.
	 */
	public int size() {
		return words;
	}

	/**********************************************************************************************
	 * Word Processing
	 */

	/**
	 * Loads every word in a file located at filePath into {@link #trie}.
	 * 
	 * @param filePath String pointing to a file with one word per line.
	 * @throws FileNotFoundException Thrown if not pointed to a valid file.
	 */
	private void loadWordsFromFile(String filePath) throws FileNotFoundException {
		File wordFile = new File(filePath);
		Scanner scanner = new Scanner(wordFile);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String word = validateWord(line);

			trie.insert(word);
			words++;
		}
	}

	/***
	 * Convert this word to a format the spell checker trie can parse.
	 * 
	 * @param word String to convert
	 * @return Converted, corrected, string that is parsable by the internal Trie
	 *         structure.
	 */
	private String validateWord(String word) {
		return word.toLowerCase();
	}
}

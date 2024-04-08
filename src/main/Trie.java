package main;

public class Trie {

	/**********************************************************************************************
	 * Data
	 */

	// Alphabet this Trie operates on
	private String alphabet = null;

	// Root node of the Trie, created by default, will never be null
	private TrieNode rootNode;

	/**********************************************************************************************
	 * Constructors
	 */

	public Trie() {
		this("abcdefghijklmnopqrstuvwxyz");
	}

	public Trie(String _alphabet) {
		alphabet = _alphabet;
		rootNode = new TrieNode(getAlphabetSize());
	}

	static TrieNode root;

	/**********************************************************************************************
	 * External Methods
	 */

	public void insert(String key) {
		// TODO
		int length = key.length();
		int index;

		TrieNode thisTrie = root;

		for (int level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (thisTrie.childNodes[index] == null) {
				thisTrie.childNodes[index] = new TrieNode(thisTrie.getAlphabetSize());
			}
			thisTrie = thisTrie.childNodes[index];
		}
		thisTrie.endOfWord = true;
	}

	public boolean search(String key) {
		int length = key.length();
		int index;

		TrieNode thisTrie = root;

		for (int level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';

			if (index >= getAlphabetSize() || thisTrie.childNodes[index] == null)
				return false;

			thisTrie = thisTrie.childNodes[index];
		}

		return (thisTrie.endOfWord);
	}

	public void delete() {

	}

	// ETC

	/**********************************************************************************************
	 * Getters
	 */

	public int getAlphabetSize() {
		return alphabet.length();
	}
}

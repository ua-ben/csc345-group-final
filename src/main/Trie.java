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

	/**********************************************************************************************
	 * External Methods
	 */

	public void insert() {
		// TODO
	}

	// ETC

	/**********************************************************************************************
	 * Getters
	 */

	public int getAlphabetSize() {
		return alphabet.length();
	}
}

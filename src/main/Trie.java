package main;

public class Trie {

	/**********************************************************************************************
	 * Data
	 */

	// Alphabet this Trie operates on
	protected String alphabet = null;

	// Root node of the Trie, created by default, will never be null
	protected TrieNode rootNode;

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

	public void insert(String key) {
		int length = key.length();
		int index;

		TrieNode thisTrie = rootNode;

		for (int level = 0; level < length; level++) {
			index = getIndexForCharacter(key.charAt(level));
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

		TrieNode thisTrie = rootNode;

		for (int level = 0; level < length; level++) {
			index = getIndexForCharacter(key.charAt(level));

			if (index >= getAlphabetSize() || thisTrie.childNodes[index] == null)
				return false;

			thisTrie = thisTrie.childNodes[index];
		}

		return (thisTrie.endOfWord);
	}

	/**
	 * Deletes the given key from the Trie.
	 * 
	 * @param key: String/Word to remove.
	 */
	public void delete(String key) {
		rootNode = deleteR(rootNode, key, 0);
	}

	/**
	 * Recursive Portion of Delete function Removes nodes if they have no children
	 * 
	 * @param node:  Current TrieNode
	 * @param key:   String to remove
	 * @param depth: Index in String to check
	 * @return
	 */
	private TrieNode deleteR(TrieNode node, String key, int depth) {
		if (node == null) {
			return null;
		}

		// Check if we are at end of word/key
		if (depth == key.length()) {
			if (node.endOfWord) {
				node.endOfWord = false;
			}

			// If this node is not the parent of an unrelated node, remove.
			if (!node.hasChildren() && node != rootNode) {
				node = null;
			}

			return node;
		}

		int index = getIndexForCharacter(key.charAt(depth));
		node.childNodes[index] = deleteR(node.childNodes[index], key, depth + 1);

		// Check again that the node has not been turned into a leaf
		if (!node.hasChildren() && !node.endOfWord && node != rootNode) {
			node = null;
		}

		return node;
	}

	/**********************************************************************************************
	 * Getters
	 */

	protected int getIndexForCharacter(Character character) {
		return character - alphabet.charAt(0);
	}

	protected char getCharacterFromIndex(int index) {
		return alphabet.charAt(index);
	}

	public int getAlphabetSize() {
		return alphabet.length();
	}
}

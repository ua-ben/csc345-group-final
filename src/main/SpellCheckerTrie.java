package main;

import java.util.ArrayList;
import java.util.List;

/**
 * A Trie that has specific functions designed implement spell checking / word
 * suggestion algorithms.
 */
public class SpellCheckerTrie extends Trie {

	/**********************************************************************************************
	 * Constructors
	 */

	/**
	 * constructor for a spell checker tree, constructs an instance of SpellChecker
	 * using a default alphabet (lower case a-z).
	 */
	public SpellCheckerTrie() {
		super();
	}

	/**
	 * Constructor for a spell checker tree, constructs an instance of SpellChecker
	 * using a custom alphabet (as defined by _alphabet).
	 * 
	 * @param _alphabet
	 */
	public SpellCheckerTrie(String _alphabet) {
		super(_alphabet);
	}

	/**********************************************************************************************
	 * Spell Checker Methods
	 */

	/**
	 * This function gets spelling suggestions based on what words can be completed
	 * with the current inputed letters
	 * 
	 * @param forWord             is the word that has currently been typed in
	 * @param spellingSuggestions is a list of possible words that can be spelled
	 */
	public void getSpellingSuggestions(String forWord, List<String> spellingSuggestions) {
		StringBuilder prefixBuilder = new StringBuilder();
		TrieNode divergentNode = findDivergentNode(forWord, prefixBuilder);

		if (divergentNode == null) {
			// no spelling suggestions
			return;
		}

		gatherSuffixes(divergentNode, prefixBuilder.toString(), spellingSuggestions);
	}

	/**
	 * This function gets spelling suggestions based on what words can be completed
	 * with the current inputed letters
	 * 
	 * @param divergentNode       is the word that has currently been typed in
	 * @param spellingSuggestions is a list of possible words that can be spelled
	 */
	public void getSpellingSuggestions(TrieNode divergentNode, List<String> spellingSuggestions) {
		List<String> suffixList = new ArrayList<String>();

		gatherSuffixes(divergentNode, "", suffixList);
	}

	private void gatherSuffixes(TrieNode node, String suffix, List<String> suffixList) {
		if (node.endOfWord) {
			suffixList.add(suffix);
		}

		for (int i = 0; i < getAlphabetSize(); i++) {
			if (node.childNodes[i] != null) {
				gatherSuffixes(node.childNodes[i], suffix + getCharacterFromIndex(i), suffixList);
			}
		}
	}

	/***
	 * Locates a divergent node in this Trie for a given word. Divergant nodes are
	 * the final node before a word diverges into a word unknown to the tree. This
	 * can be used to find the root note with which to generate spelling suggestions
	 * from. Think of divergent nodes as the last correctly spelled letter in a
	 * word.
	 * 
	 * @param word            String to search for.
	 * @param divergentPrefix StringBuilder instance that will be populated with the
	 *                        contents of @word up to (but not including) the
	 *                        divergent character
	 * @return The divergent node.
	 */
	public TrieNode findDivergentNode(String word, StringBuilder divergentPrefix) {
		int wordLength = word.length();
		int characterIndex = 0;

		TrieNode node = rootNode;

		for (int i = 0; i < wordLength; i++) {
			characterIndex = getIndexForCharacter(word.charAt(i));
			TrieNode nextNode = node.childNodes[characterIndex];

			if (nextNode == null) {
				return node;
			}

			divergentPrefix.append(word.charAt(i));
			node = nextNode;
		}

		if (!node.endOfWord) {
			return node;
		}

		return null;
	}
}

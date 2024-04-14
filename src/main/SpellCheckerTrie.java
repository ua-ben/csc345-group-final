/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public class SpellCheckerTrie extends Trie {

	/**********************************************************************************************
	 * Constructors
	 */

	/**
	 *  constructor for spell checker
	 */
	public SpellCheckerTrie() {
		super();
	}

	/**
	 * @param _alphabet
	 */
	public SpellCheckerTrie(String _alphabet) {
		super(_alphabet);
		// TODO Auto-generated constructor stub
	}

	/**********************************************************************************************
	 * Spell Checker Methods
	 */

	/**
 	 * This function gets spelling suggestions based on what words can be completed with the current inputted letters
	 * @param forWord is the word that has currently been typed in
  	 * @param spellingSuggestions is a list of possible words that can be spelled
	 */
	public void getSpellingSuggestions(String forWord, List<String> spellingSuggestions) {
		StringBuilder prefixBuilder = new StringBuilder();
		TrieNode divergentNode = findDivergentNode(forWord, prefixBuilder);

		if (divergentNode == null) {
			System.out.println("unable to located divergent node");
			// no spelling suggestions
			return;
		}

		gatherSuffixes(divergentNode, prefixBuilder.toString(), spellingSuggestions);
	}

	/**
 	 * This function gets spelling suggestions based on what words can be completed with the current inputted letters
	 * @param divergentNode is the word that has currently been typed in
  	 * @param spellingSuggestions is a list of possible words that can be spelled
	 */
	public void getSpellingSuggestions(TrieNode divergentNode, List<String> spellingSuggestions) {
		List<String> suffixList = new ArrayList<String>();

		gatherSuffixes(divergentNode, "", suffixList);

		for (int i = 0; i < suffixList.size(); i++) {
			System.out.println("found suffix: " + suffixList.get(i));
		}
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

			// TODO: Recursive checks on non-null nodes to determine if they will eventually
			// diverge to an invalid word!

			divergentPrefix.append(word.charAt(i));
			node = nextNode;
		}

		if (!node.endOfWord) {
			return node;
		}

		return null;
	}
}

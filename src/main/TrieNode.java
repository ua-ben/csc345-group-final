package main;

public class TrieNode {

	/**********************************************************************************************
	 * Data
	 */

	protected boolean endOfWord;

	protected TrieNode[] childNodes;

	/**********************************************************************************************
	 * Constructors
	 */

	public TrieNode(int alphabetSize) {
		endOfWord = false;

		childNodes = new TrieNode[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			childNodes[i] = null;
		}
	}

	/**********************************************************************************************
	 * External Methods
	 */

	// TODO: Recursive add node, calculate index from character, etc

	public void setNextNode(int index, TrieNode newNode) {
		assert (index < getAlphabetSize());

		childNodes[index] = newNode;
	}

	/**********************************************************************************************
	 * Getters
	 */

	public int getAlphabetSize() {
		return childNodes.length;
	}
}

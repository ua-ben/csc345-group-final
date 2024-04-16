package main;

/**
 * A node object for a Trie.
 */
public class TrieNode {

	/*---------------------------------------------------------------------------
	 * Data
	 */

	protected boolean endOfWord;

	protected TrieNode[] childNodes;

	/*---------------------------------------------------------------------------
	 * Constructors
	 */

	/**
	 * Creates a new Trie Node with the specified number of available children
	 * indeces.
	 * 
	 * @param alphabetSize Number of child indeces the node should contain.
	 */
	public TrieNode(int alphabetSize) {
		endOfWord = false;

		childNodes = new TrieNode[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			childNodes[i] = null;
		}
	}

	/*---------------------------------------------------------------------------
	 * Debug Methods
	 */

	/**
	 * Debug function returning the list of letters this node houses
	 * 
	 * @param knownAlphabet The alphabet the Trie is operating on
	 * @return String for Debugging
	 */
	public String debugGetChildLetters(String knownAlphabet) {
		String output = null;
		for (int i = 0; i < childNodes.length; i++) {
			output += childNodes[i] != null ? knownAlphabet.charAt(i) : "";
		}
		return output;
	}

	/**
	 * Sets the child at index to direct to the newNode passed into the function.
	 * 
	 * @param index   The index of the current node which will point to the newNode.
	 * @param newNode Node to point to.
	 */
	public void setNextNode(int index, TrieNode newNode) {
		assert (index < getAlphabetSize());

		childNodes[index] = newNode;
	}

	/*---------------------------------------------------------------------------
	 * Getters
	 */

	/**
	 * @return True if TrieNode has at least one child, false if no children exist.
	 */
	public boolean hasChildren() {
		for (int i = 0; i < childNodes.length; i++) {
			if (childNodes[i] != null) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return length of the alphabet
	 */
	public int getAlphabetSize() {
		return childNodes.length;
	}
}

package test;

import main.Trie;
import main.TrieNode;

// Non-JUNIT test for a quick sanity check before I push changes

public class simpleTest {
	public static void main(String[] args) {
		System.out.println("Hello world!");

		Trie t = new Trie("abc");
		TrieNode node = new TrieNode(3);
	}
}

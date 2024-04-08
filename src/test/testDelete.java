package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Trie;



class testDelete {
	Trie trie = new Trie();
	

	@Test
	void testAddSearch() {
		trie.insert("test");
		trie.insert("train");
		trie.insert("television");
		
		assert(trie.search("test") == true);
		assert(trie.search("orange") == false);
		assert(trie.search("tele") == false);
	}
	
	@Test
	void testDeletion() {
		assert(trie.search("test") == true);
		
		trie.delete("test");
		assert(trie.search("test") == false);
		assert(trie.search("television") == true);
		
	}

}

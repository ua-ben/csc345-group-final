# csc345-group-final
Tries

work-bank sourced from : https://github.com/dwyl/english-words

The files under src->main contain:

-Trie.java

-TrieNode.java 

These classes contain the functions necessary to make a Trie function. src->main also contains:

-SpellChecker.java

-SpellCheckerRealtimeDemo.java

-SpellCheckerTrie.java 

This code applies a Trie to make an autocomplete similar to text applications (such as autocomplete on ios).

The files under src->Test contain:

-TestSpellChecker.java

-TestTries.java

-TestTries tests the individual methods (insert, search, delete) of Trie.java and TrieNode.java. 

-TestSpellChecker tests the autocomplete by applying a Trie to a dictionary. Afterwards, the user can enter words into the console. If the user types an invalid/incorrectly spelled word, the autocomplete will look for the last valid input and display a list of words that you may have wanted to type instead.

RUNNING THE CODE

The main application of our Trie is run from TestSpellChecker. By running this code, the console is pulled up, allowing you to enter words into the console. If you spell a word correctly, nothing happens. However, if you spell a word incorrctly, the program will traverse down from where the misspelling occurred and offer suggestions about what words you may have been trying to spell. 

package main;

import java.util.List;
import java.util.Scanner;

public class SpellCheckerRealtimeDemo {

	protected static SpellChecker spellChecker;

	public static void main(String[] args) {
		spellChecker = new SpellChecker("words.txt");
		Scanner userInputScanner = new Scanner(System.in);

		while (userInputScanner.hasNextLine()) {
			checkWord(userInputScanner.nextLine());
		}

		userInputScanner.close();
	}

	static void checkWord(String word) {
		boolean isSpelledCorrectly = !spellChecker.isWordMispelled(word);

		if (isSpelledCorrectly) {
			return;
		}

		List<String> suggestions = spellChecker.getSpellingSuggestions(word);

		System.out.println("Did you mean: ");
		for (int i = 0; i < suggestions.size(); i++) {
			System.out.println("\t" + suggestions.get(i));
		}
	}

}

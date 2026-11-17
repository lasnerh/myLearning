import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		
		String word = "monkey";
		final int MAX_WRONG = 5;
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Character> wordState = new ArrayList<>();
		ArrayList<Character> guessed = new ArrayList<>();
		int wrongGuesses = 0;
		
		for (int i = 0; i < word.length(); i++)
		{
			wordState.add('_');
		}
		
		System.out.println("************************");
		System.out.println("Java Hangman!");
		System.out.println("************************");

		// TODO: refactor the while loop in smaller sep pieces

		while (wrongGuesses < MAX_WRONG)
		{
			System.out.println(getHangmanArt(wrongGuesses));
			
			System.out.print("Word: ");
			for (char c : wordState)
			{
				System.out.print(c + " ");
			}
			System.out.println();
			
			System.out.println("Wrong guesses: " + wrongGuesses + "/" + MAX_WRONG);
			System.out.print("Guess a letter: ");
			
			String input = scanner.next().toLowerCase();
			char guess = input.charAt(0);
			
			if (!Character.isLetter(guess))
			{
				System.out.println("Letters only.");
				continue;
			}
			
			if (guessed.contains(guess))
			{
				System.out.println("You already guessed '" + guess + "'.");
				continue;
			}
			guessed.add(guess);
			
			if (word.indexOf(guess) >= 0)
			{
				System.out.println("Correct!");
				for (int i = 0; i < word.length(); i++)
				{
					if (word.charAt(i) == guess)
					{
						wordState.set(i, guess);
					}
				}
				
				if (!wordState.contains('_'))
				{
					System.out.println(getHangmanArt(wrongGuesses));
					System.out.println("YOU WIN! The word was: " + word);
					scanner.close();
					return;
				}
			}
			else
			{
				wrongGuesses++;
				System.out.println("Wrong!");
			}
		}
		
		System.out.println(getHangmanArt(wrongGuesses));
		System.out.println("GAME OVER! The word was: " + word);
		
		scanner.close();
	}
	
	
	// TODO: replace with enums instead of switch case
	static String getHangmanArt(final int wrongGuesses)
	{
		return switch (wrongGuesses)
		{
		// formatter@off
		case 0 -> """
				
				
				""";
		case 1 -> """
				 o
				
				""";
		case 2 -> """
				 o
				 |
				
				""";
		case 3 -> """
				
				 o
				/|
				
				""";
	
		case 4 -> """
				
				 o
				/|\\
				
				""";
	
		case 5 -> """
				
				 o
				/|\\
				/ \\
				""";
		default -> "";
		};
		// formatter@on
	}
	
}

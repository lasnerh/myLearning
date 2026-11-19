import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	private static final String					WORD			= WordBank.getRandomWord();
	private static final int					MAX_WRONG		= 5;
	
	private static final Scanner				scanner			= new Scanner(System.in);
	private static final ArrayList<Character>	wordState		= new ArrayList<>();
	private static final ArrayList<Character>	guessed			= new ArrayList<>();
	private static int							wrongGuesses	= 0;
	
	public static void main(String[] args)
	{
		initWordState();
		printTitle();
		
		while (wrongGuesses < MAX_WRONG && !isWordGuessed())
		{
			printRound();
			Character guess = readGuess();
			if (guess == null)
			{
				continue;
			}
			processGuess(guess);
		}
		
		printResult();
		scanner.close();
	}
	
	
	
	private static void initWordState()
	{
		for (int i = 0; i < WORD.length(); i++)
		{
			wordState.add('_');
		}
	}
	
	
	
	private static void printTitle()
	{
		System.out.println("************************");
		System.out.println("Java Hangman!");
		System.out.println("************************");
	}
	
	
	
	private static void printRound()
	{
		System.out.println(HangmanStage.fromWrongGuesses(wrongGuesses).getArt());
		
		System.out.print("Word: ");
		for (char c : wordState)
		{
			System.out.print(c + " ");
		}
		System.out.println();
		
		System.out.println("Wrong guesses: " + wrongGuesses + "/" + MAX_WRONG);
	}
	
	
	
	// returns null if the input is invalid or already guessed
	private static Character readGuess()
	{
		System.out.print("Guess a letter: ");
		char guess = scanner.next().toLowerCase().charAt(0);
		
		if (!Character.isLetter(guess))
		{
			System.out.println("Letters only.");
			return null;
		}
		
		if (guessed.contains(guess))
		{
			System.out.println("You already guessed '" + guess + "'.");
			return null;
		}
		
		guessed.add(guess);
		return guess;
	}
	
	
	
	private static void processGuess(char guess)
	{
		if (WORD.indexOf(guess) >= 0)
		{
			System.out.println("Correct!");
			revealLetter(guess);
		}
		else
		{
			wrongGuesses++;
			System.out.println("Wrong!");
		}
	}
	
	
	
	private static void revealLetter(char guess)
	{
		for (int i = 0; i < WORD.length(); i++)
		{
			if (WORD.charAt(i) == guess)
			{
				wordState.set(i, guess);
			}
		}
	}
	
	
	
	private static boolean isWordGuessed()
	{
		return !wordState.contains('_');
	}
	
	
	
	private static void printResult()
	{
		System.out.println(HangmanStage.fromWrongGuesses(wrongGuesses).getArt());
		
		if (isWordGuessed())
		{
			System.out.println("YOU WIN! The word was: " + WORD);
		}
		else
		{
			System.out.println("GAME OVER! The word was: " + WORD);
		}
	}
}

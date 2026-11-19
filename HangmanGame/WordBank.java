import java.util.Random;

public class WordBank
{
	private static final String[]	WORDS	= { "monkey", "elephant", "giraffe", "penguin", "dolphin", "kangaroo", "octopus",
			"crocodile", "squirrel", "hedgehog" };
	private static final Random		random	= new Random();
	
	
	public static String getRandomWord()
	{
		return WORDS[random.nextInt(WORDS.length)];
	}
}

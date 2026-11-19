public enum HangmanStage
{
	// formatter@off
	STAGE_0("""
			
			
			"""), STAGE_1("""
			 o
			
			"""), STAGE_2("""
			 o
			 |
			
			"""), STAGE_3("""
			
			 o
			/|
			
			"""), STAGE_4("""
			
			 o
			/|\\
			
			"""), STAGE_5("""
			
			 o
			/|\\
			/ \\
			""");
	// formatter@on
	
	private final String art;
	
	HangmanStage(String art)
	{
		this.art = art;
	}
	
	
	
	public String getArt()
	{
		return art;
	}
	
	
	
	public static HangmanStage fromWrongGuesses(int wrongGuesses)
	{
		HangmanStage[] stages = values();
		return stages[Math.min(wrongGuesses, stages.length - 1)];
	}
}

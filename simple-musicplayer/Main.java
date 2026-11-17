import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main
{
	public static void main(final String[] args)
	{

		final String FilePath = "audio_example.wav";
		final File file = new File(FilePath);

		try (final Scanner scanner = new Scanner(System.in);
			 final AudioInputStream audioStream = AudioSystem.getAudioInputStream(file))
			{

			final Clip clip = AudioSystem.getClip(); // audio player object
			clip.open(audioStream);

			String response = "";

			while (!response.equals("Q"))
			{
				System.out.println("P = Play");
				System.out.println("S = Stop");
				System.out.println("R = Reset");
				System.out.println("Q = Quit");
				System.out.println("Enter your choice: ");

				response = scanner.next().toUpperCase();

				switch (response)
				{
				case "P" -> clip.start();
				case "S" -> clip.stop();
				case "R" -> clip.setMicrosecondPosition(0);
				case "Q" -> clip.close();
				default -> System.out.println("Invalid choice");
				}
			}
		}

		catch (final FileNotFoundException e)
		{
			System.out.println("Could not locate file");
		}
		catch (final UnsupportedAudioFileException e)
		{
			System.out.println("Audio file format is not supported");
		}
		catch (final LineUnavailableException e)
		{
			System.out.println("Unable to access audio resource");
		}
		catch (final IOException e)
		{
			System.out.println("Something went wrong");
		}
		finally
		{
			System.out.println("Bye");
		}
	}
}

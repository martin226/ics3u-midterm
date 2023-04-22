import arc.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class midterm {
	private static Console con = new Console("The Puzzle Jungle", 1280, 720);
	private static Font fntText = con.loadFont("fonts/Cousine-Regular.ttf", 18);
	private static Color clrGreekVilla = new Color(237, 234, 224);
	private static Color clrSage = new Color(207,225,201,175);
	private static Color clrTranslucent = new Color(0,0,0,50);

	public static void main(String[] args) {
		int intChoice;
		String strChoice;

		String strName;
		int intDice;
		double dblSolution;

		BufferedImage imgTitle = con.loadImage("img/title.png");

		con.drawImage(imgTitle, 0, 0);
		con.repaint();
		con.getKey();
		resetConsole(clrGreekVilla);

		scene1();

		createChoicebox("What would you like to do? (1/2):");
		intChoice = con.readInt();

		// TODO: Input validation function that checks for errors as well
		while (intChoice != 1 && intChoice != 2) {
			con.println("Invalid choice. Please try again.");
			intChoice = con.readInt();
		}	
		resetConsole(clrGreekVilla);
		if (intChoice == 1) {
			scene2();
			con.println(formatInstructions("Enter [yes] if you want to provide the man your name.", "Enter [no] if you do not want to provide the man your name."));
			strChoice = con.readLine();
			while (!strChoice.equalsIgnoreCase("yes") && !strChoice.equalsIgnoreCase("no")) {
				con.println("Invalid choice. Please try again.");
				strChoice = con.readLine();
			}
			if (strChoice.equalsIgnoreCase("yes")) {
				con.println(formatInstructions("Enter your name:"));
				strName = con.readLine();
				resetConsole(clrGreekVilla);
				
				scene5(strName);
				con.println(formatInstructions("Press any key to roll the dice."));
				con.getKey();
				
				intDice = rollDice();
				con.println("You rolled a " + intDice + "!");
				
				if (1 <= intDice && intDice <= 3) {
					scene6();
					con.println(formatInstructions("What is the solution?"));
					dblSolution = con.readDouble();
				} else if (4 <= intDice && intDice <= 6) {
					// Scene 7
				}
			} else if (strChoice.equalsIgnoreCase("no")) {
				resetConsole(clrGreekVilla);
				scene4();
				return;
			}
		} else if (intChoice == 2) {
			scene3();
			return;
		}
	}
	private static void resetConsole(Color clrBg) {
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(clrBg);
		con.fillRect(0,0,1280,720);
	}
	private static String formatInstructions(String... strInstructions) {
		return "\n==============================================================\n" + String.join("\n", strInstructions) + "\n==============================================================\n";
	}
	private static int rollDice() {
		int intDice = (int)(Math.random() * 6 + 1);
		return intDice;
	}
	private static void createTextbox(Color clrText, Color clrBox, Font fntText, String... strText) {
		int intPointSize = fntText.getSize();
		double dblPixelSize = intPointSize * (4.0 / 3.0); // 4/3 is the ratio of pixels to points
		int intWidth = (int)(1280 * 3 / 4);  // 75% of screen width
		int intHeight = 40 + (int)Math.ceil(dblPixelSize * strText.length); // 40 pixels for top and bottom padding, plus the height of the text
		int intX = (int)(1280 / 2 - intWidth / 2); // Centered horizontally
		int intY = (int)(720 * 2 / 3 - intHeight / 2); // Centered vertically
		int intCount;
		con.setDrawColor(clrBox);
		con.setDrawFont(fntText);
		con.fillRoundRect(intX, intY, intWidth, intHeight, 10, 10);
		con.setDrawColor(clrText);
		for (intCount = 0; intCount < strText.length; intCount++) {
			// Draw text:
			// 		15 pixels for left padding
			// 		20 pixels for top padding (bottom padding is leftover from the height calculation)
			con.drawString(strText[intCount], intX + 15, intY + 20 + (int)Math.ceil(dblPixelSize * intCount));
		}
	}
	private static void createChoicebox(String strPrompt) {
		con.print(strPrompt + " ");
		con.setDrawColor(clrTranslucent);
		con.setTextColor(Color.WHITE);
		con.fillRect(0, 0, 1280, 24); // 24 pix is the height of the text
	}
	private static void scene1() {
		BufferedImage imgScene = con.loadImage("img/scene1.png");
		con.drawImage(imgScene, 0, 0);
		createTextbox(
			Color.BLACK, 
			clrSage, 
			fntText,
			"As you awaken, you find yourself in the heart of a dense jungle. Ahead of you lies",
			"a winding path that disappears into the distant horizon, beckoning you to follow",
			"its mysterious course. Standing beside the path, a strange portal emanates a faint,",
			"familiar energy of home. And there, between the two, a weathered sign reads a",
			"cryptic message:",
			"",
			"	Will you dare to explore this unknown wilderness, risking all for the promise",
			"	of untold wealth and discovery? Or will you choose to retreat to the safety",
			"	and familiarity of your home? The answer lies within you, waiting to be",
			"	discovered.",
			"",
			"Enter [1] to follow the path into the jungle.",
			"Enter [2] to return home."
		);
	}
	private static void scene2() {
		con.println("As you walk down the path, a man emerges from the shadows.");
		con.sleep(1000);
		con.println("He introduces himself as a guide, and asks for your name.");
		con.sleep(2000);
	}
	private static void scene3() {
		con.println("You return to the comfort of your home, empty-handed");
		con.sleep(2000);
		con.println("\n-THE END-");
	}
	private static void scene4() {
		con.println("Offended, the guide leaves you to fend for yourself in your unfamiliar surroundings.");
		con.sleep(1000);
		con.println("Eventually, you starve to death.");
		con.sleep(2000);
		con.println("\n-THE END-");
	}
	private static void scene5(String strName) {
		con.println("\"Welcome to the Puzzle Jungle, " + strName + "\", the man says.");
		con.sleep(1000);
		con.println("He takes you through the thick foliage of the jungle, and you arrive at a vault.");
		con.sleep(1000);
		con.println("Next to the vault door is a glass box with a dice inside, and a red button underneath labelled \"ROLL\".");
		con.sleep(1500);
		con.println("Confused, you turn to the guide, but he has disappeared.");
		con.sleep(2000);
	}
	private static void scene6() {
		con.println("Congratulations, you have rolled the easy question.");
		con.sleep(1000);
		con.println("Name the first 3 digits of pi to receive a diamond.");
	}
}

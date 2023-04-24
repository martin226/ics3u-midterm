import arc.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class midterm {
	private static Console con = new Console("The Puzzle Jungle", 1280, 720);
	private static Font fntText = con.loadFont("fonts/Cousine-Regular.ttf", 18);
	private static Color clrGreekVilla = new Color(237, 234, 224);
	private static Color clrGreen = new Color(207,225,201,175);
	private static Color clrYellow = new Color(237,194,88,175);
	private static Color clrTan = new Color(199,143,107,175);
	private static Color clrTranslucent = new Color(0,0,0,50);

	public static void main(String[] args) {
		int intChoice;
		String strChoice;

		String strName;
		int intDice;
		double dblSolution;
		int intSolution;

		BufferedImage imgTitle = con.loadImage("img/title.png");

		con.drawImage(imgTitle, 0, 0);
		con.repaint();
		con.getKey();
		resetConsole(clrGreekVilla);

		scene1();

		createChoicebox("What would you like to do? (1/2):", true);
		intChoice = con.readInt();

		// TODO: Input validation function that checks for errors as well
		while (intChoice != 1 && intChoice != 2) {
			createChoicebox("Invalid choice. What would you like to do? (1/2):", false);
			intChoice = con.readInt();
		}	
		resetConsole(clrGreekVilla);
		if (intChoice == 1) {
			scene2();
			
			createChoicebox("Do you want to enter your name? (yes/no):", true);
			strChoice = con.readLine();
			while (!strChoice.equalsIgnoreCase("yes") && !strChoice.equalsIgnoreCase("no")) {
				createChoicebox("Invalid choice. Do you want to enter your name? (yes/no):", false);
				strChoice = con.readLine();
			}
			if (strChoice.equalsIgnoreCase("yes")) {
				createChoicebox("Enter your name:", false); // don't draw the box since last one was not destroyed
				strName = con.readLine();
				resetConsole(clrGreekVilla);
				
				scene5(strName);
				con.getKey();
				
				intDice = rollDice();
				createAlert(Color.BLACK, clrTan, 200, "You rolled a " + intDice + "!");
				con.sleep(3000);
				
				if (1 <= intDice && intDice <= 3) {
					scene6();
					createChoicebox("Enter your answer:", true);
					dblSolution = con.readDouble();
					resetConsole(clrGreekVilla);
					if (dblSolution != 3.14) {
						scene8();
						con.getKey();
						con.closeConsole();
						return;
					}
				} else if (4 <= intDice && intDice <= 6) {
					scene7();
					createChoicebox("Enter your answer:", true);
					intSolution = con.readInt();
					resetConsole(clrGreekVilla);
					if (intSolution <= 1000 || !perfectSquare(intSolution)) {
						scene8();
						con.getKey();
						con.closeConsole();
						return;
					}
				}
			} else if (strChoice.equalsIgnoreCase("no")) {
				resetConsole(clrGreekVilla);
				scene4();
				con.getKey();
				con.closeConsole();
				return;
			}
		} else if (intChoice == 2) {
			scene3();
			con.getKey();
			con.closeConsole();
			return;
		}
	}
	private static void resetConsole(Color clrBg) {
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(clrBg);
		con.fillRect(0,0,1280,720);
	}
	private static int rollDice() {
		int intDice = (int)(Math.random() * 6 + 1);
		return intDice;
	}
	private static boolean perfectSquare(int intNum) {
		return Math.sqrt(intNum) % 1 == 0;
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
		con.repaint();
	}
	private static void createChoicebox(String strPrompt, boolean blnDrawBox) {
		con.clear();
		con.print(strPrompt + " ");
		if (blnDrawBox) {
			con.setDrawColor(clrTranslucent);
			con.setTextColor(Color.WHITE);
			con.fillRect(0, 0, 1280, 24); // 24 pix is the height of the text
			con.repaint();
		}
	}
	private static void createAlert(Color clrText, Color clrBox, int intWidth, String strAlert) {
		con.clear();
		con.setDrawColor(clrBox);
		con.fillRoundRect(1280 - intWidth, 0, intWidth, 50, 10, 10);
		con.setDrawColor(clrText);
		con.drawString(strAlert, 1280 - intWidth + 10, 12);
		con.repaint();
	}
	private static void scene1() {
		BufferedImage imgScene = con.loadImage("img/scene1.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK, 
			clrGreen, 
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
		BufferedImage imgScene = con.loadImage("img/scene2.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrYellow,
			fntText,
			"You decide to follow the path into the jungle. As you walk, you notice that",
			"the jungle is eerily quiet. You hear no birds, no insects, no animals. You",
			"begin to wonder if you are alone in this strange place. Suddenly, you hear",
			"a faint voice calling out to you from the shadows. A man emerges from the",
			"trees, and introduces himself as a guide. He asks for your name.",
			"",
			"Enter [yes] if you want to provide the man your name.",
			"Enter [no] if you want to remain anonymous."
		);
	}
	private static void scene3() {
		BufferedImage imgScene = con.loadImage("img/scene3.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"You return to the comfort of your home, empty-handed. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene4() {
		BufferedImage imgScene = con.loadImage("img/scene4.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"You decide to remain anonymous. The guide is offended by your lack of trust,",
			"and leaves you to fend for yourself in your unfamiliar surroundings. Eventually,",
			"you starve to death. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene5(String strName) {
		BufferedImage imgScene = con.loadImage("img/scene5.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"\"Welcome to the Puzzle Jungle, " + strName + "\", the man says.",
			"",
			"He takes you through the thick foliage of the jungle, and you arrive",
			"at a vault. Hanging next to the vault door is a giant dice and a red",
			"button underneath labelled \"ROLL\". Confused, you turn to the guide,",
			"but he has disappeared.",
			"",
			"Press any key to roll the dice."
		);

	}
	private static void scene6() {
		BufferedImage imgScene = con.loadImage("img/scene6.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"Congratulations, you have rolled the easy question.",
			"",
			"Name the first 3 digits of pi to receive a diamond."
		);
	}
	private static void scene7() {
		BufferedImage imgScene = con.loadImage("img/scene7.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"You have rolled the hard question.",
			"",
			"Give any number larger than 1,000 that has an odd number of",
			"factors to receive a diamond."
		);
	}
	private static void scene8() {
		BufferedImage imgScene = con.loadImage("img/scene8.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"Wrong answer! You trigger a trap and an arrow flies out of the",
			"wall, piercing your heart. You die. THE END.",
			"",
			"Press any key to exit."
		);
	}
}

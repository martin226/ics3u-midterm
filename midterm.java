// The Puzzle Jungle
// Author: Martin Sit
// ICS 3U1
// Version 2023.04.25

import arc.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class midterm {
	private static Console con = new Console("The Puzzle Jungle", 1280, 720);
	private static Font fntText = con.loadFont("fonts/Cousine-Regular.ttf", 18);
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
		double dblVelocity;
		String strPassword;
		boolean blnSecure;

		BufferedImage imgTitle = con.loadImage("img/title.png");

		con.drawImage(imgTitle, 0, 0);
		con.repaint();
		con.getKey();
		con.clear();

		scene1();

		createChoicebox("What would you like to do? (1/2):", true);
		intChoice = con.readInt();

		// Validate input
		while (intChoice != 1 && intChoice != 2) {
			createChoicebox("Invalid choice. What would you like to do? (1/2):", false);
			intChoice = con.readInt();
		}	
		con.clear();

		// If the user chooses to play the game
		if (intChoice == 1) {
			scene2();
			
			createChoicebox("Do you want to enter your name? (yes/no):", true);
			strChoice = con.readLine();

			// Validate input
			while (!strChoice.equalsIgnoreCase("yes") && !strChoice.equalsIgnoreCase("no")) {
				createChoicebox("Invalid choice. Do you want to enter your name? (yes/no):", false);
				strChoice = con.readLine();
			}

			// If the user chooses to enter their name
			if (strChoice.equalsIgnoreCase("yes")) {
				createChoicebox("Enter your name:", false); // don't draw the box since last one was not destroyed
				strName = con.readLine();
				con.clear();
				
				scene5(strName);
				con.getKey();
				
				intDice = rollDice();
				createAlert(Color.BLACK, clrTan, 200, "You rolled a " + intDice + "!");
				con.sleep(3000);
				
				// If the user rolls a 1, 2, or 3
				if (1 <= intDice && intDice <= 3) {
					scene6();
					createChoicebox("Enter your answer:", true);
					dblSolution = con.readDouble();
					con.clear();

					// Eliminates all numbers that are not the correct answer, 3.14
					if (dblSolution != 3.14) {
						scene8();
						con.getKey();
						con.closeConsole();
						return;
					}

				// If the user rolls a 4, 5, or 6
				} else if (4 <= intDice && intDice <= 6) {
					scene7();
					createChoicebox("Enter your answer:", true);
					intSolution = con.readInt();
					con.clear();

					// A number has an odd number of factors if it is a perfect square (since the square root is a factor)
					// This if statement eliminates all numbers less than or equal to 1000
					// and all non-perfect squares
					if (intSolution <= 1000 || !perfectSquare(intSolution)) {
						scene8();
						con.getKey();
						con.closeConsole();
						return;
					}
				}

				scene9();
				createChoicebox("What would you like to do? (1/2):", true);
				intChoice = con.readInt();

				// Validate input
				while (intChoice != 1 && intChoice != 2) {
					createChoicebox("Invalid choice. What would you like to do? (1/2):", false);
					intChoice = con.readInt();
				}
				con.clear();

				// If the user chooses to store the diamond in the vault
				if (intChoice == 1) {
					scene10();
					createChoicebox("Enter your password:", true);
					strPassword = con.readLine();
					blnSecure = passwordIsSecure(strPassword);
					con.clear();

					// If the password is not secure, the user loses the game
					if (!blnSecure) {
						scene11();

					// If the password is secure, the user wins the game
					} else {
						scene12();
					}
					con.getKey();
					con.closeConsole();
					return;

				// If the user chooses to carry the diamond
				} else if (intChoice == 2) {
					scene13();
					createChoicebox("Enter your answer:", true);
					dblVelocity = con.readDouble();
					con.clear();

					// If the user enters the correct velocity, they win the game
					if (dblVelocity == 7.07) {
						scene16();

					// If the user enters an incorrect velocity, they lose the game
					} else if (dblVelocity < 7.07) { // Too slow
						scene14();
					} else if (dblVelocity > 7.07) { // Too fast
						scene15();
					}
					con.getKey();
					con.closeConsole();
					return;
				}

			// If the user chooses not to enter their name
			} else if (strChoice.equalsIgnoreCase("no")) {
				con.clear();
				scene4();
				con.getKey();
				con.closeConsole();
				return;
			}
		
		// If the user chooses to exit the game
		} else if (intChoice == 2) {
			scene3();
			con.getKey();
			con.closeConsole();
			return;
		}
	}
	private static int rollDice() {
		int intDice = (int)(Math.random() * 6 + 1);
		return intDice;
	}
	private static boolean perfectSquare(int intNum) {
		// A number is a perfect square if its square root is an integer
		return Math.sqrt(intNum) % 1 == 0;
	}
	private static boolean passwordIsSecure(String strPassword) {
		// A password is secure if it:
		// - is at least 8 characters long
		// - contains at least one uppercase letter
		// - contains at least one lowercase letter
		// - contains at least one number
		// - contains at least one special character
		boolean blnHasUppercase = false;
		boolean blnHasLowercase = false;
		boolean blnHasNumber = false;
		boolean blnHasSpecial = false;
		for (int intCount = 0; intCount < strPassword.length(); intCount++) {
			char chrCurrent = strPassword.charAt(intCount);
			if (Character.isUpperCase(chrCurrent)) { // If the current character is uppercase
				blnHasUppercase = true;
			} else if (Character.isLowerCase(chrCurrent)) { // If the current character is lowercase
				blnHasLowercase = true;
			} else if (Character.isDigit(chrCurrent)) { // If the current character is a number
				blnHasNumber = true;
			} else if (!Character.isLetterOrDigit(chrCurrent)) { // If the current character is a special character
				blnHasSpecial = true;
			}
		}
		return strPassword.length() >= 8 && blnHasUppercase && blnHasLowercase && blnHasNumber && blnHasSpecial;
	}
	private static void createTextbox(Color clrText, Color clrBox, Font fntText, String... strText) {
		// Draws a textbox with the specified text
		// Calculates the size of the textbox based on the length of the text
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
		// Draws a choicebox with the specified prompt
		con.clear();
		con.print(strPrompt + " ");
		if (blnDrawBox) { // If the choicebox should be drawn
			con.setDrawColor(clrTranslucent);
			con.setTextColor(Color.WHITE);
			con.fillRect(0, 0, 1280, 24); // 24 pix is the height of the text
			con.repaint();
		}
	}
	private static void createAlert(Color clrText, Color clrBox, int intWidth, String strAlert) {
		// Draws an alert with the specified text
		con.clear();
		con.setDrawColor(clrBox);
		con.fillRoundRect(1280 - intWidth, 0, intWidth, 50, 10, 10);
		con.setDrawColor(clrText);
		con.drawString(strAlert, 1280 - intWidth + 10, 12);
		con.repaint();
	}
	private static void animateDiamonds(BufferedImage imgScene) {
		/* Animation - diamonds falling down */
		// There will be 5 diamonds on the screen in the beginning
		// Each will have their own x and y coordinates
		// End when all diamonds are off the screen
		BufferedImage imgDiamond = con.loadImage("img/diamond.png");
		int intSpeed = 10;
		int intWidth = 1280;
		int intHeight = 720 + 54;

		// Initialize the x coordinates of the diamonds randomly
		int intX1 = (int)(Math.random() * intWidth);
		int intX2 = (int)(Math.random() * intWidth);
		int intX3 = (int)(Math.random() * intWidth);
		int intX4 = (int)(Math.random() * intWidth);
		int intX5 = (int)(Math.random() * intWidth);

		// Space out the heights of the diamonds so they don't all appear at once
		int intY1 = 0;
		int intY2 = 144;
		int intY3 = 288;
		int intY4 = 432;
		int intY5 = 576;

		// Loop until all diamonds are off the screen
		while (intY1 < intHeight || intY2 < intHeight || intY3 < intHeight || intY4 < intHeight || intY5 < intHeight) {
			con.drawImage(imgScene, 0, 0);

			con.drawImage(imgDiamond, intX1, intY1);
			con.drawImage(imgDiamond, intX2, intY2);
			con.drawImage(imgDiamond, intX3, intY3);
			con.drawImage(imgDiamond, intX4, intY4);
			con.drawImage(imgDiamond, intX5, intY5);

			intY1 += intSpeed;
			intY2 += intSpeed;
			intY3 += intSpeed;
			intY4 += intSpeed;
			intY5 += intSpeed;

			con.repaint();
			con.sleep(33);
		}
	}
	private static void animateSliding(BufferedImage imgScene1, BufferedImage imgScene2) {
		/* Animation - sliding images */
		// Start with imgScene1 and slide it to the left while imgScene2 slides in from the right
		int intCount;
		int intWidth = 1280;
		int intSpeed = 40; // factor of 1280

		con.drawImage(imgScene1, 0, 0);
		con.repaint();
		con.sleep(1000);

		for (intCount = 0; intCount <= intWidth; intCount += intSpeed) {
			con.drawImage(imgScene1, -intCount, 0);
			con.drawImage(imgScene2, intWidth - intCount, 0);
			con.repaint();
			con.sleep(33);
		}
	}
	private static void scene1() {
		// Scene 1 - Introduction
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
		// Scene 2 - The Guide
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
		// Scene 3 - Return Home
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
		// Scene 4 - Anonymity
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
		// Scene 5 - The Vault and the Dice
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
		// Scene 6 - Easy Question
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
		// Scene 7 - Hard Question
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
		// Scene 8 - Vault Trap
		BufferedImage imgScene = con.loadImage("img/scene8.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();

		/* Animation - arrows flying at person */
		// There will be 3 arrows on the screen, starting from the right
		// Each will have their own x and y coordinates
		// End when all arrows reach the person
		BufferedImage imgArrow = con.loadImage("img/arrow.png");
		int intSpeed = 10;
		int intWidth = 1280;
		int intLocation = 500;
		
		// Initialize the x coordinates of the arrows randomly
		// Allows for non-uniform strike
		int intX1 = intWidth - (int)(Math.random() * 100);
		int intX2 = intWidth - (int)(Math.random() * 100);
		int intX3 = intWidth - (int)(Math.random() * 100);

		// Range of 320-420
		int intY1 = (int)(Math.random() * 100) + 320;
		int intY2 = (int)(Math.random() * 100) + 320;
		int intY3 = (int)(Math.random() * 100) + 320;

		// Loop until one of the arrows reaches the person
		while (intX1 > intLocation && intX2 > intLocation && intX3 > intLocation) {
			con.drawImage(imgScene, 0, 0);

			con.drawImage(imgArrow, intX1, intY1);
			con.drawImage(imgArrow, intX2, intY2);
			con.drawImage(imgArrow, intX3, intY3);

			intX1 -= intSpeed;
			intX2 -= intSpeed;
			intX3 -= intSpeed;

			con.repaint();
			con.sleep(33);
		}

		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"Wrong answer! You trigger a trap and an arrow flies out of the",
			"wall, piercing you. You die. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene9() {
		// Scene 9 - Treasure of the Jungle
		BufferedImage imgScene = con.loadImage("img/scene9.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		animateDiamonds(imgScene);
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"The vault door swings open, revealing a room filled with gold and",
			"a single, large diamond. Do you want to keep the diamond in the vault",
			"for storage or take it with you?",
			"",
			"Enter [1] to keep the diamond in the vault.",
			"Enter [2] to take the diamond with you."
		);
	}
	private static void scene10() {
		// Scene 10 - Vault Password
		BufferedImage imgScene = con.loadImage("img/scene10.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"As you close the vault, a keyboard pops out of the outer wall. The sign's text changes to:",
			"\"Choose a secure password for the vault.\"",
			"",
			"Enter a password for the vault. Make sure it is secure!"
		);
	}
	private static void scene11() {
		// Scene 11 - Bad Password
		BufferedImage imgScene = con.loadImage("img/scene11.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"Your password wasn't secure enough! You find out later that your vault has been",
			"robbed and your precious diamond has been stolen. You lose morale and motivation to",
			"continue your journey. You die. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene12() {
		// Scene 12 - Good Password
		BufferedImage imgScene1 = con.loadImage("img/scene12_pt1.png");
		BufferedImage imgScene2 = con.loadImage("img/scene12_pt2.png");

		animateSliding(imgScene1, imgScene2);
		animateDiamonds(imgScene2);

		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"You continue on your journey, gathering various treasures and seeing",
			"breathtaking sights along the way. Then, you make your way back to the portal", 
			"and return home with all your newfound riches. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene13() {
		// Scene 13 - Zombies
		BufferedImage imgScene = con.loadImage("img/scene13.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();

		/* Animation - zombies chasing person */
		// There will be 3 zombies on the screen, starting from the right
		// Each will have their own x and y coordinates
		// End when one zombie reaches the middle of the screen
		// The zombies start from the same cluster and accelerate at 1 pixel per frame squared
		BufferedImage imgZombie = con.loadImage("img/zombie.png");
		int intAcceleration = 1;
		int intSpeed = 0;
		int intWidth = 1280;
		int intLocation = intWidth / 2;
		
		// Initialize the x coordinates of the zombies randomly between 1000 and 1280
		int intX1 = (int)(Math.random() * 280) + 1000;
		int intX2 = (int)(Math.random() * 280) + 1000;
		int intX3 = (int)(Math.random() * 280) + 1000;

		// Initialize the y coordinates of the zombies randomly between 180 and 280
		int intY1 = (int)(Math.random() * 100) + 180;
		int intY2 = (int)(Math.random() * 100) + 180;
		int intY3 = (int)(Math.random() * 100) + 180;

		// Loop until one of the zombies reaches the middle of the screen
		while (intX1 > intLocation && intX2 > intLocation && intX3 > intLocation) {
			con.drawImage(imgScene, 0, 0);

			con.drawImage(imgZombie, intX1, intY1);
			con.drawImage(imgZombie, intX2, intY2);
			con.drawImage(imgZombie, intX3, intY3);

			intSpeed += intAcceleration;

			intX1 -= intSpeed;
			intX2 -= intSpeed;
			intX3 -= intSpeed;

			con.repaint();
			con.sleep(33);
		}

		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"As you close the vault door, you hear a loud moan. You turn around",
			"and see three zombies running towards you at a constant acceleration of 1 m/s^2.",
			"Desperate, you look around your surroundings and seem to see shelter 100 m away.",
			"",
			"What is the minimum velocity you need to run at in order to escape the zombies?",
			"Assume the zombies start from rest and that you can run at a constant velocity.",
			"Provide your answer to 2 decimal places."
		);
	}
	private static void scene14() {
		// Scene 14 - Too Slow
		BufferedImage imgScene = con.loadImage("img/scene14.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"Too slow! The zombies catch up to you and eat you alive. You die. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene15() {
		// Scene 15 - Too Fast
		BufferedImage imgScene = con.loadImage("img/scene15.png");
		con.drawImage(imgScene, 0, 0);
		con.repaint();
		createTextbox(
			Color.BLACK,
			clrTan,
			fntText,
			"Too fast! You get tired and stop to rest in the middle of the way to shelter.",
			"The zombies catch up to you and eat you alive. You die. THE END.",
			"",
			"Press any key to exit."
		);
	}
	private static void scene16() {
		// Scene 16 - Correct Velocity
		BufferedImage imgScene1 = con.loadImage("img/scene16_pt1.png");
		BufferedImage imgScene2 = con.loadImage("img/scene16_pt2.png");

		animateSliding(imgScene1, imgScene2);
		animateDiamonds(imgScene2);

		createTextbox(
			Color.BLACK,
			clrGreen,
			fntText,
			"You reach shelter safely. You stay there for a few hours, making",
			"sure that all the zombies have left. Then, you make your way back to the", 
			"portal and return home with your diamond. THE END.",
			"",
			"Press any key to exit."
		);
	}
}

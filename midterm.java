import arc.*;
import java.awt.Color;
import java.awt.Font;

public class midterm {
	public static void main(String[] args) {
		Console con = new Console("The Puzzle Jungle", 1280, 720);

		int intChoice;
		String strChoice;
		Font fntTitle = con.loadFont("fonts/Cousine-Regular.ttf", 16);
		Font fntText = con.loadFont("fonts/NotoSans-Regular.ttf", 18);
		Color clrGreekVilla = new Color(237, 234, 224);
		
		String strName;
		int intDice;

		resetConsole(con, clrGreekVilla);

		con.setTextFont(fntTitle);
		printTitle(con);
		con.println("Press any key to start...");
		con.getKey();
		resetConsole(con, clrGreekVilla);

		con.setTextFont(fntText);
		scene1(con);
		con.println("\n==============================================================");
		con.println("Enter [1] to follow the path into the jungle.");
		con.println("Enter [2] to return home.");
		con.println("==============================================================\n");
		intChoice = con.readInt();
		// TODO: Input validation function that checks for errors as well
		while (intChoice != 1 && intChoice != 2) {
			con.println("Invalid choice. Please try again.");
			intChoice = con.readInt();
		}	
		resetConsole(con, clrGreekVilla);
		if (intChoice == 1) {
			scene2(con);
			con.println("\n==============================================================");
			con.println("Enter [yes] if you want to provide the man your name.");
			con.println("Enter [no] if you do not want to provide the man your name.");
			con.println("==============================================================\n");
			strChoice = con.readLine();
			while (!strChoice.equalsIgnoreCase("yes") && !strChoice.equalsIgnoreCase("no")) {
				con.println("Invalid choice. Please try again.");
				strChoice = con.readLine();
			}
			if (strChoice.equalsIgnoreCase("yes")) {
				con.println("\n==============================================================");
				con.println("Enter your name:");
				con.println("==============================================================\n");
				strName = con.readLine();
				resetConsole(con, clrGreekVilla);
				
				scene5(con, strName);
				con.println("\n==============================================================");
				con.println("Press any key to roll the dice.");
				con.println("==============================================================\n");
				con.getKey();
				
				intDice = rollDice();
				con.println("You rolled a " + intDice + "!");
			} else if (strChoice.equalsIgnoreCase("no")) {
				resetConsole(con, clrGreekVilla);
				scene4(con);
				return;
			}
		} else if (intChoice == 2) {
			scene3(con);
			return;
		}
	}
	private static void resetConsole(Console con, Color clrBg) {
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(clrBg);
		con.fillRect(0,0,1280,720);
	}
	private static void printTitle(Console con) {
		con.println("████████╗██╗░░██╗███████╗  ██████╗░██╗░░░██╗███████╗███████╗██╗░░░░░███████╗  ░░░░░██╗██╗░░░██╗███╗░░██╗░██████╗░██╗░░░░░███████╗");
		con.println("╚══██╔══╝██║░░██║██╔════╝  ██╔══██╗██║░░░██║╚════██║╚════██║██║░░░░░██╔════╝  ░░░░░██║██║░░░██║████╗░██║██╔════╝░██║░░░░░██╔════╝");
		con.println("░░░██║░░░███████║█████╗░░  ██████╔╝██║░░░██║░░███╔═╝░░███╔═╝██║░░░░░█████╗░░  ░░░░░██║██║░░░██║██╔██╗██║██║░░██╗░██║░░░░░█████╗░░");
		con.println("░░░██║░░░██╔══██║██╔══╝░░  ██╔═══╝░██║░░░██║██╔══╝░░██╔══╝░░██║░░░░░██╔══╝░░  ██╗░░██║██║░░░██║██║╚████║██║░░╚██╗██║░░░░░██╔══╝░░");
		con.println("░░░██║░░░██║░░██║███████╗  ██║░░░░░╚██████╔╝███████╗███████╗███████╗███████╗  ╚█████╔╝╚██████╔╝██║░╚███║╚██████╔╝███████╗███████╗");
		con.println("░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ╚═╝░░░░░░╚═════╝░╚══════╝╚══════╝╚══════╝╚══════╝  ░╚════╝░░╚═════╝░╚═╝░░╚══╝░╚═════╝░╚══════╝╚══════╝");
		con.println("\n");
	}
	private static int rollDice() {
		int intDice = (int)(Math.random() * 6 + 1);
		return intDice;
	}
	private static void scene1(Console con) {
		// TODO: Add scene image
		con.println("As you awaken, you find yourself in the heart of a dense jungle.");
		con.sleep(1000);
		con.println("Ahead of you lies a winding path that disappears into the distant horizon, beckoning you to follow its mysterious course.");
		con.sleep(1500);
		con.println("Standing beside the path, a strange portal emanates a faint, familiar energy of home.");
		con.sleep(1000);
		con.println("And there, between the two, a weathered sign reads a cryptic message:\n");
		con.sleep(1000);
		con.println("	Will you dare to explore this unknown wilderness,");
		con.println("	risking all for the promise of untold wealth and discovery?");
		con.println("	Or will you choose to retreat to the safety and familiarity of your home?");
		con.println("	The answer lies within you, waiting to be discovered.");
		con.sleep(2000);
	}
	private static void scene2(Console con) {
		con.println("As you walk down the path, a man emerges from the shadows.");
		con.sleep(1000);
		con.println("He introduces himself as a guide, and asks for your name.");
		con.sleep(2000);
	}
	private static void scene3(Console con) {
		con.println("You return to the comfort of your home, empty-handed");
		con.sleep(2000);
		con.println("\n-THE END-");
	}
	private static void scene4(Console con) {
		con.println("Offended, the guide leaves you to fend for yourself in your unfamiliar surroundings.");
		con.sleep(1000);
		con.println("Eventually, you starve to death.");
		con.sleep(2000);
		con.println("\n-THE END-");
	}
	private static void scene5(Console con, String strName) {
		con.println("\"Welcome to the Puzzle Jungle, " + strName + "\", the man says.");
		con.sleep(1000);
		con.println("He takes you through the thick foliage of the jungle, and you arrive at a vault.");
		con.sleep(1000);
		con.println("Next to the vault door is a glass box with a dice inside, and a red button underneath labelled \"ROLL\".");
		con.sleep(1500);
		con.println("Confused, you turn to the guide, but he has disappeared.");
		con.sleep(2000);
	}
}

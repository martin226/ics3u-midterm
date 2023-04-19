import arc.*;
import java.awt.Color;
import java.awt.Font;

public class midterm {
	public static void main(String[] args) {
		Console con = new Console("The Puzzle Jungle", 1280, 720);

		int intChoice;

		resetConsole(con);

		Font fntTitle = con.loadFont("fonts/Cousine-Regular.ttf", 16);
		con.setTextFont(fntTitle);
		printTitle(con);
		con.println("Press any key to start...");
		con.getKey();
		resetConsole(con);

		Font fntText = con.loadFont("fonts/NotoSans-Regular.ttf", 18);
		con.setTextFont(fntText);
		scene1(con);
		con.println("==============================================================");
		con.println("Enter [1] to follow the path into the jungle.");
		con.println("Enter [2] to return home.");
		con.println("==============================================================");
		intChoice = con.readInt();
		// TODO: Input validation function that checks for errors as well
		while (intChoice != 1 && intChoice != 2) {
			con.println("Invalid choice. Please try again.");
			intChoice = con.readInt();
		}	
		if (intChoice == 1) {
			// TODO: Add scene 2
		} else if (intChoice == 2) {
			// TODO: Add scene 3
		}
	}
	private static void resetConsole(Console con) {
		Color clrGreekVilla = new Color(237, 234, 224);
		con.clear();
		con.setTextColor(Color.BLACK);
		con.setDrawColor(clrGreekVilla);
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
	private static void scene1(Console con) {
		// TODO: Add scene image
		con.println("As you awaken, you find yourself in the heart of a dense jungle.");
		con.println("Ahead of you lies a winding path that disappears into the distant horizon,");
		con.println("beckoning you to follow its mysterious course.");
		con.println("Standing beside the path, a strange portal emanates a faint, familiar energy of home.");
		con.println("And there, between the two, a weathered sign reads a cryptic message:\n");
		con.println("	Will you dare to explore this unknown wilderness,");
		con.println("	risking all for the promise of untold wealth and discovery?");
		con.println("	Or will you choose to retreat to the safety and familiarity of your home?");
		con.println("	The answer lies within you, waiting to be discovered.");		
		con.println();
	}
}

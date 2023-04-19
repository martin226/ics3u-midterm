import arc.*;
import java.awt.Color;
import java.awt.Font;

public class midterm {
	public static void main(String[] args) {
		Console con = new Console("The Puzzle Jungle", 1280, 720);
		resetConsole(con);

		Font fntTitle = con.loadFont("fonts/Cousine-Regular.ttf", 16);
		con.setTextFont(fntTitle);
		printTitle(con);
		con.println("Press any key to start...");
		con.getKey();
		resetConsole(con);
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
}

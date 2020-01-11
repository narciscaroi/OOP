package main;

import input.Input;
import input.InputLoad;
import input.Player;
import output.Output;
import rounds.Round;

import java.util.ArrayList;

public final class Main {
    private Main() {
            //just to trick checkstyle
    }
    public static void main(final String[] args) {

        InputLoad inputLoad = new InputLoad();
	Input input = inputLoad.load(args[0], args[1]); //args[0] , args[1]

            System.out.println("-----------------CITIRE----------");
        int coordN = input.getCoordN();
        int coordM = input.getCoordM();
        char[][] mapType = new char[coordN][coordM];
        ArrayList<Player> players = new ArrayList<>();
        String[] mapTypeString = new String[coordN];
        int numberOfPlayers = input.getNumberOfPlayers();
        int numberOfRounds = input.getNumberOfRounds();
        char[][] playersMoves = new char[numberOfRounds][numberOfPlayers];

	System.out.println(input.getCoordN() + " " + input.getCoordM());
	for (int i = 0; i < input.getCoordN(); i++)  {
            for (int j = 0; j < input.getCoordM(); j++) {
                mapType[i][j] = input.getMapType()[i][j];
                System.out.print(mapType[i][j]);
            }
                System.out.println();
	}
	System.out.println(input.getNumberOfPlayers());
	for (int i = 0; i < input.getNumberOfPlayers(); i++) {
                players.add(input.getPlayers().get(i));

	     System.out.println(players.get(i).getRace() + " "
		+ players.get(i).getLinePosition() + " "
		+ players.get(i).getColumnPosition());

	}
	System.out.println(input.getNumberOfRounds());
	for (int i = 0; i < input.getNumberOfRounds(); i++) {
	        for (int j = 0; j < input.getNumberOfPlayers(); j++) {
                        playersMoves[i][j] = input.getPlayersMoves()[i][j];

	                System.out.print(playersMoves[i][j]);
		}
		System.out.println();
	}
	System.out.println("----------END ____ CITIRE--------");

         Round round = new Round();
         round.createGame(numberOfRounds, numberOfPlayers, players,
               playersMoves, mapType);
         String dead = "dead";

          Output output = new Output();
         output.writer(args[0], args[1], numberOfPlayers, players, dead);
    }
}

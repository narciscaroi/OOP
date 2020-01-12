package main;

import input.Angels;
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

        //Input input = inputLoad.load(inputPath, outputPath); //args[0] , args[1]
        Input input = inputLoad.load(args[0], args[1]);

            System.out.println("-----------------CITIRE----------");
        int coordN = input.getCoordN();
        int coordM = input.getCoordM();
        char[][] mapType = new char[coordN][coordM];
        ArrayList<Player> players = new ArrayList<>();
        String[] mapTypeString = new String[coordN];
        int numberOfPlayers = input.getNumberOfPlayers();
        int numberOfRounds = input.getNumberOfRounds();
        char[][] playersMoves = new char[numberOfRounds][numberOfPlayers];
        ArrayList<ArrayList<Angels>> listOfAngels = new ArrayList<>(numberOfRounds);
        ArrayList<Integer> noAngelsRounds = new ArrayList<>();

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

	for (int i = 0; i < numberOfRounds; i++) {
	        noAngelsRounds.add(input.getNoAngelsRounds().get(i));
	        System.out.print(noAngelsRounds.get(i));
                listOfAngels.add(i, new ArrayList<Angels>());
	        if (noAngelsRounds.get(i) > 0) {
                        for (int j = 0; j < input.getListOfAngels().get(i).size(); j++) {
                                listOfAngels.get(i).add(j, input.getListOfAngels().get(i).get(j));
                                System.out.print(" " + input.getListOfAngels().get(i).
                                        get(j).getType() + ","
                                        + listOfAngels.get(i).get(j).getLinePosition() + ","
                                        + listOfAngels.get(i).get(j).getColumnPosition());
                        }
                }
	        System.out.println();
        }
	System.out.println("----------END ____ CITIRE--------");

        ArrayList<ArrayList<String>> observerRounds = new ArrayList<>(numberOfRounds);
        for (int i = 0; i < numberOfRounds; i++) {
                observerRounds.add(i, new ArrayList<String>());
        }
         Round round = new Round();
         round.createGame(numberOfRounds, numberOfPlayers, players,
               playersMoves, mapType, noAngelsRounds, listOfAngels, observerRounds);
         String dead = "dead";

         Output output = new Output();
         output.writer(args[0], args[1], numberOfPlayers, numberOfRounds, players, dead,
                       observerRounds);
    }
}

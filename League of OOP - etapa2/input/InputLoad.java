package input;

import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

public class InputLoad {
        public InputLoad() {

        }


        /**
         * <h1>Citirea din fisier</h1>
         * Am facut harta si miscarile playerilor ca doua matrici de char-uri.
         * Playerii in retin intr-un ArrayList de obiecte de tip Player.
         * Anumite date din fisier, le-am citit mai intai ca strinuri, apoi
         * am parcurs stringurile si le-am parsat pentru a obtine char-uri.
         * Tipul unui jucator il citesc cu charAt pentru a lua doar litera.
         * Cand ajung cu citirea la ingeri, retin intr-o lista numarul de ingeri
         * din fiecare runda, apoi fiecare inger in retin intr-o lista de liste
         * pentru a putea accesa ingerii pe runde, de exemplu indicele "0" de la
         * lista de liste semnifica runda 0.
         * @param inputPath = calea pentru fisierul de input
         * @param outputPath = calea pentru fisierul de output
         * @return
         */
        public Input load(final String inputPath, final String outputPath) {
                int n, m, noRounds, noPlayers;

                try {
                        FileSystem fs = new FileSystem(inputPath, outputPath);
                        n = fs.nextInt();
                        m = fs.nextInt();

                        char[][] mapType;
                        ArrayList<Player> players = new ArrayList<>();
                        String[] mapTypeString = new String[n];

                        for (int i = 0; i < n; i++) {
                                mapTypeString[i] = fs.nextWord();
                        }

                        mapType = Map.getInstance().createMap(mapTypeString, n, m);

                        noPlayers = fs.nextInt();
                        for (int i = 0; i < noPlayers; i++) {
                                Player temp = PlayerFactory.getInstance().
                                        createPlayer(fs.nextWord(), fs.nextInt(), fs.nextInt(), i);
                                players.add(temp);
                        }

                        noRounds = fs.nextInt();
                        String[] playersMovesString = new String[noRounds];
                        char[][] playersMoves = new char[noRounds][noPlayers];

                        for (int i = 0; i < noRounds; i++) {
                                playersMovesString[i] = fs.nextWord();
                        }
                        for (int i = 0; i < noRounds; i++) {
                                for (int j = 0; j < noPlayers; j++) {
                                        playersMoves[i][j] = playersMovesString[i].charAt(j);
                                }
                        }
                        ArrayList<ArrayList<Angels>> listOfAngels = new ArrayList<>(noRounds);
                        ArrayList<Angels> angels = new ArrayList<>();
                        ArrayList<Integer> noAngelsRounds = new ArrayList<>();
                        for (int i = 0; i < noRounds; i++) {
                                noAngelsRounds.add(fs.nextInt());
                                listOfAngels.add(i, new ArrayList<Angels>());
                                if (noAngelsRounds.get(i) > 0) {
                                        //listOfAngels.add(i, new ArrayList<Angels>());
                                        for (int j = 0; j < noAngelsRounds.get(i); j++) {
                                                String string = fs.nextWord();
                                                String[] tokens = string.split(",");
                                                int line = Integer.parseInt(tokens[1]);
                                                int column = Integer.parseInt(tokens[2]);
                                                Angels tempAng = AngelsFactory.getInstance().
                                                        createAngel(tokens[0], line, column);
                                                listOfAngels.get(i).add(tempAng);
                                        }
                                        angels.clear();
                                }
                        }

                        fs.close();
                        return new Input(n, m, mapType, noPlayers, players,
                                noRounds, playersMoves, listOfAngels, noAngelsRounds);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
}

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
                                        createPlayer(fs.nextWord(), fs.nextInt(), fs.nextInt());
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
                        fs.close();
                        return new Input(n, m, mapType, noPlayers, players, noRounds, playersMoves);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
}

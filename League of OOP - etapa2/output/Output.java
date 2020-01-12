package output;

import fileio.FileSystem;
import input.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Output {
        public Output() {

        }
        /**
         * Scriu rezultatul programului in fisier.
         * Parcurg evenimentele retinute de Marele Magician si scriu in fisier
         * fiecare eveniment.
         * Parcurg lista de playeri si avem 2 cazuri de scriere:
         * Caz1: Cand playerul este mort, scriu tipul player-ului cu string-ul dead
         * caz2: Cand playerul ramane in viata, scriu tipul player-ului, level-ul,
         * experienta, viata, si linia si coloana pe care se afla pe harta.
         * @param inputPat = path la fisierul de intrare
         * @param outputPath = path la fisierul de iesire
         * @param noPlayers = numarul de playeri
         * @param players = lista de playeri
         * @param dead = string-ul "dead"
         */
        public void writer(final String inputPat, final String outputPath, final int noPlayers,
                        final int noRounds, final ArrayList<Player> players, final String dead,
                        final ArrayList<ArrayList<String>> observerRounds) {
                try {
                        FileSystem fs = new FileSystem(inputPat, outputPath);
                        for (int i = 0; i < observerRounds.size(); i++) {
                              for (int j = 0; j < observerRounds.get(i).size(); j++) {
                                      fs.writeWord(observerRounds.get(i).get(j));
                                      fs.writeNewLine();
                              }
                              fs.writeNewLine();
                        }
                        System.out.println(observerRounds.get(0).size());
                        fs.writeWord("~~ Results ~~");
                        fs.writeNewLine();
                        for (int i = 0; i < noPlayers; i++) {
                                fs.writeCharacter(players.get(i).getRace().charAt(0));
                                fs.writeWord(" ");
                                if (players.get(i).getDead() == 1) {
                                       fs.writeWord("dead");
                                } else {
                                        fs.writeInt(players.get(i).getLevel());
                                        fs.writeWord(" ");
                                        fs.writeInt(players.get(i).getXp());
                                        fs.writeWord(" ");
                                        fs.writeInt((int) players.get(i).getHp()); //hp
                                        fs.writeWord(" ");
                                        fs.writeInt(players.get(i).getLinePosition()); //line
                                        fs.writeWord(" ");
                                        fs.writeInt(players.get(i).getColumnPosition()); //column
                                }
                                fs.writeNewLine();
                        }
                        fs.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

}

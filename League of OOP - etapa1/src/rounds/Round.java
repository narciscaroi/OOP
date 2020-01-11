package rounds;

import input.Player;
import java.util.ArrayList;

public class Round {

        public Round() { }
        /**
         * Aici creez cu adevarat jocul.
         * Intr-un for parcurg toate rundele.
         * In primul for setez player-ilor directia unde trebuie sa se deplaseze si
         * apelez metoda de deplasare, le setez tipul campului de pe teren,
         * le verific experienta, si verific daca acestia trebuie sa primeasca DoT, si in
         * cazul in care trebuie, le scad din viata valoarea DoT-ului si verific daca au murit.
         * In cel de-al doilea for, verific luptele dintre playerii care sunt in acelasi punct pe
         * harta
         * @param noRounds = numarul de runde
         * @param noPlayers = numarul de playeri
         * @param players = lista de playeri.
         * @param playerMoves = o matrice care imi indica cum se misca fiecare player
         * @param mapType = tipul hartii, cu fiecare camp de ce fel este
         */
        public void createGame(final int noRounds, final int noPlayers,
                               final ArrayList<Player> players, final char[][] playerMoves,
                               final char[][] mapType) {
                for (int idxRound = 0; idxRound < noRounds; idxRound++) {
                       System.out.println(" --------------------  ROUND ___ "
                        + idxRound + " --------------------");
                        for (int idxPlayer = 0; idxPlayer < noPlayers; idxPlayer++) {
                                players.get(idxPlayer).setDirection(
                                        playerMoves[idxRound][idxPlayer]);
                                players.get(idxPlayer).movingThroughMap();

                                int line = players.get(idxPlayer).getLinePosition();
                                int column = players.get(idxPlayer).getColumnPosition();
                                players.get(idxPlayer).setCurrentField(
                                        mapType[line][column]);

                                players.get(idxPlayer).checkExp();
                                players.get(idxPlayer).resetFighted();
                                players.get(idxPlayer).setHpBeforeDeflect(players.get(
                                        idxPlayer).getHp());
                                players.get(idxPlayer).setGotLevelUp(0);
                                //DOT
                                players.get(idxPlayer).resetTookDot();
                                players.get(idxPlayer).checkDotDamage();
                                players.get(idxPlayer).checkIsDead();
                        }
                        System.out.println("-FIGHTING-");
                        for (int idxPlayer = 0; idxPlayer < noPlayers; idxPlayer++) {
                                players.get(idxPlayer).counterRound = idxRound;
                        }
                        for (int idxPlayer = 0; idxPlayer < noPlayers; idxPlayer++) {
                                if (players.get(idxPlayer).getDead() == 1) {
                                        continue;
                                }
                                for (int verifyIdx = idxPlayer + 1;
                                     verifyIdx < noPlayers; verifyIdx++) {
                                        if (players.get(verifyIdx).getDead() == 1
                                                || players.get(verifyIdx).getFighted() == 1) {
                                                continue;
                                        } else if (players.get(idxPlayer).getFighted() == 0) {
                                            if (players.get(idxPlayer).getLinePosition()
                                                   == players.get(verifyIdx).getLinePosition()
                                                   && players.get(idxPlayer).getColumnPosition()
                                                   == players.get(verifyIdx).getColumnPosition()) {

                                                     players.get(idxPlayer).fightsWith((
                                                             players.get(verifyIdx)));
                                                     players.get(verifyIdx).fightsWith((
                                                            players.get(idxPlayer)));

                                                    players.get(idxPlayer).setFighted(1);
                                                    players.get(verifyIdx).setFighted(1);
                                                    players.get(idxPlayer).checkExp();
                                                    players.get(verifyIdx).checkExp();
                                            }
                                        }

                                }
                        }
                        System.out.println("STOP FIGHTING");
                        if (idxRound < noRounds) {
                                for (int i = 0; i < noPlayers; i++) {
                                        System.out.print(players.get(i).getRace() + " ");
                                        if (players.get(i).getDead() == 1) {
                                                System.out.println("dead");
                                                continue;
                                        }
                                        System.out.println(players.get(i).getLevel() + " "
                                                + players.get(i).getXp() + " "
                                                + players.get(i).getHp() + " "
                                                + players.get(i).getLinePosition()
                                                + " " + players.get(i).getColumnPosition());
                                }
                        }
                        System.out.println(" --------------------  END ROUND ___ "
                                + idxRound + " --------------------");
                }
        }
}

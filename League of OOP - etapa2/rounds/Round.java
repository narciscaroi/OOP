package rounds;

import common.Constants;
import input.Angels;
import input.Player;

import java.util.ArrayList;


public class Round implements TheGreatMagician {
        private ArrayList<ArrayList<String>> newsRounds = new ArrayList<>(Constants.MAXROUNDS);

        public Round() {
        }

        /**
         * Initializez lista.
         * @param noRounds
         */
        public void startObserving(final int noRounds) {
                for (int i = 0; i < noRounds; i++) {
                        newsRounds.add(i, new ArrayList<String>());
                }
        }

        /**
         * Retin evenimentele pe parcursul jocului intr - o lista.
         * @param news
         * @param idx
         */
        @Override
        public void updateGameActions(final Object news, final int idx) {
                newsRounds.get(idx).add((String) news);
        }

        /**
         * Aici creez cu adevarat jocul.
         * Intr-un for parcurg toate rundele.
         * In primul for setez player-ilor directia unde trebuie sa se deplaseze si
         * apelez metoda de deplasare, le setez tipul campului de pe teren,
         * le verific experienta, si verific daca acestia trebuie sa primeasca DoT, si in
         * cazul in care trebuie, le scad din viata valoarea DoT-ului si verific daca au murit.
         * In cel de-al doilea for, verific luptele dintre playerii care sunt in acelasi punct pe
         * harta
         * De fiecare data cand se intamppla un eveniment, de ex un erou moare sau a fost
         * ajutat de un inger, adaug evenimentul la lista care retine evenimentele pe
         * parcursul jocului.
         * @param noRounds = numarul de runde
         * @param noPlayers = numarul de playeri
         * @param players = lista de playeri.
         * @param playerMoves = o matrice care imi indica cum se misca fiecare player
         * @param mapType = tipul hartii, cu fiecare camp de ce fel este
         * @param listOfAngels
         */

        public void createGame(final int noRounds, final int noPlayers,
                               final ArrayList<Player> players, final char[][] playerMoves,
                               final char[][] mapType, final ArrayList<Integer> noAngelsRounds,
                               final ArrayList<ArrayList<Angels>> listOfAngels,
                               ArrayList<ArrayList<String>> observerRounds) {
                startObserving(noRounds);
                for (int idxRound = 0; idxRound < noRounds; idxRound++) {
                        updateGameActions("~~ Round " + (idxRound + 1) + " ~~", idxRound);
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
                                players.get(idxPlayer).setSettedStrategy(0);
                                players.get(idxPlayer).setWasHelpedByAngel(0);
                                players.get(idxPlayer).setStartFightHp(players.get(idxPlayer).
                                        getHp());
                        }
                        System.out.println("-FIGHTING-");
                        for (int idxPlayer = 0; idxPlayer < noPlayers; idxPlayer++) {
                                players.get(idxPlayer).counterRound = idxRound;
                        }

                        for (int idxPlayer = 0; idxPlayer < noPlayers; idxPlayer++) {
                                for (int verifyIdx = idxPlayer + 1;
                                     verifyIdx < noPlayers; verifyIdx++) {
                                        if (players.get(idxPlayer).getDead() == 1
                                                || players.get(verifyIdx).getFighted() == 1) {
                                                continue;
                                        } else if (players.get(idxPlayer).getFighted() == 0) {
                                            if (players.get(idxPlayer).getLinePosition()
                                                   == players.get(verifyIdx).getLinePosition()
                                                   && players.get(idxPlayer).getColumnPosition()
                                                   == players.get(verifyIdx).getColumnPosition()
                                                   && players.get(verifyIdx).getDead() == 0) {

                                                   players.get(idxPlayer).fightsWith((
                                                             players.get(verifyIdx)));
                                                   players.get(verifyIdx).fightsWith((
                                                            players.get(idxPlayer)));

                                                   players.get(idxPlayer).setFighted(1);
                                                   players.get(verifyIdx).setFighted(1);
                                                   players.get(idxPlayer).checkExp();
                                                   players.get(verifyIdx).checkExp();

                                                    if (players.get(verifyIdx).getDead() == 1) {
                                                            String s1 = "Player "
                                                                    + players.get(verifyIdx).
                                                                    getRaceWord() + " "
                                                                    + players.get(verifyIdx).
                                                                    getId()
                                                                    + " was killed by "
                                                                    + players.get(idxPlayer).
                                                                    getRaceWord() + " "
                                                                    + players.get(idxPlayer).
                                                                    getId();
                                                            updateGameActions(s1, idxRound);
                                                    }
                                                   if (players.get(idxPlayer).getDead() == 1) {
                                                            String s2 = "Player "
                                                                    + players.get(idxPlayer).
                                                                    getRaceWord() + " "
                                                                    + players.get(idxPlayer).
                                                                    getId()
                                                                    + " was killed by "
                                                                    + players.get(verifyIdx).
                                                                    getRaceWord() + " "
                                                                    + players.get(verifyIdx).
                                                                    getId();
                                                            updateGameActions(s2, idxRound);
                                                   }
                                                    while (players.get(idxPlayer).getReachedLevel()
                                                            < players.get(idxPlayer).getLevel()) {
                                                            players.get(idxPlayer).
                                                                    incReachedLevel(1);
                                                            String s1 = players.get(idxPlayer).
                                                                    getRaceWord()
                                                                    + " " + players.get(idxPlayer).
                                                                    getId()
                                                                    + " reached level "
                                                                    + players.get(idxPlayer).
                                                                    getReachedLevel();
                                                            updateGameActions(s1, idxRound);
                                                    }
                                                    while (players.get(verifyIdx).getReachedLevel()
                                                            < players.get(verifyIdx).getLevel()) {
                                                            players.get(verifyIdx).
                                                                    incReachedLevel(1);
                                                            String s1 = players.get(verifyIdx).
                                                                    getRaceWord()
                                                                    + " " + players.get(verifyIdx)
                                                                    .getId() + " reached level "
                                                                    + players.get(verifyIdx).
                                                                    getReachedLevel();
                                                            updateGameActions(s1, idxRound);
                                                    }


                                            }
                                        }

                                }
                        }
                        for (int angIdx = 0; angIdx < noAngelsRounds.get(idxRound); angIdx++) {
                                String s = "Angel " + listOfAngels.get(idxRound).
                                        get(angIdx).getType() + " was spawned at "
                                        + listOfAngels.get(idxRound).get(angIdx).
                                        getLinePosition() + " "
                                        + listOfAngels.get(idxRound).get(angIdx).
                                        getColumnPosition();
                                updateGameActions(s, idxRound);

                                for (int idxPlayer = 0; idxPlayer < noPlayers;
                                     idxPlayer++) {

                                        if (players.get(idxPlayer).getLinePosition()
                                                == listOfAngels.get(idxRound).get(angIdx).
                                                getLinePosition()
                                                && players.get(idxPlayer).getColumnPosition()
                                                == listOfAngels.get(idxRound).get(angIdx).
                                                getColumnPosition()
                                                && players.get(idxPlayer).getDead() == 0) {

                                                players.get(idxPlayer).angelVisitor(listOfAngels.
                                                        get(idxRound).get(angIdx));
                                                if (!(listOfAngels.get(idxRound).get(angIdx).
                                                        getType().equals("Spawner"))) {

                                                        String s2 = listOfAngels.get(idxRound).
                                                                get(angIdx).getType() + " "
                                                                + listOfAngels.get(idxRound).
                                                                get(angIdx).getWhatDoes() + " "
                                                                + players.get(idxPlayer).
                                                                getRaceWord() + " "
                                                                + players.get(idxPlayer).getId();
                                                        updateGameActions(s2, idxRound);

                                                        players.get(idxPlayer).checkIsDead();
                                                        if (players.get(idxPlayer).getDead() == 1) {
                                                                String s5 = "Player " + players.
                                                                        get(idxPlayer).getRaceWord()
                                                                        + " " + players.
                                                                        get(idxPlayer).getId()
                                                                        + " was killed by an angel";
                                                                updateGameActions(s5, idxRound);
                                                                continue;
                                                        }
                                                }
                                                while (players.get(idxPlayer).getReachedLevel()
                                                   < players.get(idxPlayer).getLevel()) {
                                                        players.get(idxPlayer).incReachedLevel(1);
                                                        String s1 = players.get(idxPlayer).
                                                                getRaceWord() + " "
                                                                + players.get(idxPlayer).getId()
                                                                + " reached level "
                                                                + players.get(idxPlayer).
                                                                getReachedLevel();
                                                        updateGameActions(s1, idxRound);
                                                }

                                                if (listOfAngels.get(idxRound).get(angIdx).getType()
                                                        .equals("TheDoomer")) {
                                                        String s3 = "Player " + players.
                                                                get(idxPlayer).getRaceWord() + " "
                                                                + players.get(idxPlayer).getId()
                                                                + " was killed by an angel";
                                                        updateGameActions(s3, idxRound);
                                                }
                                        } else if (players.get(idxPlayer).getLinePosition()
                                                == listOfAngels.get(idxRound).get(angIdx).
                                                getLinePosition()
                                                && players.get(idxPlayer).getColumnPosition()
                                                == listOfAngels.get(idxRound).get(angIdx).
                                                getColumnPosition()) {
                                                players.get(idxPlayer).angelVisitor(listOfAngels.
                                                        get(idxRound).get(angIdx));

                                                if (listOfAngels.get(idxRound).get(angIdx).getType()
                                                        .equals("Spawner")
                                                        && players.get(idxPlayer).
                                                        getWasBroughtToLife() == 1) {
                                                        String s2 = listOfAngels.get(idxRound).
                                                                get(angIdx).getType() + " "
                                                                + listOfAngels.get(idxRound).
                                                                get(angIdx).getWhatDoes() + " "
                                                                + players.get(idxPlayer).
                                                                getRaceWord() + " "
                                                                + players.get(idxPlayer).getId();
                                                        updateGameActions(s2, idxRound);

                                                        String s4 = "Player " + players.
                                                                get(idxPlayer).getRaceWord() + " "
                                                                + players.get(idxPlayer).getId()
                                                                + " was brought to life by "
                                                                + "an angel";
                                                        updateGameActions(s4, idxRound);
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
                for (int i = 0; i < newsRounds.size(); i++) {
                        for (int j = 0; j < newsRounds.get(i).size(); j++) {
                                observerRounds.get(i).add(newsRounds.get(i).get(j));
                        }
                }
        }
}

package input;

import java.util.ArrayList;

public class Input {
        private int coordN;
        private int coordM;
        private char[][] mapType;
        private int numberOfPlayers;
        private ArrayList<Player> players;
        private int numberOfRounds;
        private char[][] playersMoves;

        /**
         * Aici retin doate datele din fisier.
         * @param coordN
         * @param coordM
         * @param mapType
         * @param numberOfPlayers
         * @param players
         * @param numberOfRounds
         * @param playersMoves
         */
        public Input(final int coordN, final int coordM, final char[][] mapType,
                     final int numberOfPlayers, final ArrayList<Player> players,
                     final int numberOfRounds, final char[][] playersMoves) {
                this.coordN = coordN;
                this.coordM = coordM;
                this.mapType = mapType;
                this.numberOfPlayers = numberOfPlayers;
                this.players = players;
                this.numberOfRounds = numberOfRounds;
                this.playersMoves = playersMoves;
        }

        //getters & setters
        /**
         * Getter din care iau valoarea pentru coordN.
         * @return
         */
        public int getCoordN() {
                return coordN;
        }

        /**
         * Setter in care pun din InputLoad valoarea pentru coordN.
         * @param coordN
         */
        public void setCoordN(final int coordN) {
                this.coordN = coordN;
        }
        /**
         * Getter din care iau valoarea pentru coordM.
         * @return
         */
        public int getCoordM() {
                return coordM;
        }

        /**
         * Setter in care pun din InputLoad valoarea pentru coordM.
         * @param coordM
         */
        public void setCoordM(final int coordM) {
                this.coordM = coordM;
        }

        /**
         * Getter pentru harta.
         * @return
         */
        public char[][] getMapType() {
                return mapType;
        }
        /**
         * Setter pentru tipul hartii.
         * Cu acesta setez "fiecare" char al hartii cu tipurile pe
         * care le poate lua.
         * @param mapType
         */
        public void setMapType(final char[][] mapType) {
                this.mapType = mapType;
        }

        /**
         * Getter pentru numarul de jucatori.
         * @return
         */
        public int getNumberOfPlayers() {
                return numberOfPlayers;
        }

        /**
         * Setter pentru numarul de jucatori.
         * @param numberOfPlayers
         */
        public void setNumberOfPlayers(final int numberOfPlayers) {
                this.numberOfPlayers = numberOfPlayers;
        }

        /**
         * Getter pentru lista de playeri.
         * @return
         */
        public ArrayList<Player> getPlayers() {
                return players;
        }
        /**
         * Pun lista de playeri citita in lista din aceasta clasa.
         * @param players
         */
        public void setPlayers(final ArrayList<Player> players) {
                this.players = players;
        }

        /**
         * Getter numar de runde.
         * @return
         */
        public int getNumberOfRounds() {
                return numberOfRounds;
        }

        /**
         * Setez numarul de runde citit din fisier.
         * @param numberOfRounds
         */
        public void setNumberOfRounds(final int numberOfRounds) {
                this.numberOfRounds = numberOfRounds;
        }

        /**
         * Getter pentru matricea cu miscarile fiecarui player din
         * fiecare runda.
         * @return
         */
        public char[][] getPlayersMoves() {
                return playersMoves;
        }

        /**
         * Retin miscarile fiecarui player pentru fiecare runda.
         * @param playersMoves
         */
        public void setPlayersMoves(final char[][] playersMoves) {
                this.playersMoves = playersMoves;
        }
}

package input;

final class Map {
        private static Map instance = null;

        private Map() { }

        static Map getInstance() {
                if (instance == null) {
                        instance = new Map();
                }
                return instance;
        }
        /**
         * Aici creez mapa cu dimensiunile de "n" linii si "m" coloane.
         * Primesc ca input un string, care teoretic reprezinta o linie
         * din mapa creata aici, il parcug si pun char cu char in mapa
         * pe care vreau sa o creez.
         * @param mapTypeStr
         * @param n
         * @param m
         * @return
         */
        char[][] createMap(final String[] mapTypeStr, final int n, final int m) {
                char[][] mapType = new char[n][m];
                for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                                mapType[i][j] = mapTypeStr[i].charAt(j);
                        }
                }
                return mapType;
        }
}

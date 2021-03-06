package input;

import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

final class PlayerFactory {
        private static PlayerFactory instance = null;

        private PlayerFactory() { }

        static PlayerFactory getInstance() {
                if (instance == null) {
                        instance = new PlayerFactory();
                }
                return instance;
        }

        /**
         * In functie de rasa returnez un jucator de acel tip.
         * @param race
         * @param linePosition
         * @param columnPosition
         * @return
         */
        Player createPlayer(final String race, final int linePosition, final int columnPosition) {
                switch (race) {
                        case "R" : return new Rogue(race, linePosition, columnPosition);
                        case "K" : return new Knight(race, linePosition, columnPosition);
                        case "W" : return new Wizard(race, linePosition, columnPosition);
                        case "P" : return new Pyromancer(race, linePosition, columnPosition);
                        default: return null;
                }
        }
}

package input;

import angels.DamageAngels;
import angels.DarkAngels;
import angels.Dracula;
import angels.GoodBoy;
import angels.LevelUpAngel;
import angels.LifeGiver;
import angels.SmallAngel;
import angels.Spawner;
import angels.TheDoomer;
import angels.XPAngel;
import common.Constants;

final class AngelsFactory {
        private static AngelsFactory instance = null;

        private AngelsFactory() { }

        static AngelsFactory getInstance() {
                if (instance == null) {
                        instance = new AngelsFactory();
                }
                return instance;
        }

        /**
         * In functie de tip returnez un inger de acel tip.
         * @return
         */
         Angels createAngel(final String type, final int linePosition, final int columnPosition) {
                switch (type) {
                        case "DamageAngel" : return new DamageAngels(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "DarkAngel" : return new DarkAngels(type, linePosition,
                                columnPosition, Constants.BADANGSTR);
                        case "Dracula" : return new Dracula(type, linePosition,
                                columnPosition, Constants.BADANGSTR);
                        case "GoodBoy" : return new GoodBoy(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "LevelUpAngel" : return new LevelUpAngel(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "LifeGiver" : return new LifeGiver(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "SmallAngel" : return new SmallAngel(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "Spawner" : return new Spawner(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        case "TheDoomer" : return new TheDoomer(type, linePosition,
                                columnPosition, Constants.BADANGSTR);
                        case  "XPAngel" : return new XPAngel(type, linePosition,
                                columnPosition, Constants.GOODANGSTR);
                        default: return null;
                }
        }
}

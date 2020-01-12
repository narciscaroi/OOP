package input;

import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public abstract class Angels {
        private String type;
        private int linePosition;
        private int columnPosition;
        private String whatDoes;

        public Angels() {
                //just to trick checkstyl
        }
        public Angels(final String type, final int linePosition, final int columnPosition,
                   final String whatDoes) {
                this.type = type;
                this.linePosition = linePosition;
                this.columnPosition = columnPosition;
                this.whatDoes = whatDoes;
        }

        public abstract void accept(Rogue rogue);
        public abstract void accept(Knight knight);
        public abstract void accept(Pyromancer pyromancer);
        public abstract void accept(Wizard wizard);


        //getters & setters

        /**
         * Aceasta metoda este un getter si
         * returneaza tipul ingerului.
         * @return
         */
        public String getType() {
                return type;
        }

        /**
         * Aceasta metoda este un setter si
         * seteaza tipul ingerului.
         * @param type
         */
        public void setType(final String type) {
                this.type = type;
        }

        /**
         * Aceasta metoda returneaza linia pe care se afla ingerul.
         * @return
         */
        public int getLinePosition() {
                return linePosition;
        }

        /**
         * Aceasta metoda seteaza pe ce linie se afla ingerul.
         * @param newLinePosition
         */
        public void setLinePosition(final int newLinePosition) {
                this.linePosition = newLinePosition;
        }

        /**
         * Aceasta metoda returneaza coloana pe care se afla ingerul.
         * @return
         */
        public int getColumnPosition() {
                return columnPosition;
        }

        /**
         * Aceasta metoda seteaza coloana pe care se afla ingerul.
         * @param newColumnPosition
         */
        public void setColumnPosition(final int newColumnPosition) {
                this.columnPosition = columnPosition;
        }

        /**
         * Returneaza scopul ingerului(adica daca il ajuta sau
         * il "loveste".
         * @return
         */
        public String getWhatDoes() {
                return whatDoes;
        }

        /**
         * Seteaza scopul ingerului.
         * @param whatDoes
         */
        public void setWhatDoes(final String whatDoes) {
                this.whatDoes = whatDoes;
        }
}

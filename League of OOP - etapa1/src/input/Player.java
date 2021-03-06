package input;

import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public abstract class Player {
        private String race;
        private int linePosition;
        private int columnPosition;
        private int hp;
        private int xp;
        private int level = 0;
        private int damage;
        private float procentDmg;
        private char direction;
        private int counterRogueCritChance = 0;
        private int dead = 0; // 0 nu, 1 da
        private char currentField;
        private int maxHp;
        private int fighted = 0; //0 nu, 1 da
        private int nextMovementStopped = 0; //0 nu, adica se misca, 1 da, sta pe loc
        private int hpBeforeDeflect;
        private int tookDot = 0; //0 da, 1 nu
        public int counterRoundsDot = 0;
        public int dot = 0;
        public int counterRound = 0;
        private int gotLevelUp = 0;

        public Player() { }
        public Player(final String race, final int linePosition, final int columnPosition,
           final int hp, final int damage, final float procDmg) {
                this.race = race;
                this.linePosition = linePosition;
                this.columnPosition = columnPosition;
                this.hp = hp;
                this.damage = damage;
                this.procentDmg = procDmg;

        }

        //Double Dispatch
        public abstract void fightsWith(Player player);
        public abstract void giveDamageTo(Rogue rogue);
        public abstract void giveDamageTo(Knight knight);
        public abstract void giveDamageTo(Pyromancer pyromancer);
        public abstract void giveDamageTo(Wizard wizard);
        public abstract void checkExp();

        /**
         *Verific hp.ul jucatorului.
         * @return 1 daca e mort, 0 daca inca traieste
         */
        public int checkIsDead() {
                if (hp <= 0) {
                        dead = 1;
                        return 1;
                }
                return 0;
        }

        /**
         *Muta playerul pe harta stanga/dreapta/sus/jos daca este cazul.
         * U => in sus => line --
         * D => in jos => line ++
         * R => dreapta => column++
         * L => stanfa => column--
         */
        public void movingThroughMap() {
                if (getNextMovementStopped() == 0
                        && getDead() == 0) {
                        if (direction == 'U') {
                                linePosition -= 1;
                        } else if (direction == 'D') {
                                linePosition += 1;
                        } else if (direction == 'R') {
                                columnPosition += 1;
                        } else if (direction == 'L') {
                                columnPosition -= 1;
                        }
                } else {
                        decNextMovementStopped();
                }
        }

        /**
         *Verific damage-ul de tip DoT.
         * Verific daca counterul este mai mare decat 0, daca da,atunci inca trebuie
         * sa primeasca damage DoT si decrementez counterul.
         */
        public void checkDotDamage() {
                if (counterRoundsDot > 0) {
                        int newHp = getHp() - dot;
                        setHp(newHp);
                        counterRoundsDot -= 1;
                        setTookDot();
                }
        }
        // getters & setters

        /**
         * Iau rasa player-ului.
         * @return R/K/P/W
         */
        public String getRace() {
                return race;
        }

        /**
         * Setez rasa.
         * @param newRace
         */
        public void setRace(final String newRace) {
                this.race = newRace;
        }

        /**
         * Iau linia pe care se afla pe harta player-ul.
         * @return
         */
        public int getLinePosition() {
                return linePosition;
        }

        /**
         * Setez linia pe care se afla pe harta.
         * @param newLinePosition
         */
        public void setLinePosition(final int newLinePosition) {
                this.linePosition = newLinePosition;
        }

        /**
         * Iau coloana pe care se afla pe harta.
         * @return
         */
        public int getColumnPosition() {
                return columnPosition;
        }

        /**
         * Setez coloana pe care se afla pe harta.
         * @param newColumnPosition
         */
        public void setColumnPosition(final int newColumnPosition) {
                this.columnPosition = newColumnPosition;
        }

        /**
         * Iau hp-ul player-ului.
         * @return
         */
        public int getHp() {
                return hp;
        }

        /**
         * Setez hp-ul.
         * @param newHp
         */
        public void setHp(final int newHp) {
                this.hp = newHp;
        }

        /**
         * Iau level-ul.
         * @return
         */
        public int getLevel() {
                return level;
        }


        /**
         * Setez level-ul.
         * @param newLevel
         */
        public void setLevel(final int newLevel) {
                this.level = newLevel;
        }

        /**
         * Iau directia in care trebuie sa se miste pe harta.
         * @return
         */
        public char getDirection() {
                return direction;
        }

        /**
         * Setez directia.
         * @param newDirectionn
         */
        public void setDirection(final char newDirectionn) {
                this.direction = newDirectionn;
        }

        /**
         * Iau damage-ul de baza pe care un player ar trebui sa il dea(fara wizard).
         * @return
         */
        public int getDamage() {
                return damage;
        }

        /**
         * Setez damage-ul.
         * @param newDmg
         */
        public void setDamage(final int newDmg) {
                this.damage = newDmg;
        }

        /**
         * Preiau numar de batalii ale lui rogue.
         * @return
         */
        public int getCounterRogueCritChance() {
                return counterRogueCritChance;
        }

        /**
         * Setez bataliile lui rog pt a stii cand urmeaza sa dea critica.
         * Incrementeaza de cate ori s-a luptat rogue.ul
         */
        public void setCounterRogueCritChance() {
                this.counterRogueCritChance += 1;
        }


        /**
         * Peiau tipul de camp de pe teren pe care se afla un player.
         * @return
         */
        public char getCurrentField() {
                return currentField;
        }

        /**
         * Setez campul de pe teren pe care se afla un player.
         * @param newCurrentField
         */
        public void setCurrentField(final char newCurrentField) {
                this.currentField = newCurrentField;
        }

        /**
         * Iau procentul de baza al lui wiz pentru spell-ul lui wizard.
         * @return
         */
        public float getProcentDmg() {
                return procentDmg;
        }

        /**
         * Setez procentajul de baza.
         * @param newProcentDmg
         */
        public void setProcentDmg(final float newProcentDmg) {
                this.procentDmg = newProcentDmg;
        }
        /**
         * Iau experienta pe care a acumulat-o un player.
         * @return
         */
        public int getXp() {
                return xp;
        }
        /**
         * Setez noua experienta.
         * @param newXp
         */
        public void setXp(final int newXp) {
                this.xp = newXp;
        }

        /**
         * Verific daca un jucator este mort.
         * @return
         */
        public int getDead() {
                return dead;
        }

        /**
         * Verific daca un jucator s-a luptat.
         * @return
         */
        public int getFighted() {
                return fighted;
        }

        /**
         * Verific daca un player s-a luptat.
         * @param newFighted
         */
        public void setFighted(final int newFighted) {
                this.fighted = newFighted;
        }

        /**
         * Setez cu 1 daca un player s-a luptat deja intr-o lupta.
         */
        public void resetFighted() {
                this.fighted = 0;
        }
        /**
         * Verific daca un jucator a luat deja damage DoT la inceputul rundei.
         * @return
         */
        public int getTookDot() {
                return tookDot;
        }
        /**
         * Setez cu 1 daca un jucator a luat damage DOT.
         */
        public void setTookDot() {
                this.tookDot = 1;
        }

        /**
         * Resetez la inceputul rundei.
         */
        public void resetTookDot() {
                this.tookDot = 0;
        }

        /**
         * Verific daca in runda, jucatorul a primit un DoT care nu ii permite sa se deplaseze.
         * @return
         */
        public int getNextMovementStopped() {
                return nextMovementStopped;
        }

        /**
         * Setez cate runde nu are voie sa se deplaseze.
         */
        public void setNextMovementStopped(final int newNextMoves) {
                this.nextMovementStopped = newNextMoves;
        }

        /**
         * Decrementez numarul de runde in care un player este imobilizat.
         */
        public void decNextMovementStopped() {
                this.nextMovementStopped -= 1;
        }
        /**
         * Iau viata unui player inainte ca acesta sa primeasca spell-ul Deflect de la W.
         * @return
         */
        public int getHpBeforeDeflect() {
                return hpBeforeDeflect;
        }
        /**
         * Stezez viata unui player inainte sa primeasca deflect.
         * @param hpBeforeDeflect
         */
        public void setHpBeforeDeflect(final int hpBeforeDeflect) {
                this.hpBeforeDeflect = hpBeforeDeflect;
        }
        /**
         * Verific daca un player a primit deja lvlup intr-o runda.
         * @return
         */
        public int getGotLevelUp() {
                return gotLevelUp;
        }
        /**
         * Setez atunci cand un jucator primeste lvlup.
         * @param newGotLevelUp
         */
        public void setGotLevelUp(final int newGotLevelUp) {
                this.gotLevelUp = newGotLevelUp;
        }
}

package player;

import common.Constants;
import input.Angels;
import input.Player;

public class Rogue extends Player implements HeroStrategy {
        public Rogue() {
        }

        public Rogue(final String race, final int linePosition, final int columnPosition,
                     final int id) {
                super(race, linePosition, columnPosition, Constants.ROGHP,
                        Constants.ROGDMG, 0, id, "Rogue");
        }

        /**
         * A.
         * @param angel
         */
        @Override
        public void angelVisitor(final Angels angel) {
                angel.accept(this);
        }

        /**
         * A.
         * @return
         */
        @Override
        public void heroStrategy() {
                float maxHp = Constants.ROGHP + Constants.ROGHPPERLVL * getLevel();
                if (Constants.RLEFTFRAC * maxHp <  getStartFightHp()
                        && getStartFightHp() < Constants.RRIGHTFRAC * maxHp
                        && getSettedStrategy() == 0
                        && getNextMovementStopped() == 0) {
                        setHp(getHp() * Constants.RDOWNHP);
                        incBonusProc(Constants.RUPCOEF);
                        setSettedStrategy(1);
                        return;
                }

                if (Constants.RLEFTFRAC * maxHp > getStartFightHp()
                        && getSettedStrategy() == 0
                        && getNextMovementStopped() == 0) {
                        setHp(getHp() * Constants.RUPHP);
                        decBonusProc(Constants.RDOWNCOEF);
                        setSettedStrategy(1);
                }
        }
        /**
         * Calculez damage-ul dat pe backstab de baza cu modificatorul de teren si critica.
         * @return
         */
        private float backstab() {
                float dmgDealt = getDamage() + Constants.ROGDMGPERLVL * getLevel();
                if (getCurrentField() == 'W') {
                        if (getCounterRogueCritChance() % Constants.ROGVERIFYIFCRIT == 0) {
                                dmgDealt *= Constants.ROGCRITPROC;
                        }
                        dmgDealt *= Constants.ROGFIELDBONUS;
                }
                return dmgDealt;
        }
        /**
         * Calculez damage-ul de baza dat de paralysis cu modificatorul de teren.
         * @return
         */
        private float paralysis() {
                float paralysisDmg = Constants.PARALYDMG + Constants.PARALYDMGPERLVL * getLevel();
                if (getCurrentField() == 'W') {
                        paralysisDmg *= Constants.ROGFIELDBONUS;
                }
                return paralysisDmg;
        }
        /**
         * Calculez damage-ul dat de wizard pe spell-ul deflect.
         * @param wizard
         * @return
         */
        private float deflect(final Wizard wizard) {
                float deflectDmgDealt = backstab();
                float deflectParalysisDmg = paralysis();
                float proccent = Constants.WIZDEFLPR + Constants.WIZDELFINCPROC * wizard.getLevel();
                if (proccent > Constants.WIZDEFLMAXPROC) {
                        proccent = Constants.WIZDEFLMAXPROC;
                }
                float deflectDmg = proccent * (deflectDmgDealt + deflectParalysisDmg);
                if (wizard.getCurrentField() == 'D') {
                        deflectDmg *= Constants.WIZDESERTBONUS;
                }
                deflectDmg *= (Constants.DEFKODWTR + wizard.getBonusProc());
                return  deflectDmg;
        }
        /**
         * Apelez prin DD cu cine se lupta un player.
         * @param player
         */
        @Override
        public void fightsWith(final Player player) {
                player.giveDamageTo(this);
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis, iar
         * in momentul in care inmultesc damage-ul cu modificatorul de rasa, la acest
         * modificator, adun si procentul bonus acumulat pe parcurs.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param rogue
         */
        @Override
        public void giveDamageTo(final Rogue rogue) {
                rogue.heroStrategy();
                heroStrategy();
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= (Constants.BSMODRTR + getBonusProc());

                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= (Constants.PMODRTR + getBonusProc());
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }


                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                float newHp = rogue.getHp() - totalDamage;
                setCounterRogueCritChance();
                rogue.setHp(newHp);
                rogue.counterRoundsDot = counterDot;
                rogue.dot = Math.round(paralysisDmg);
                rogue.setNextMovementStopped(counterDot);

                rogue.checkIsDead();
                if (rogue.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - rogue.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
                setCounterRogueCritChance();
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis, iar
         * in momentul in care inmultesc damage-ul cu modificatorul de rasa, la acest
         * modificator, adun si procentul bonus acumulat pe parcurs.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param knight
         */
        @Override
        public void giveDamageTo(final Knight knight) {
                setNextMovementStopped(1);
                knight.heroStrategy();
                heroStrategy();
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= (Constants.BSMODRTK + getBonusProc());

                //paralysis
                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= (Constants.PMODRTK + getBonusProc());
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }

                knight.dot = Math.round(paralysisDmg);
                knight.counterRoundsDot = counterDot;
                knight.setNextMovementStopped(counterDot);
                setCounterRogueCritChance();
                //modific hp
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                System.out.println("BACKSTAB @@@ " + dmgDealt);
                System.out.println("PARALYSUS @@ " + paralysisDmg);
                float newHp = knight.getHp() - totalDamage;
                knight.setHp(newHp);
                knight.checkIsDead();
                if (knight.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - knight.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis, iar
         * in momentul in care inmultesc damage-ul cu modificatorul de rasa, la acest
         * modificator, adun si procentul bonus acumulat pe parcurs.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * Calculez cat damage primeste  Rogue-ul de la Wizard pe deflect
         * @param
         */
        @Override
        public void giveDamageTo(final Wizard wizard) {
                wizard.heroStrategy();
                heroStrategy();
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= (Constants.BSMODRTW + getBonusProc());
                //todo DoT paralysis
                int counterDot = 0;
                float paralysisDmg = paralysis();

                paralysisDmg *= (Constants.PMODRTW + getBonusProc());
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }

                wizard.dot = Math.round(paralysisDmg);
                wizard.counterRoundsDot = counterDot;
                wizard.setNextMovementStopped(counterDot);
                wizard.setTookDot();
                //modific hp
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                float newHp = wizard.getHp() - totalDamage;
                wizard.setHp(newHp);
                wizard.checkIsDead();
                if (wizard.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - wizard.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
                //deflect dat de wizz in atacator
                float deflectDmg = deflect(wizard);

                float newRogHp = getHp() - Math.round(deflectDmg);
                setHp(newRogHp);
                if (getDead() == 1) {
                        if (wizard.getGotLevelUp() == 0) {
                                int newXp = wizard.getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (wizard.getLevel() - getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                wizard.setXp(newXp);
                        }
                }
                setCounterRogueCritChance();
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis, iar
         * in momentul in care inmultesc damage-ul cu modificatorul de rasa, la acest
         * modificator, adun si procentul bonus acumulat pe parcurs.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param pyromancer
         */
        @Override
        public void giveDamageTo(final Pyromancer pyromancer) {
                pyromancer.heroStrategy();
                heroStrategy();
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= (Constants.BSMODRTP + getBonusProc());

                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= (Constants.PMODRTP + getBonusProc());
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }

                pyromancer.dot = Math.round(paralysisDmg);
                pyromancer.counterRoundsDot = counterDot;
                pyromancer.setNextMovementStopped(counterDot);

                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);

                //modific hp
                float newHp = pyromancer.getHp() - totalDamage;
                pyromancer.setHp(newHp);
                pyromancer.checkIsDead();
                setCounterRogueCritChance();
                if (pyromancer.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel()
                                                - pyromancer.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
        }
        /**
         * La inceputul fiecarei runde verific daca un jucator a primit experienta.
         * Daca acestuia i-a crescut xp-ul, ii si incrementez leve-ul in functie de xp-ul
         * pe care acesta il are si setez ca acesta a primit lvl up.
         */
        @Override
        public void checkExp() {
                while (getXp() >= (Constants.PLAYERBASEUPXP
                        + getLevel() * Constants.PLAYERXPMULTIPLIER)
                        && getDead() == 0) {
                        super.setLevel(getLevel() + 1);
                        super.setHp(Constants.ROGHP + getLevel() * Constants.ROGHPPERLVL);
                        super.setGotLevelUp(1);
                }
        }

}

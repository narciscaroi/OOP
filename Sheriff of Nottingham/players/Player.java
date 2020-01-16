package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerStrategy; //basic/greedy/bribed
    private int playerRoundType; //0-comerciant -> 1 sheriff
    private int coins = Constants.INITIALCOINS;
    public List<Goods> cardsInHand = new ArrayList<>();
    public List<Goods> bag =  new ArrayList<>();
    public List<Goods> goodsOnTable = new ArrayList<>();
    public List<Goods> goodsDeclared = new ArrayList<>();
    public int bribery = 0;
    public int score;
    public int id;
    public int verifiedBag; //0 - nu 1-da

    public  Player() { }
    public Player(final String playerOrder, final List<Goods> cards) {
        this.playerStrategy = playerOrder;
        this.cardsInHand = cards;
    }

    /**
     * <h1>COUNTER FRECVENTA.</h1>
     * Calculez frecventa fiecarui bun din cartile unui jucator.
     * @return frecventa pentru fiecare carte din mana unui jucator
     */
    public void countFrequency() {
        int freq = 0;
        //parcurg fiecare carte din mana
        for (int i = 0; i < cardsInHand.size(); i++) {
            freq = 0;
            Goods currentGood = cardsInHand.get(i);
            for (Goods goods : cardsInHand) {
                if (currentGood.equals(goods)) {
                    //cand am gasit un anumit tip de bun,ii cresc frecventa
                    freq++;
                }
            }
            cardsInHand.get(i).setFrequency(freq);
        }
    }

    /**
     * <h1>Counter frecventa Taraba.</h1>
     * calculez numar de aparitii al fiecarui bun de pe taraba
     * unui jucator.
     * @return frecventa
     */
    public void countFrequencyOnTable() {
        int freqTable = 0;
        //parcurg fiecare bun de pe tarabana
        for (int i = 0; i < goodsOnTable.size(); i++) {
            freqTable = 0;
            Goods currentGood = goodsOnTable.get(i);
            for (Goods goods : goodsOnTable) {
                //cand gasesc un anumit tip de bun, ii cresc frecventa
                if (currentGood.equals(goods)) {
                    freqTable++;
                }
            }
            goodsOnTable.get(i).setFrequencyOnTable(freqTable);
        }
    }

    //getters & setters

    /**
     * <h1>STRATEGY</h1>
     * Preiau strategia unui jucator.
     * @return strategia: basic/greedy/bribed
     */
    public String getPlayerStrategy() {
        return playerStrategy;
    }

    /**
     * <h1>Strategy.</h1>
     * Stetez strategia unui jucator
     * @param playerStrategy
     * basic/greedy/bribed
     */
    public void setPlayerStrategy(final String playerStrategy) {
        this.playerStrategy = playerStrategy;
    }

    /**
     * <h1>SERIF SAU COMERCIANT</h1>
     * Iau tipul jucatorului dintr-o subRunda.
     * acesta poate fi serif sau comerciant
     * @return 0 pentru comerciant, 1 pentru serif
     */
    public int getPlayerRoundType() {
        return playerRoundType;
    }

    /**
     * <h1>SERIF SAU COMERCIANT</h1>
     * Preiau tipul jucatorului dintr-o subrunda (serif/comerciant).
     * @param playerRoundType
     */
    public void setPlayerRoundType(final int playerRoundType) {
        this.playerRoundType = playerRoundType;
    }

    /**
     * <h1>COINS PLAYER</h1>
     * Preiau numarul de coins-uri pe care il are un jucator.
     * fiecare jucator incepe cu 80 coins
     * @return numarul de coins-uri
     */
    public int getPlayerCoins() {
        return this.coins;
    }

    /**
     * <h1>COINS PLAYER.</h1>
     * Setez numarul de coins-uri pentru un jucator.
     * la inceput 80
     * Apoi schimb numarul de coinsuri cu unul calculat
     * in afara acestui setter
     * @param newCoins
     */
    public void setPlayerCoin(final int newCoins) {
        this.coins = newCoins;
    }
}

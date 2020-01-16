package com.tema1.round;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsType;
import com.tema1.players.Player;
import java.util.ArrayList;
import java.util.List;

public class InspectBag {
    public List<Goods> nonDeclaredGoods = new ArrayList<>();
    public List<Goods> auxGoodsOnTable = new ArrayList<>();

    /**
     * <h1>BASIC SERIF.</h1>
     * Avem seriful cu strategie basic si un player de inspectat.
     * Verific daca toate bunurile declarte de player-ul inspectat
     * verifica id-ul pe care l-a declarat
     * Aplic sactiunea potrivita.
     * @param serif
     * @param inspectedPlayer
     */
    void inspectBasic(final Player serif, final Player inspectedPlayer) {
        if (serif.getPlayerCoins() < Constants.MINBASICCOINS) {
            return;
        } else {
            for  (int i = 0; i < inspectedPlayer.bag.size(); i++) {
                if (inspectedPlayer.bag.get(i) == inspectedPlayer.goodsDeclared.get(0)) {
                    auxGoodsOnTable.add(inspectedPlayer.bag.get(i));
                } else {
                    nonDeclaredGoods.add(inspectedPlayer.bag.get(i));
                }
            }
        }
        int totalPenalty = 0;
        int totalPenaltySerif = 0;
        for (int i = 0; i < nonDeclaredGoods.size(); i++) {
            if (nonDeclaredGoods.get(i).getType() == GoodsType.Illegal) {
                totalPenalty += nonDeclaredGoods.get(i).getPenalty();
            }
        }
        if (totalPenalty == 0) {
            totalPenaltySerif = auxGoodsOnTable.size() * Constants.LEGALPENALTY;
            serif.setPlayerCoin(serif.getPlayerCoins() - totalPenaltySerif);
            inspectedPlayer.setPlayerCoin(inspectedPlayer.getPlayerCoins() + totalPenaltySerif);
        } else if (totalPenalty != 0) {
            inspectedPlayer.setPlayerCoin(inspectedPlayer.getPlayerCoins() - totalPenalty);
            serif.setPlayerCoin(serif.getPlayerCoins() + totalPenalty);
        }
        if (auxGoodsOnTable.size() > 0) {
            inspectedPlayer.goodsOnTable.addAll(auxGoodsOnTable);
        }
        inspectedPlayer.bag.clear();
        nonDeclaredGoods.clear();
        auxGoodsOnTable.clear();
    }
    /**
     * <h1>Greedy SERIF.</h1>
     * Avem seriful cu strategie basic si un player de inspectat.
     * Verific daca toate bunurile declarte de player-ul inspectat
     * verifica id-ul pe care l-a declarat
     * Aplic sactiunea potrivita.
     * @param serif
     * @param inspectedPlayer
     */
    void inspectGreedy(final Player serif, final Player inspectedPlayer) {
        for (int i = 0; i < inspectedPlayer.bag.size(); i++) {
             if (inspectedPlayer.bag.get(i) == inspectedPlayer.goodsDeclared.get(0)) {
                auxGoodsOnTable.add(inspectedPlayer.bag.get(i));
            } else {
                nonDeclaredGoods.add(inspectedPlayer.bag.get(i));
            }
        }
        int totalPenalty = 0;
        int totalPenaltySerif = 0;
        for (int i = 0; i < nonDeclaredGoods.size(); i++) {
            if (nonDeclaredGoods.get(i).getType() == GoodsType.Illegal) {
                totalPenalty += nonDeclaredGoods.get(i).getPenalty();
            }
        }
        if (totalPenalty == 0) {
            totalPenaltySerif = auxGoodsOnTable.size() * Constants.LEGALPENALTY;
            serif.setPlayerCoin(serif.getPlayerCoins() - totalPenaltySerif);
            inspectedPlayer.setPlayerCoin(inspectedPlayer.getPlayerCoins() + totalPenaltySerif);
        } else {
            int newCoins = inspectedPlayer.getPlayerCoins() - totalPenalty;
            inspectedPlayer.setPlayerCoin(newCoins);
            int newSerifCoins = serif.getPlayerCoins() + totalPenalty;
            serif.setPlayerCoin(newSerifCoins);

        }
        if (auxGoodsOnTable.size() > 0) {
            inspectedPlayer.goodsOnTable.addAll(auxGoodsOnTable);
        }
        inspectedPlayer.bag.clear();
        nonDeclaredGoods.clear();
        auxGoodsOnTable.clear();
    }
    /**
     * <h1>GBRIBE SERIF.</h1>
     * Avem seriful cu strategie basic si un player de inspectat.
     * Verific daca toate bunurile declarte de player-ul inspectat
     * verifica id-ul pe care l-a declarat
     * Aplic sactiunea potrivita.
     * @param serif seriful de tip bribe
     * @param inspectedPlayer playerul care trebuie inspectat
     */
    void inspectBribe(final Player serif, final Player inspectedPlayer) {
        for (int i = 0; i < inspectedPlayer.bag.size(); i++) {
            if (inspectedPlayer.bag.get(i).getId()
                    == inspectedPlayer.goodsDeclared.get(0).getId()) {
                auxGoodsOnTable.add(inspectedPlayer.bag.get(i));
            } else {
                nonDeclaredGoods.add(inspectedPlayer.bag.get(i));
            }
        }

        int totalPenalty = 0;
        int totalPenaltySerif = 0;
        for (int i = 0; i < nonDeclaredGoods.size(); i++) {
            if (nonDeclaredGoods.get(i).getType() == GoodsType.Illegal) {
                totalPenalty += nonDeclaredGoods.get(i).getPenalty();
            }
        }

        if (totalPenalty == 0) {
            totalPenaltySerif = auxGoodsOnTable.size() * Constants.LEGALPENALTY;
                    inspectedPlayer.goodsDeclared.get(0).getPenalty();
            serif.setPlayerCoin(serif.getPlayerCoins() - totalPenaltySerif);
            inspectedPlayer.setPlayerCoin(inspectedPlayer.getPlayerCoins() + totalPenaltySerif);
        } else {
            inspectedPlayer.setPlayerCoin(inspectedPlayer.getPlayerCoins() - totalPenalty);
            serif.setPlayerCoin(serif.getPlayerCoins() + totalPenalty);
        }
        if (auxGoodsOnTable.size() > 0) {
            inspectedPlayer.goodsOnTable.addAll(auxGoodsOnTable);
        }
        inspectedPlayer.bag.clear();
        nonDeclaredGoods.clear();
        auxGoodsOnTable.clear();
    }
}

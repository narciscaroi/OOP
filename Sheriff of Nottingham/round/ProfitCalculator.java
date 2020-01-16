package com.tema1.round;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;
import com.tema1.players.Player;

import java.util.ArrayList;
import java.util.List;

public class ProfitCalculator {
    private int legalProfit = 0;
    private int illegalProfit = 0;
    public ProfitCalculator() {
    }

    /**
     * <h1>CALCULATOR PENTRU PROFITUL UNUI JUCATOR.</h1>
     * Calculeaza profitul pe care il are un jucator.
     * Verific mai intai ce bunuri ilegale a adus pe tarabada.
     * Daca a adus,adaug bonusul pe care acestea il aduc
     * Apoi calculez si profitul pe cele legale, daca a adus.
     * @param player
     */
    public void profitCalculator(final Player player) {
        legalProfit = 0;
        illegalProfit = 0;
        List<Goods> auxiliarList = new ArrayList<>();
        for (int i = 0; i < player.goodsOnTable.size(); i++) {
           if (player.goodsOnTable.get(i).getType() == GoodsType.Illegal) {
               illegalProfit += player.goodsOnTable.get(i).getProfit();
           }
           if (player.goodsOnTable.get(i).getId() == Constants.SILKID) {
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHEESEID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHEESEID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHEESEID));
           } else if (player.goodsOnTable.get(i).getId() == Constants.PEPPERID) {
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHICKENID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHICKENID));
           } else if (player.goodsOnTable.get(i).getId() == Constants.BARRELID) {
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.BREADID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.BREADID));
           } else if (player.goodsOnTable.get(i).getId() == Constants.BEERID) {
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.WINEID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.WINEID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.WINEID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.WINEID));
           } else if (player.goodsOnTable.get(i).getId() == Constants.SEAFOODID) {
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.TOMATOID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.TOMATOID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.POTATOID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.POTATOID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.POTATOID));
               auxiliarList.add(GoodsFactory.getInstance().getGoodsById(Constants.CHICKENID));
           }
        }
        player.goodsOnTable.addAll(auxiliarList);
        for (int i = 0; i < player.goodsOnTable.size(); i++) {
            if (player.goodsOnTable.get(i).getType() == GoodsType.Legal) {
                legalProfit += player.goodsOnTable.get(i).getProfit();
            }
        }
    }

    /**
     * <h1>GETTER PENTRU PROFITUL LEGAL.</h1>
     * CU aceasta metoda iau profitul calculat anterior aduse de bunurile legale.
     * @return
     */
    public int getLegalProfit() {
        return legalProfit;
    }

    /**
     * <h1>GETTER PENTRU PROFITUL ILLEGAL.</h1>
     * CU aceasta metoda iau profitul calculat anterior aduse de bunurile illegale.
     * @return
     */
    public int getIllegalProfit() {
        return illegalProfit;
    }
}

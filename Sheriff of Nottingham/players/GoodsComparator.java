package com.tema1.players;

import com.tema1.goods.Goods;
import java.util.Comparator;

public class GoodsComparator implements Comparator<Goods> {
    @Override
    /**
     * <h1>COMPARATOR BUNURI.</h1>
     * Cu aceasta metoda compar 2 bunuri.
     * Compar prima data dupa frecventa,apoi dupa profit si dupa id
     * @return un indice pentru a stii daca trebuie interschimbate
     */
    public int compare(final Goods o1, final Goods o2) {
        //compar frecventa
        if (o1.getFrequency() < o2.getFrequency()) {
            return 1;
        } else if (o1.getFrequency() > o2.getFrequency()) {
            return -1;
        } else {
                //compar profitul
            if (o1.getProfit() < o2.getProfit()) {
                return 1;
            } else if (o1.getProfit() > o2.getProfit()) {
                return -1;
            } else {
                //compar id-ul
                if  (o1.getId() < o2.getId()) {
                    return 1;
                } else if (o1.getId() > o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}

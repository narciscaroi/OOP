package com.tema1.players;

import java.util.Comparator;

public class PlayersComparator implements Comparator<Player> {
    @Override
    /**
     * <h1>COMPARATOR PLAYERI.</h1>
     * Cu aceasta metoda compar 2 playeri.
     * Compar prima data dupa scor si apoi dupa id
     * @return un indice pentru a stii daca trebuie interschimbate
     */
    public int compare(final Player player1, final Player player2) {
        //compar scorul
        if (player1.score < player2.score) {
            return 1;
        } else if (player1.score > player2.score) {
            return -1;
        } else {
            //compar id-ul
            if (player1.id > player2.id) {
                return 1;
            } else if (player1.id < player2.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

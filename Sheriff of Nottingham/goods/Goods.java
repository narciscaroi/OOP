package com.tema1.goods;

public abstract class Goods {
    private final int id;
    private final GoodsType type;
    private final int profit;
    private final int penalty;
    private int frequency;
    private int frequencyCards;
    private int frequencyOnTable;

    public Goods(final int id, final GoodsType type, final int profit, final int penalty) {
        this.id = id;
        this.type = type;
        this.profit = profit;
        this.penalty = penalty;
    }

    public final int getId() {
        return id;
    }

    public final GoodsType getType() {
        return type;
    }

    public final int getProfit() {
        return profit;
    }

    public final int getPenalty() {
        return penalty;
    }

    /**
     * <h1>GETTER FREQ.</h1>
     * Frecventa cartilor in mana unui jucator.
     * @return returneaza de cate ori se repeta un anumit tip de carte
     * in mana unui jucator
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     *<h2>SETTER FREQ.</h2>
     * Seteaza frecventa unei carti.
     * @param frequency primesc frecventa unui anumit tip de carti
     * si actualizez frecventa bunului cu setterul pentru
     * a contoriza fiecare aparitie
     */
    public void setFrequency(final int frequency) {
        this.frequency = frequency;
    }

    /**
     *<h1>GETTER FREQ CARDS.</h1>
     * iau frecventa bunurilor la fiecare subrunda.
     * @return
     */
    public int getFrequencyCards() {
        return frequencyCards;
    }

    /**
     * <h2>STTER FREQ EACH ROUND.</h2>
     *modifica frecventa bunurilor la fiecare subrunda,
     */
    public void incFrequencyCards() {
        this.frequencyCards += 1;
    }

    /**
     * <h1>SETTER FREQ CARDS EACH ROUND.</h1>
     * dupa fiecare subrunda frecventa trebuie resetata.
     */
    public void set0FrequencyCards() {
        this.frequencyCards = 0;
    }

    /**
     * <h2>GETTER FREQ GOODS ON TABLE.</h2>
     * iau frecventa fiecarui bun de pe taraba.
     * @return frecventa
     */
    public int getFrequencyOnTable() {
        return frequencyOnTable;
    }

    /**
     * <h1>SETTER FREQ ON TABLE.</h1>
     * cu ajutorul acestei metode contorizez de cate ori apare
     * un anumit tip de produs pe taraba.
     * @param frequencyOnTable
     */
    public void setFrequencyOnTable(final int frequencyOnTable) {
        this.frequencyOnTable = frequencyOnTable;
    }
}

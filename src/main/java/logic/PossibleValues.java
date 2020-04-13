package logic;

import java.util.Arrays;


public class PossibleValues {

    private boolean values[];
    private byte amount;


    public PossibleValues(boolean eachValuePossible,byte gridSize) {
        values = new boolean[gridSize];
        Arrays.fill(values, eachValuePossible);
        if (eachValuePossible) amount = (byte) values.length;
        else amount = 0;
    }


    public PossibleValues(boolean[] bool) {
        values = bool;
        for (boolean tmp : bool) {
            if (tmp) amount++;
        }
    }

    public PossibleValues(PossibleValues pv) {
        values = new boolean[pv.getSize()];
        System.arraycopy(pv.getValues(), 0, values, 0, pv.getSize());
        amount = pv.getAmount();
    }


    public boolean isPossible(int possibleValue) {
        return values[possibleValue - 1];
    }



    public void setImpossible(int impossibleValue) {
        if (values[impossibleValue - 1]) {
            values[impossibleValue - 1] = false;
            amount--;
        }
    }


    public byte getSize() {
        return (byte)values.length;
    }

    protected boolean[] getValues() {
        return values;
    }

    public byte getAmount() {
        return amount;
    }


    public int getPossibleValue() {
        for (int i = 0; i < values.length; i++) {
            if (values[i]) return i + 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PossibleValues)) return false;

        PossibleValues that = (PossibleValues) o;

        return Arrays.equals(getValues(), that.getValues());

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValues());
    }

}

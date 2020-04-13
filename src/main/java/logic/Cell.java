package logic;

public class Cell {
    private int value;
    private PossibleValues possibleValues;

    public Cell(int value,byte gridSize) {
        this.value = value;
        if (value == 0) {
            possibleValues = new PossibleValues(true,gridSize);
            return;
        }
        possibleValues = new PossibleValues(false,gridSize);
    }


    public Cell(Cell cell) {
        this.value = cell.getValue();
        this.possibleValues = new PossibleValues(cell.possibleValues);
    }

    public int getValue() {
        return value;
    }

    public boolean isPossible(int i) {
        return possibleValues.isPossible(i);
    }

    public void setValue(int value) {
        this.value = value;
        if (value != 0) possibleValues = new PossibleValues(false,possibleValues.getSize());
    }

    public PossibleValues getPossibleValues() {
        return possibleValues;
    }

    public void setValueImpossible(int value) {
        possibleValues.setImpossible(value);
    }

    public void setPossibleValues(PossibleValues possibleValues) {
        this.possibleValues = possibleValues;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public byte getAmountOfPossibleValues() {
        return possibleValues.getAmount();

    }


    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell cell = (Cell) o;

        if (getValue() != cell.getValue()) return false;
        return getPossibleValues().equals(cell.getPossibleValues());

    }

    @Override
    public int hashCode() {
        int result = getValue();
        result = 31 * result + getPossibleValues().hashCode();
        return result;
    }
}

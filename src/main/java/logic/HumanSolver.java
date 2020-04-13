package logic;


public class HumanSolver {
    Grid grid;

    public HumanSolver(Grid grid) {
        if (grid.isCorrect()) this.grid = grid;
        else throw new IllegalArgumentException("Grid isn't correct");

    }

    public Grid getGrid() {
        return grid;
    }


    private void calculate(CellAddress address) {
        Cell targetCell = grid.getCell(address);
        if (!targetCell.isEmpty()) throw new IllegalArgumentException("calculate invoked with not Empty Cell");
        if (targetCell.getAmountOfPossibleValues() == 1) {
            for (int j = 1; j < grid.getSize() + 1; j++) {
                if (targetCell.isPossible(j)) {
                    targetCell.setValue(j);
                    break;
                }
            }

        }
        for (int i = 1; i < grid.getSize() + 1; i++) {
            if (!targetCell.isPossible(i)) continue;
            if (calculateInRow(address, i) || calculateInColumn(address, i) || calculateInZone(address, i))
                return;
        }


    }


    private boolean calculateInRow(CellAddress address, int possibleValue) {
        if (!grid.getCell(address).isPossible(possibleValue))
            new IllegalArgumentException("possibleValue is impossible");
        int counter = 0;
        for (int j = 0; j < grid.getSize(); j++) {
            if (grid.getCell(address.getRowIndex(), j).isPossible(possibleValue)) {
                counter++;
                if (counter > 1) return false;
            }
        }

        grid.getCell(address).setValue(possibleValue);
        return true;
    }


    private boolean calculateInColumn(CellAddress address, int possibleValue) {
        if (!grid.getCell(address).isPossible(possibleValue))
            new IllegalArgumentException("possibleValue is impossible");
        int counter = 0;
        for (int j = 0; j < grid.getSize(); j++) {
            if (grid.getCell(j, address.getColumnIndex()).isPossible(possibleValue)) {
                counter++;
                if (counter > 1) return false;
            }
        }

        grid.getCell(address).setValue(possibleValue);
        return true;
    }


    private boolean calculateInZone(CellAddress address, int possibleValue) {
        if (!grid.getCell(address).isPossible(possibleValue))
            new IllegalArgumentException("possibleValue is impossible");
        int rowShift = ((address.getRowIndex()) / grid.getZoneSize()) * grid.getZoneSize();
        int columnShift = ((address.getColumnIndex()) / grid.getZoneSize()) * grid.getZoneSize();
        int counter = 0;
        for (int j = 0; j < grid.getZoneSize(); j++) {
            for (int k = 0; k < grid.getZoneSize(); k++) {
                if (grid.getCell(rowShift + j, columnShift + k).isPossible(possibleValue)) {
                    if (++counter > 1) return false;

                }
            }

        }

        grid.getCell(address).setValue(possibleValue);
        return true;
    }

    private void settingThePossibleValuesForCellInRow(CellAddress address) {
        Cell tmpCell;
        Cell targetCell = grid.getCell(address);
        for (int i = 0; i < grid.getSize(); i++) {
            tmpCell = grid.getCell(address.getRowIndex(), i);
            if (!tmpCell.isEmpty()) targetCell.setValueImpossible(tmpCell.getValue());
        }

    }

    private void settingThePossibleValuesForCellInColumn(CellAddress address) {
        Cell tmpCell;
        Cell targetCell = grid.getCell(address);
        for (int i = 0; i < grid.getSize(); i++) {
            tmpCell = grid.getCell(i, address.getColumnIndex()); //
            if (!tmpCell.isEmpty()) {
                targetCell.setValueImpossible(tmpCell.getValue());
            }
        }

    }

    private void settingThePossibleValuesForCellInZone(CellAddress address) {
        int rowShift = ((address.getRowIndex()) / grid.getZoneSize()) * grid.getZoneSize();
        int columnShift = ((address.getColumnIndex()) / grid.getZoneSize()) * grid.getZoneSize();
        Cell tmpCell;
        Cell targetCell = grid.getCell(address);
        for (int i = 0; i < grid.getZoneSize(); i++) {
            for (int j = 0; j < grid.getZoneSize(); j++) {
                tmpCell = grid.getCell(rowShift + i, columnShift + j);
                if (!tmpCell.isEmpty()) targetCell.setValueImpossible(tmpCell.getValue());
            }
        }

    }

    public void solve() {
        CellAddress address;
        ;
        boolean marker;
        boolean changeMarker;
        Cell tmpCell;
        Cell targetCell;
        do {
            marker = false;
            for (int i = 0; i < grid.getSize(); i++) {
                for (int j = 0; j < grid.getSize(); j++) {
                    address = new CellAddress(i, j);
                    targetCell = grid.getCell(address);
                    if (!targetCell.isEmpty()) continue;
                    tmpCell = new Cell(targetCell);
                    settingThePossibleValuesForCellInRow(address);
                    settingThePossibleValuesForCellInColumn(address);
                    settingThePossibleValuesForCellInZone(address);
                    calculate(address);
                    changeMarker = !tmpCell.equals(targetCell);
                    if (changeMarker) {
                        marker = changeMarker;
                    }
                }
            }

        } while (marker);

    }


}

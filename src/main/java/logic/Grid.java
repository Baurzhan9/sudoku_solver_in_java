package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grid {
    private ArrayList<ArrayList<Cell>> cellList;
    private byte size=9;
    private byte zoneSize=3;

    public Grid() {
        this((byte)9,(byte)3);
    }
    public Grid(byte gridSize,byte zoneSize){
        if(gridSize%zoneSize!=0) throw new IllegalArgumentException("FIELD_SIZE%ZONE_SIZE  !=0");
        size=gridSize;
        this.zoneSize=zoneSize;
        cellList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cellList.add(new ArrayList<Cell>());
            for (int j = 0; j < size; j++) {
                cellList.get(i).add(new Cell(0,size));
            }

        }

    }

    private byte getGridSizeFromFile(File file) throws FileNotFoundException { //TODo this code is bullshit rewrite this;
        Scanner in= new Scanner(file);
        int i=0;
        while (in.hasNext()){
            in.nextInt();
            i++;
        }
        return (byte) Math.sqrt(i);
    }
    public Grid(File file) {
        try {
            size= getGridSizeFromFile(file);
            Scanner in = new Scanner(file);

            cellList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                cellList.add(new ArrayList<Cell>());
                for (int j = 0; j < size; j++) {
                    cellList.get(i).add(new Cell(in.nextInt(),size));
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }

    }

    public Grid(Grid grid) {
        size=grid.getSize();
        cellList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cellList.add(new ArrayList<Cell>());
            for (int j = 0; j < size; j++) {
                cellList.get(i).add(new Cell(grid.getCell(i, j)));
            }
        }
    }


    public void replaceCell(CellAddress address, Cell cell) {
        replaceCell(address.getRowIndex(), address.getColumnIndex(), cell);
    }

    public void replaceCell(int rowIndex, int columnIndex, Cell cell) {
        cellList.get(rowIndex).remove(columnIndex);
        cellList.get(rowIndex).add(columnIndex, cell);
    }


    private boolean isRowCorrect(int rowIndex) {

        for (int i = 0; i < cellList.get(rowIndex).size(); i++) {
            if (cellList.get(rowIndex).get(i).isEmpty()) continue;
            for (int j = i + 1; j < cellList.get(rowIndex).size(); j++) {
                if (cellList.get(rowIndex).get(i).getValue() == cellList.get(rowIndex).get(j).getValue()) return false;
            }
        }
        return true;
    }

    private boolean isColumnCorrect(int columnIndex) {

        for (int i = 0; i < size; i++) {
            if (cellList.get(i).get(columnIndex).isEmpty()) continue;
            for (int j = i + 1; j < size; j++) {
                if (cellList.get(i).get(columnIndex).getValue() == cellList.get(j).get(columnIndex).getValue())
                    return false;
            }
        }
        return true;
    }

    private boolean isZoneCorrect(int rowIndex, int columnIndex) {
        int rowShift = (rowIndex / zoneSize) * zoneSize;
        int columnShift = (columnIndex / zoneSize) * zoneSize;
        for (int i = rowShift; i < zoneSize + rowShift; i++) {
            for (int j = columnShift; j < zoneSize + columnShift; j++) {
                if (cellList.get(i).get(j).isEmpty()) continue;
                for (int k = i; k < zoneSize + rowShift; k++) {
                    for (int l = j + 1; l < zoneSize + columnShift; l++) {
                        if (cellList.get(i).get(j).getValue() == cellList.get(k).get(l).getValue()) return false;
                    }
                }


            }
        }
        return true;
    }


    public boolean isCorrect() {
        for (int i = 0; i < size; i++) {
            if (!isRowCorrect(i) || !isColumnCorrect(i)) return false;
        }
        for (int i = 0; i < zoneSize; i++) {
            for (int j = 0; j < size; j++) {
                if (!isZoneCorrect(i, j)) return false;
            }
        }

        return true;
    }


    public boolean isSolved() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cellList.get(i).get(j).isEmpty()) return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Cell>> getCellList() {
        return cellList;
    }


    public Cell getCell(int rowCellIndex, int columnCellIndex) {
        return cellList.get(rowCellIndex).get(columnCellIndex);

    }

    public Cell getCell(CellAddress address) {
        return cellList.get(address.getRowIndex()).get(address.getColumnIndex());
    }

    public byte getSize(){
        return size;
    }
    public byte getZoneSize(){
        return zoneSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grid grid = (Grid) o;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!cellList.get(i).get(j).equals(grid.getCellList().get(i).get(j))) return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        return cellList != null ? cellList.hashCode() : 0;
    }


    public String toConsoleString() {

        StringBuilder resultString = new StringBuilder();
        StringBuilder lineString = new StringBuilder();
        int stringLength = (size * 2) + (size / zoneSize);
        for (int i = 0; i < stringLength; i++) {
            lineString.append("─");
        }
        resultString.append(lineString + "\n");
        for (int i = 0; i < cellList.size(); i++) {
            resultString.append("│");
            for (int j = 0; j < cellList.get(i).size(); j++) {
                resultString.append(cellList.get(i).get(j).toString());
                if ((j + 1) % zoneSize == 0) resultString.append("│");
                resultString.append(" ");
            }
            resultString.append("\n");
            if ((i + 1) % zoneSize == 0) resultString.append(lineString + "\n");

        }

        return resultString.toString();
    }

    public String toConsoleStringWithTheReleaseOfColor(int rowIndex, int columnIndex) {

        StringBuilder resultString = new StringBuilder();
        StringBuilder lineString = new StringBuilder();
        int stringLength = (size * 2) + (size / zoneSize);
        for (int i = 0; i < stringLength; i++) {
            lineString.append("─");
        }
        resultString.append(lineString + "\n");
        for (int i = 0; i < cellList.size(); i++) {
            resultString.append("│");
            for (int j = 0; j < cellList.get(i).size(); j++) {
                if (rowIndex == i && columnIndex == j) resultString.append(Constants.ANSI_RED);
                resultString.append(cellList.get(i).get(j).toString());
                resultString.append(Constants.ANSI_RESET);
                if ((j + 1) % zoneSize == 0) resultString.append("│");
                resultString.append(" ");
            }
            resultString.append("\n");
            if ((i + 1) % zoneSize == 0) resultString.append(lineString + "\n");

        }
        return resultString.toString();
    }

    public String toConsoleStringWithTheReleaseOfColor(CellAddress address) {
        return toConsoleStringWithTheReleaseOfColor(address.getRowIndex(), address.getColumnIndex());
    }

    @Override
    public String toString() {

        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < cellList.size(); i++) {
            for (int j = 0; j < cellList.get(i).size(); j++) {
                resultString.append(cellList.get(i).get(j).toString());
                resultString.append(" ");
            }
            resultString.append("\n");


        }

        return resultString.toString();
    }

}

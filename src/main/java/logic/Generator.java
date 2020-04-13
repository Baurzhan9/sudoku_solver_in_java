package logic;

import java.util.Random;


public class Generator {
    private Grid grid;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public static Grid generate(){
        Generator generator= new Generator();
        generator.generateGrid();
        return generator.getGrid();
    }
    private boolean cleanCell() {
        Random random = new Random();
        Grid tmpGrid = new Grid(grid);
        HumanSolver humanSolver;
        for (int i = 0; i < grid.getSize() * grid.getSize(); i++) {
            CellAddress address = new CellAddress(Math.abs(random.nextInt() % grid.getSize()), Math.abs(random.nextInt() % grid.getSize()));
            if (tmpGrid.getCell(address).isEmpty()) continue;
            tmpGrid.replaceCell(address, new Cell(0,grid.getSize()));
            humanSolver = new HumanSolver(new Grid(tmpGrid));
            humanSolver.solve();
            if (humanSolver.getGrid().isSolved()) {
                grid = new Grid(tmpGrid);
                return true;
            } else tmpGrid = new Grid(grid);

        }
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.getCell(i, j).isEmpty()) continue;
                tmpGrid = new Grid(grid);
                tmpGrid.replaceCell(i, j, new Cell(0,grid.getSize()));
                humanSolver = new HumanSolver(tmpGrid);
                humanSolver.solve();
                if (tmpGrid.isSolved()) {
                    grid = new Grid(tmpGrid);
                    return true;
                }
            }
        }
        return false;
    }

    private void  generateGrid() {
        Solver solver = new Solver(new Grid());
        solver.solve();
        grid = solver.getGrid();
        while (cleanCell()) ;


    }


}



import logic.Generator;
import logic.Grid;
import logic.Solver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class Processor {

    private File outputFile;
    private File inputFile;

    Processor(File outputFile, File inputFile) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
    }

    private void writeToFile(Grid grid) {

        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(outputFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(grid.toString());
            writer.write('\n');
            writer.flush();
        } catch (IOException e) {
            System.err.println("File isn't correct");
        }
    }

    public void generate() {
        Grid generatedGrid = Generator.generate();
        if (outputFile != null) {
            writeToFile(generatedGrid);

        } else System.out.println(generatedGrid.toConsoleString());
    }

    public void solve() {
        if (inputFile == null) {
            System.err.println("Use key -input_file_address");
            return;
        }
        Solver solver = new Solver(new Grid(inputFile));
        solver.solve();
        Grid solvedGrid = solver.getGrid();
        if (outputFile != null) {
            writeToFile(solvedGrid);
        } else System.out.println(solvedGrid.toConsoleString());

    }
}

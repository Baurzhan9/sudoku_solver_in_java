import com.beust.jcommander.JCommander;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        JCommander commander = new JCommander(settings, args); // simple one-liner
        commander.setProgramName("Sudoku");
        if (settings.isHelp()) {
            commander.usage();
            return;
        }
        if (settings.isGenerate() == settings.isSolve()) {
            System.out.println("Choose one option: generate or solve");
            return;
        }
        Main main = new Main();
        File inputFile = new File("c:/dir/input.txt");
        File outputFile = new File("c:/dir/output.txt");
        if (settings.getOutputFileAddress() != null) {
            outputFile = new File(settings.getOutputFileAddress());
        }
        if (settings.getInputFileAddress() != null) {
            inputFile = new File(settings.getInputFileAddress());
        }
        Processor processor = new Processor(outputFile, inputFile);
        if (settings.isGenerate()) {
            processor.generate();
        }
        if (settings.isSolve()) {
            processor.solve();
        }
    }

}


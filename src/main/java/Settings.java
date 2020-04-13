import com.beust.jcommander.Parameter;


class Settings {


    @Parameter(names = "-solve", description = "Solve sudoku")
    boolean solve ;
    @Parameter(names = "-generate", description = "Generate new sudoku")
    boolean generate = true;


    @Parameter(names = "-output_file_address", description = "file address with sudoku", required = false)
    private String outputFileAddress;
    @Parameter(names = "-input_file_address", description = "file address with sudoku", required = false)
    private String inputFileAddress;


    @Parameter(names = "-help", description = "Показать это сообщение", help = true)
    private boolean help;


    public boolean isSolve() {
        return solve;
    }

    public boolean isGenerate() {
        return generate;
    }

    public String getInputFileAddress() {
        return inputFileAddress;
    }

    public String getOutputFileAddress() {
        return outputFileAddress;
    }

    public boolean isHelp() {
        return help;
    }
}
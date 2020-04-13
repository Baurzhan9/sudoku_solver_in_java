package logic;


abstract class Constants {
    static
    final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static long searchTime = 600;
    private Constants() {

    }



    public static void setSearchTime(long searchTime) {
        Constants.searchTime = searchTime;
    }
}

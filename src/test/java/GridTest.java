

import org.junit.Test;
import com.github.mejiomah17.sudoku.Grid;

import java.io.File;

/**
 * Created by mark on 21.07.2016.
 */
public class GridTest {
    @Test
    public void isCorrect() throws Exception {
        Grid grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/WrongColumn"));
        assert (!grid.isCorrect());
        grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/WrongColumn"));
        assert (!grid.isCorrect());
        grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/WrongZone"));
        assert (!grid.isCorrect());
        grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/NormalTest"));
        assert (grid.isCorrect());
    }

    @Test
    public void isSolved() throws Exception {
        Grid grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/ZeroGrid"));
        assert (!grid.isSolved());
        grid = new Grid(new File("src/test/testTextFiles/GridTestFiles/SolvedGrid"));
        assert (grid.isSolved());

    }

}
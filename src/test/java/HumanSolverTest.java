
import org.junit.Test;
import com.github.mejiomah17.sudoku.Grid;
import com.github.mejiomah17.sudoku.HumanSolver;

import java.io.File;

public class HumanSolverTest {

    @Test
    public void solve() throws Exception {

        HumanSolver humanSolver = new HumanSolver(new Grid(new File("src/test/testTextFiles/HumanSolverTestFiles/NormalTest")));
        humanSolver.solve();
        assert (humanSolver.getGrid().isSolved() && humanSolver.getGrid().isCorrect());
    }


}
import org.junit.Test;
import com.github.mejiomah17.sudoku.Grid;
import com.github.mejiomah17.sudoku.Solver;

/**
 * Created by mark on 01.09.16.
 */
public class SolverTest {
    @Test
    public void solve() throws Exception {
        Solver solver = new Solver(new Grid((byte)9,(byte)3));
       // Solver solver= new Solver(new Grid());
        solver.solve();
        System.out.printf(solver.getGrid().toString());
        assert (solver.getGrid().isCorrect()&&solver.getGrid().isSolved());

    }



}
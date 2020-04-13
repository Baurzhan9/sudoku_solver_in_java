
import com.github.mejiomah17.sudoku.Cell;
import org.junit.Test;
/**
 * Created by mark on 21.07.2016.
 */
public class CellTest {
    @Test
    public void equals() throws Exception {
        Cell cell = new Cell(0,(byte)9);
        assert (cell.equals(new Cell(0,(byte)9)));
        cell.setValueImpossible(1);
        assert (!cell.equals(new Cell(0,(byte)9)));
        Cell otherCell = new Cell(0,(byte)9);
        otherCell.setValueImpossible(1);
        assert (otherCell.equals(cell));

    }


}
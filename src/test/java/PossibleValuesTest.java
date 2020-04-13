
import org.junit.Test;
import com.github.mejiomah17.sudoku.PossibleValues;


public class PossibleValuesTest {
    PossibleValues possibleValues = new PossibleValues(true, (byte) 9);

    @Test
    public void equals() throws Exception {
        assert (possibleValues.equals(new PossibleValues(true,(byte) 9)));
        assert (!possibleValues.equals(new PossibleValues(false,(byte) 9)));

    }

    @Test
    public void isPossible() throws Exception {
        for (int i = 1; i < possibleValues.getSize(); i++) {
            assert (possibleValues.isPossible(i));
        }
        new PossibleValues(possibleValues);

    }

    @Test
    public void setImpossible() throws Exception {
        possibleValues.setImpossible(4);

        assert (!possibleValues.isPossible(4));
        assert (possibleValues.getSize() - 1 == possibleValues.getAmount());
    }

}
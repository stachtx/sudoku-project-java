
package grupatp.application;

import grupatp.model.SudokuBoard;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public enum Difficulty {

    Easy(10),
    Medium(30),
    Hard(50);

    private final int amount;

    private Difficulty(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public SudokuBoard deleteFields(final SudokuBoard board) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 81; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < amount; i++) {
            board.board.get(list.get(i)).setFieldValue(0);
        }
        return board;
    }
}

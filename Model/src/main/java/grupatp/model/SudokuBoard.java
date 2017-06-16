package grupatp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import static org.apache.commons.lang.SerializationUtils.deserialize;
import static org.apache.commons.lang.SerializationUtils.serialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Paweł Ograbek
 * @author Tomasz Stachura
 */
public class SudokuBoard implements Serializable, Cloneable{

    private static final long serialVersionUID = 1L;

    public List<SudokuField> board = Arrays.asList(new SudokuField[81]);
    private final List<SudokuRow> sudokuRow = Arrays.asList(new SudokuRow[9]);
    private final List<SudokuColumn> sudokuColumn = Arrays.asList(new SudokuColumn[9]);
    private final List<SudokuBox> sudokuBox = Arrays.asList(new SudokuBox[9]);

    public SudokuBoard() {
        SudokuField[] row = new SudokuField[9];
        SudokuField[] column = new SudokuField[9];
        SudokuField[] box = new SudokuField[9];

        for (int i = 0; i < board.size(); i++) {
            board.set(i, new SudokuField());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                row[j] = board.get(9 * i + j);
                column[j] = board.get(9 * j + i);
                box[j] = board.get((j / 3) * 9 + (j % 3) + (i / 3) * 27 + (i % 3) * 3);
            }
            sudokuRow.set(i, new SudokuRow(row));
            sudokuColumn.set(i, new SudokuColumn(column));
            sudokuBox.set(i, new SudokuBox(box));
        }
    }

    public SudokuRow getRow(final int row) {
        return sudokuRow.get(row);
    }

    public SudokuColumn getColumn(final int column) {
        return sudokuColumn.get(column);
    }

    public SudokuBox getBox(final int box) {
        return sudokuBox.get(box);
    }

    public SudokuField getField(final int x, final int y) {
        return board.get(x + 9 * y);
    }

    public int getBoard(final int x, final int y) {
        return board.get(x + 9 * y).getFieldValue();
    }

    public void setBoard(final int x, final int y, final int value) {
        if (checkBoard(x + 9 * y, value)) {
            board.get(x + 9 * y).setFieldValue(value);
        }
    }

    private boolean checkBoard(final int x, final int n) {
        for (int i = 0; i < 9; i++) {
            if (n == board.get(9 * i + x % 9).getFieldValue()) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (n == board.get(i + (x / 9) * 9).getFieldValue()) {
                return false;
            }
        }

        int BoxX = (x / 9) / 3, BoxY = (x % 9) / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (n == board.get((i + BoxX * 3) * 9 + (j + BoxY * 3)).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkSolve(final int x, final int n, final int[] board) {
        for (int i = 0; i < 9; i++) {
            if (n == board[9 * i + x % 9]) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (n == board[i + (x / 9) * 9]) {
                return false;
            }
        }

        int BoxX = (x / 9) / 3, BoxY = (x % 9) / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (n == board[(i + BoxX * 3) * 9 + (j + BoxY * 3)]) {
                    return false;
                }
            }
        }
        return true;
    }
               

    @Override
    public String toString() {
        ReflectionToStringBuilder
                .setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof SudokuBoard) {
            final SudokuBoard other = (SudokuBoard) obj;
            return new EqualsBuilder()
                    .append(board, other.board)
                    .append(sudokuRow, other.sudokuRow)
                    .append(sudokuColumn, other.sudokuColumn)
                    .append(sudokuBox, other.sudokuBox)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(board)
                .append(sudokuRow)
                .append(sudokuColumn)
                .append(sudokuBox)
                .toHashCode();
    }
    
    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        return (SudokuBoard) deserialize(serialize(this));
    }
    
    public boolean check(final int x, final int y, final int n) {
        return checkBoard(x + y * 9, n);
    }


   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.model;

import grupatp.serialization.FileSudokuBoardDao;
import grupatp.model.SudokuRow;
import grupatp.model.SudokuField;
import grupatp.model.SudokuColumn;
import grupatp.model.SudokuBox;
import grupatp.model.BacktrackingSudokuSolver;
import grupatp.model.SudokuBoard;
import grupatp.model.SudokuSolver;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.not;

/**
 *
 * @author Paweł
 */
public class SudokuSolverTest {

    public SudokuSolverTest() {
    }

    SudokuSolver a = new BacktrackingSudokuSolver();
    SudokuBoard b1 = new SudokuBoard();
    SudokuBoard b2 = new SudokuBoard();
    SudokuBoard b3 = new SudokuBoard();

    @Test
    public void testSolve1() {
        a.solve(b1);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                assertTrue(b2.check(x, y, b1.getBoard(x, y)));
                b2.setBoard(x, y, b1.getBoard(x, y));
            }
        }
    }

    @Test
    public void testSolve2() {
        a.solve(b1);
        a.solve(b2);
        a.solve(b3);
        int[] tmp1 = new int[81];
        int[] tmp2 = new int[81];
        int[] tmp3 = new int[81];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                tmp1[x + y * 9] = b1.getBoard(x, y);
                tmp2[x + y * 9] = b2.getBoard(x, y);
                tmp3[x + y * 9] = b3.getBoard(x, y);
            }
        }
        assertThat(tmp1, not(tmp2));
        assertThat(tmp1, not(tmp3));
        assertThat(tmp2, not(tmp3));
    }

    @Test
    public void testSolve3() {
        a.solve(b1);
        for (int i = 0; i < 9; i++) {
            assertTrue(b1.getRow(i).verify());
            assertTrue(b1.getColumn(i).verify());
            assertTrue(b1.getBox(i).verify());
        }
    }

    @Test
    public void testEquals() {
        SudokuField[] f = new SudokuField[9];
        SudokuField f1=new SudokuField();
        SudokuField f2=new SudokuField();
        SudokuRow s1 = new SudokuRow(f);
        SudokuRow s2 = new SudokuRow(f);
        SudokuColumn s3 = new SudokuColumn(f);
        SudokuColumn s4 = new SudokuColumn(f);
        SudokuBox s5 = new SudokuBox(f);
        SudokuBox s6 = new SudokuBox(f);
        SudokuBoard s7 = new SudokuBoard();
        SudokuBoard s8 = s7;
        f1.setFieldValue(2);
        f2.setFieldValue(2);
        assertTrue(f1.equals(f2) && f2.equals(f1));
        assertTrue(f1.hashCode() == f2.hashCode());
        assertTrue(s1.equals(s2) && s2.equals(s1));
        assertTrue(s1.hashCode() == s2.hashCode());
        assertTrue(s3.equals(s4) && s4.equals(s3));
        assertTrue(s3.hashCode() == s4.hashCode());
        assertTrue(s5.equals(s6) && s6.equals(s5));
        assertTrue(s5.hashCode() == s6.hashCode());
        assertTrue(s7.equals(s8));
        assertTrue(s7.hashCode() == s8.hashCode());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException, Exception {
        SudokuBoard tmpA = new SudokuBoard();
        FileSudokuBoardDao tmp = new FileSudokuBoardDao("tmp.ser");
        a.solve(tmpA);
        tmp.write(tmpA);
        SudokuBoard tmpB = new SudokuBoard();
        tmpB = tmp.read();
        assertEquals(tmpA, tmpB);
        assertTrue(tmpA.equals(tmpB));
    }
    
    @Test
    public void testClone() throws CloneNotSupportedException {
        SudokuField[] f = new SudokuField[9];
        SudokuField f1=new SudokuField();
        SudokuRow s1 = new SudokuRow(f);
        SudokuColumn s2 = new SudokuColumn(f);
        SudokuBox s3 = new SudokuBox(f);
        SudokuBoard s4 = new SudokuBoard();
        
        assertEquals(f1,f1.clone());
        assertEquals(s1,s1.clone());
        assertEquals(s2,s2.clone());
        assertEquals(s3,s3.clone());
        assertEquals(s4,s4.clone());
    }
}

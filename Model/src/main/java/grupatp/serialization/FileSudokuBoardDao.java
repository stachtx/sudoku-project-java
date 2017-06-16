/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.serialization;

import grupatp.model.SudokuBoard;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    private final String path;

    public FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() throws ClassNotFoundException, IOException {
        SudokuBoard board = null;
        try (FileInputStream in = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(in);) {
            return board = (SudokuBoard) (ois.readObject());
        }
    }

    @Override
    public void write(final SudokuBoard obj) throws FileNotFoundException, IOException {
        try (FileOutputStream out = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(out);) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    @Override
    public void finalize() throws Throwable {
        try {
            close(); // close open files
        } finally {
            super.finalize();
        }
    }

    @Override
    public void close() throws Exception {

    }

}

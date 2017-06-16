/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.serialization;

import grupatp.model.SudokuBoard;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String name;
    private static Connection conn = null;
    private static Statement stmt = null;
    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String DB_URL = "jdbc:derby:Sudoku.db";

    public JdbcSudokuBoardDao(final String name) throws ClassNotFoundException, SQLException {
        this.name = name;
        this.setupDataBase();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setupDataBase() throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER);
        boolean exist = true;
        if (!Files.exists(Paths.get("Sudoku"))) {
            exist = false;
            conn = DriverManager.getConnection(DB_URL + ";create=true");
        } else {
            conn = DriverManager.getConnection(DB_URL + ";create=false");
        }
        ResultSet rs = conn.getMetaData().getTables(null, null, "SUDOKUFIELDS", null);

        if (!rs.next()) {
            stmt = conn.createStatement();
            stmt.execute("CREATE SCHEMA SCHEMAT");
            String query = "CREATE TABLE SUDOKUFIELDS ("
                    + "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name_board varchar(30),"
                    + "index integer not null,"
                    + "value integer not null)";

            stmt.execute(query);
        }
        conn.close();

    }

    @Override
    public SudokuBoard read() throws SQLException, ClassNotFoundException {
        SudokuBoard tmp = new SudokuBoard();
        conn = DriverManager.getConnection(DB_URL + ";create=false");
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select index , value from SUDOKUFIELDS where name_board='" + name + "'");

        while (rs.next()) {
            int i = rs.getInt("index");
            int v = rs.getInt("value");
            tmp.setBoard(i / 9, i % 9, v);
        }

        rs.close();
        stmt.close();
        conn.close();
        return tmp;

    }

    /**
     *
     * @param obj
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void write(final SudokuBoard obj) throws SQLException, ClassNotFoundException {

        conn = DriverManager.getConnection(DB_URL + ";create=false");
        stmt = conn.createStatement();

        for (int i = 0; i < 81; i++) {
            stmt.execute("insert into SUDOKUFIELDS(name_board, index, value) values ('" + name + "', "
                    + Integer.toString(i) + ", " + Integer.toString(obj.board.get(i).getFieldValue()) + ")");
        }
        stmt.close();
        conn.close();

    }

    /**
     *
     * @throws Exception
     * @throws Throwable
     */
    @Override
    public void finalize() throws Exception, Throwable {
        try {
            stmt.close();
            conn.close();
        } finally {
            super.finalize();
        }
    }

    @Override
    public void close() throws Exception {

    }
}

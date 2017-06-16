
package grupatp.serialization;

import java.sql.SQLException;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class SudokuBoardDaoFactory {

    public Dao getFileDao(final String path) {
        if (path.contains(".xml")) {
            return new FileSudokuBoardDaoXstream(path);
        } else {
            return new FileSudokuBoardDao(path);
        }
    }
    public Dao getJdbcDao(final String name) throws ClassNotFoundException, SQLException {
        return new JdbcSudokuBoardDao(name);
    }
}

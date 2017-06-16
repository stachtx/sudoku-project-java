/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.serialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import grupatp.model.SudokuBoard;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class FileSudokuBoardDaoXstream implements Dao<SudokuBoard>, AutoCloseable {
    
    private final String path;
    private static final XStream XSTREAM = new XStream(new StaxDriver());
    
    public FileSudokuBoardDaoXstream(final String path) {
        this.path = path;
    }
    
    @Override
    public SudokuBoard read()throws ClassNotFoundException, IOException {
        SudokuBoard obj = null;
            File file = new File(path);
            file.createNewFile();
            obj = (SudokuBoard) XSTREAM.fromXML(file);
            return obj;
       
        
    }


    @Override
    public void write(final SudokuBoard obj)  throws FileNotFoundException, IOException  {
        
            XSTREAM.toXML(obj, new FileWriter(path));
        
    }

    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

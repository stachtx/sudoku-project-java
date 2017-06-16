/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.serialization;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 * @param <T>
 */
public interface Dao<T> extends AutoCloseable {

    T read() throws Exception;

    void write(T obj) throws Exception;
}

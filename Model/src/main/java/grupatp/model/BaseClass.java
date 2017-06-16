/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Paweł Ograbek
 * @author Tomasz Stachura
 */
public abstract class BaseClass implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 1L;
    private List<SudokuField> listField = Arrays.asList(new SudokuField[9]);
    
    public BaseClass(final SudokuField[] sudokuField) {
        for (int i = 0; i < 9; i++) {
            listField.set(i, new SudokuField());
        }
        for (int i = 0; i < 9; i++) {
            listField.set(i, sudokuField[i]);
        }
    }
    
    
    
     @Override
    public String toString() {    
        ReflectionToStringBuilder
                .setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof BaseClass) {
            final BaseClass other = (BaseClass) obj;
            return new EqualsBuilder()
                    .append(listField, other.listField)
                    .isEquals();
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(listField)
                .toHashCode();
    }
    
     @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean verify() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listField.get(i).getFieldValue() == listField.get(j).getFieldValue()) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            count = 0;
        }
        return true;
    }
}

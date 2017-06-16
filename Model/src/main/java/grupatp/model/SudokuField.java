/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Paweł Ograbek
 * @author Tomasz Stachura
 */
public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

  
    private static final Logger logger = LoggerFactory.getLogger(SudokuField.class);
    private static final long serialVersionUID = 1L;
    private int value = 0;
    
    
    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
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
        if (obj instanceof SudokuField) {
            final SudokuField other = (SudokuField) obj;
            return new EqualsBuilder()
                    .append(value, other.value)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(value)
                .toHashCode();
    }

    @Override
    public int compareTo(final SudokuField other) {
        return Integer.compare(value, other.getFieldValue());
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}

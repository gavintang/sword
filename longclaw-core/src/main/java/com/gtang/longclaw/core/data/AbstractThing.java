package com.gtang.longclaw.core.data;

import com.gtang.longclaw.core.util.validate.Validateable;
import com.gtang.longclaw.core.util.validate.ValidationException;
import com.gtang.longclaw.core.util.validate.ValidationUtil;
import java.io.Serializable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

abstract public class AbstractThing
        implements Serializable, Validateable, Identifiable {

    private long id;
    
    public AbstractThing() {}

    public AbstractThing(AbstractThing other) {
        setId(other.getId());
    }

    public AbstractThing(final long id) {
        setId(id);
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public boolean isSame(final AbstractThing thing) {
        return thing != null && thing.getId() == getId();
    }

    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append('[');
        paramString(sb);
        sb.append(']');
        return sb.toString();
    }

    /**
     * @param buf 
     * @see #toString()
     */
    protected void paramString(final StringBuffer buf) {
        buf.append("id=").append(getId());
        buf.append(",valid=").append(valid);
    }

    /**
     * A master hash function that instantiates a {@linkplain HashBuilder} and
     * then passes it to a separate, overloaded method for hash calculation.
     * Intended to make the calculation of hashes for business objects easier to
     * implement by localizing the hash calculation to the objects that contain
     * the various fields. </p>
     *
     * Intended to be implemented using the following design pattern: </p>
     *
     * <pre>
     * public class MyObject
     *   extends Thing
     * {
     *    .
     *    .
     *
     *    private String myField;
     *    private int myOtherField;
     *    .
     *    .
     *
     *     protected
     *     int hashCode (final HashBuilder hash)
     *     {
     *       hash.add (myField);
     *       hash.add (myOtherField);
     *       return;
     *     }
     *   }
     * </prE>
     *
     * @see Object#hashCode()
     * @return the hashed value
     */
    @Override
    public final int hashCode() {
        final HashCodeBuilder hash = new HashCodeBuilder();
        hashCode(hash);
        return hash.hashCode();
    }

    /**
     * @param hash
     * @see #hashCode()
     */
    protected void hashCode(final HashCodeBuilder hash) {
        hash.append(getId());
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractThing o = (AbstractThing) obj;
        return ObjectUtils.compare(this.getId(), o.getId()) == 0;
    }

    private boolean valid = false;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    @Override
    public void validate() throws ValidationException {
        ValidationUtil.validate(this);
    }

}

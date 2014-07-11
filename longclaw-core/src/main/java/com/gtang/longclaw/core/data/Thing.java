package com.gtang.longclaw.core.data;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.annotations.AccessType;

/**
 *.
 *
 * @author Lee Crawford
 * Copyright (c) 2006 - 2010, Twofish, Inc.
 */

@MappedSuperclass
@EntityListeners({})
@SuppressWarnings("serial")
@XmlAccessorType (XmlAccessType.NONE)
abstract public class Thing
	extends AbstractThing
{
    public Thing ()
    {
    	super();
        return;
    }
    
    public Thing(Thing other)
    {
    	super(other);
    	return; 
    }

    /***
    @Generated(GenerationTime.ALWAYS) 
    @Column (name="ID", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO,  generator="IdGenerator")
    @Column (name="ID")

	***/

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,  generator="IdGenerator")
    @Column (name="ID")
    @AccessType("property")
    private long id;

    @Override
    public long getId ()
    {
	    return this.id;
    }
    
    @Override
    public void setId(long id)
    {
    	this.id = id;
    }
}


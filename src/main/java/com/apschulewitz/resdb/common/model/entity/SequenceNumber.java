/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apschulewitz.resdb.common.model.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "resdb_sequence_number")
@NamedQueries({
	@NamedQuery(name = "SequenceNumber.findAll",
				query = "SELECT s FROM SequenceNumber s")
})
public class SequenceNumber implements Serializable
{
	private static final long serialVersionUID = -7062201696679878346L;

	@Id
	@Column(name="table_name", nullable=false, length = 30)
	private String tableName;

	@Column(name = "next_id", nullable = false)
    private long nextId;

    public SequenceNumber()
    {

    }

    public SequenceNumber(String table, long id)
    {
        tableName = table;
        nextId = id;
    }

    public long getNextId()
    {
        return nextId;
    }

    public void setNextId(long nextId)
    {
        this.nextId = nextId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    @Override
    public Object clone()
    {
        return new SequenceNumber(getTableName(), getNextId());
    }

    @Override
    public boolean equals(Object o)
    {
    	if (o == null)
    		return false;
    	else if (this == o)
    		return true;
    	else if (!(o instanceof SequenceNumber))
            return false;

        SequenceNumber other = (SequenceNumber)o;

        return tableName == null ? other.getTableName() == null : tableName.equals(other.getTableName());
    }

    @Override
    public int hashCode()
    {
        return tableName == null ? 0 : tableName.hashCode();
    }

    @Override
    public String toString()
    {
        return tableName == null ? "null" : tableName;
    }

}

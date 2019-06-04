/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.security.model.entity.primarykey;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author adrian
 */
public class UserGroupMembershipId implements Serializable
{

	private static final long serialVersionUID = 7878172276329944974L;

    private String logonName;
    private String groupName;
    private Date dateOfMembership;

    public UserGroupMembershipId() {}

    public UserGroupMembershipId(final String user, final String group, final Date start)
    {
        logonName = user;
        groupName = group;
        dateOfMembership = start;
    }

    public void setLogonName(String logon)
    {
        logonName = logon;
    }

    public String getLogonName()
    {
        return logonName;
    }

    public void setGroupName(String group)
    {
        groupName = group;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setDateOfMembership(Date start)
    {
        dateOfMembership = start;
    }

    public Date getDateOfMembership()
    {
        return dateOfMembership;
    }

//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null) return false;
//        if (!(o instanceof UserGroupMembershipId)) return false;
//        final UserGroupMembershipId id = (UserGroupMembershipId)o;
//        if (!logonName.equals(id.getLogonName()))
//            return false;
//        if (!groupName.equals(id.getGroupName()))
//            return false;
//        if (!dateOfMembership.equals(id.getDateOfMembership()))
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return logonName.hashCode() + groupName.hashCode() + dateOfMembership.hashCode();
//    }
}

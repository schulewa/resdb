package com.apschulewitz.resdb.security.model.entity.primarykey;

public class UserGroupPermissionId {

	private String logonName;
	private Integer permission;

	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public Integer getPermission() {
		return permission;
	}
	public void setPermission(Integer permission) {
		this.permission = permission;
	}


}

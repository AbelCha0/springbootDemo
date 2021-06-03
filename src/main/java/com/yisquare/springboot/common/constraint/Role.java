package com.yisquare.springboot.common.constraint;


public enum Role {
    SUPERADMIN(0,"superAdmin"),
    SYSADMIN(1,"systemAdmin"),
    SYSOWNER(2,"systemOwner"),
    SYSMEMBER(3,"systemMember");

    private String roleName;
    private int roleID;

   private Role(int roleID, String roleName) {

       this.roleID = roleID;
       this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }
    public int getRoleID(){
        return roleID;
    }

}

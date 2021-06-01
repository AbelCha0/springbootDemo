package com.yisquare.springboot.pojo;


public enum Role {
    SYSADMIN(0,"systemAdmin"),
    SYSMEMBER(1,"systemMember"),
    SUPERADMIN(2,"superAdmin");
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

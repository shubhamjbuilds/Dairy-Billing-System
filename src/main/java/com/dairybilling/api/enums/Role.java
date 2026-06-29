package com.dairybilling.api.enums;

public enum Role {
	SUPER_ADMIN,    //-- Can create Dairy Groups (The ultimate system owner)
    PARENT_ADMIN,   // --Can approve Branches under their specific Dairy Group
    BRANCH_ADMIN,   //-- Can approve Customers and collect milk
    FARMER          //-- Can only view their own billing reports

}

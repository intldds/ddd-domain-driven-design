package com.finance.project.domainLayer.entitiesInterfaces;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;


public interface Owner {

    boolean addCategory(CategoryID categoryID);

    boolean addAccount(AccountID accountID);
}

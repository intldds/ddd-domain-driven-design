package com.finance.project.domainLayer.entitiesInterfaces;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;

/**
 * The interface Owner.
 */
public interface Owner {

    /**
     * Add category boolean.
     *
     * @param categoryID the category id
     * @return the boolean
     */
    boolean addCategory(CategoryID categoryID);

    /**
     * Add account boolean.
     *
     * @param accountID the account id
     * @return the boolean
     */
    boolean addAccount(AccountID accountID);
}

package com.finance.project;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;
import com.finance.project.dtos.dtos.CreatePersonCategoryDTO;
import com.finance.project.dtos.dtos.CreatePersonDTO;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtosAssemblers.CreatePersonAccountDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.CreatePersonCategoryDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.CreatePersonDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.CreatePersonTransactionDTOAssembler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Bootstrapping {


    /*

        Context:

        - 2 Addresses
              "porto"
              "lisbon"

        - 5 Persons
              4 from family ("Miguel" [son], "Bernardo" [son], "Maria" [mother], "Pedro" [father])
              1 friend ("Francis")

        - 2 Groups
              "Lemos Family" [4 elements]
              "Skate Friends" [3 elements]

        - 3 Accounts (per person / per group)
              Company
              Bank Account
              Wallet

        - 3 Categories (per person / per group)
              Salary
              IRS
              Food

        - 3 Transactions (per person / per group)
              Salary January
              IRS January
              Food January

     */

    public static void loadData(CreatePersonService createPersonService, CreateGroupService createGroupService) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Addresses [2]

        // [i] Porto
        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        Address porto = Address.createAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);


        // [ii] Lisbon
        String lisboaStreet = "Av. José Malhoa";
        String lisboaDoorNumber = "12";
        String lisboaPostCode = "1099 - 017";
        String lisboaCity = "Lisboa";
        String lisboaCountry = "Portugal";

        Address lisbon = Address.createAddress(lisboaStreet, lisboaDoorNumber, lisboaPostCode, lisboaCity, lisboaCountry);


        // Persons [5]

        // [i] Miguel - Son
        String miguelEmail = "miguel@gmail.com";
        String miguelName = "Miguel Lemos";
        String miguelBirthdate = LocalDate.of(2000, 10, 23).format(formatter);
        String miguelBirthplace = "Vila Nova de Gaia";

        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(miguelEmail, miguelName,
                miguelBirthdate, miguelBirthplace);

        PersonID miguelPersonID = PersonID.createPersonID(miguelEmail);
        createPersonService.createAndSavePerson(createPersonDTO);
        createPersonService.addAddressToPerson(miguelPersonID, porto);

        // Accounts Miguel

        // [i.1] Company
        String miguelCompanyDenomination = "Company";
        String miguelCompanyDescription = "Company account";

        // Create CreatePersonAccountDTO
        CreatePersonAccountDTO createPersonCompanyAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(miguelEmail, miguelCompanyDescription, miguelCompanyDenomination);

        createPersonService.addAccountToPerson(createPersonCompanyAccountDTO);
        AccountID miguelCompanyAccountID = AccountID.createAccountID(miguelCompanyDenomination, miguelPersonID);

        // [i.2] Bank Account
        String miguelBankAccountDenomination = "Bank Account";
        String miguelBankAccountDescription = "Personal bank account";

        // Create CreatePersonAccountDTO
        CreatePersonAccountDTO createPersonBankAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(miguelEmail, miguelBankAccountDescription, miguelBankAccountDenomination);

        createPersonService.addAccountToPerson(createPersonBankAccountDTO);
        AccountID miguelBankAccountID = AccountID.createAccountID(miguelBankAccountDenomination, miguelPersonID);

        // Categories Miguel

        // [ii.1] Salary
        String miguelSalaryDenomination = "Salary";

        // Create AddCategoryDTO
        CreatePersonCategoryDTO addCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(miguelEmail, miguelSalaryDenomination);

        createPersonService.addCategoryToPerson(addCategoryDTO);
        CategoryID miguelSalaryCategoryID = CategoryID.createCategoryID(miguelSalaryDenomination, miguelPersonID);

        // Transactions Miguel

        // [iii.1] Salary January
        String miguelCredit = "credit";
        String miguelSalaryJanuaryDescription = "January salary";
        double miguelSalaryJanuaryAmount = 1500;
        String miguelSalaryJanuaryDate = "2020-01-21";

        // Create a Person Transaction DTO
        CreatePersonTransactionDTO createmiguelTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(miguelEmail,
                miguelSalaryDenomination, miguelCredit, miguelSalaryJanuaryDescription, miguelSalaryJanuaryAmount, miguelCompanyDenomination,
                miguelBankAccountDenomination, miguelSalaryJanuaryDate);

        createPersonService.addPersonTransaction(createmiguelTransactionDTO);


        // [ii] Bernardo - Son
        String bernardoEmail = "bernardo@gmail.com";
        String bernardoName = "Bernardo Lemos";
        String bernardoBirthdate = LocalDate.of(2005, 12, 23).format(formatter);
        String bernardoBirthplace = "Lisboa";
        CreatePersonDTO createbernardoDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(bernardoEmail, bernardoName, bernardoBirthdate, bernardoBirthplace);

        PersonID bernardoPersonID = PersonID.createPersonID(bernardoEmail);
        createPersonService.createAndSavePerson(createbernardoDTO);
        createPersonService.addAddressToPerson(bernardoPersonID, lisbon);

        createPersonAccounts(bernardoPersonID, createPersonService);
        createPersonCategories(bernardoPersonID, createPersonService);
        createPersonTransactions(bernardoPersonID, createPersonService);


        // [iii] Maria - Mother
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Lemos";
        String mariaBirthdate = LocalDate.of(1963, 01, 9).format(formatter);
        String mariaBirthplace = "Vila Nova de Gaia";
        CreatePersonDTO createmariaDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        PersonID mariaPersonID = PersonID.createPersonID(mariaEmail);
        createPersonService.createAndSavePerson(createmariaDTO);
        createPersonService.addAddressToPerson(mariaPersonID, porto);

        createPersonService.addMotherToPerson(miguelPersonID, mariaPersonID);
        createPersonService.addMotherToPerson(bernardoPersonID, mariaPersonID);

        createPersonAccounts(mariaPersonID, createPersonService);
        createPersonCategories(mariaPersonID, createPersonService);
        createPersonTransactions(mariaPersonID, createPersonService);


        // [iv] Pedro - Father
        String pedroEmail = "pedro@gmail.com";
        String pedroName = "Pedro Lemos";
        String pedroBirthdate = LocalDate.of(1964, 01, 16).format(formatter);
        String pedroBirthplace = "Vila Nova de Gaia";
        CreatePersonDTO createpedroDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(pedroEmail, pedroName, pedroBirthdate, pedroBirthplace);

        PersonID pedroPersonID = PersonID.createPersonID(pedroEmail);
        createPersonService.createAndSavePerson(createpedroDTO);
        createPersonService.addAddressToPerson(pedroPersonID, porto);

        createPersonService.addFatherToPerson(miguelPersonID, pedroPersonID);
        createPersonService.addFatherToPerson(bernardoPersonID, pedroPersonID);

        createPersonAccounts(pedroPersonID, createPersonService);
        createPersonCategories(pedroPersonID, createPersonService);
        createPersonTransactions(pedroPersonID, createPersonService);


        // [v] Francis - Friend
        String francisEmail = "francis@gmail.com";
        String francisName = "Francis Starlite";
        String francisBirthdate = LocalDate.of(2003, 03, 15).format(formatter);
        String francisBirthplace = "Vila Nova de Gaia";
        CreatePersonDTO createfrancisDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(francisEmail, francisName, francisBirthdate, francisBirthplace);

        PersonID francisPersonID = PersonID.createPersonID(francisEmail);
        createPersonService.createAndSavePerson(createfrancisDTO);
        createPersonService.addAddressToPerson(francisPersonID, porto);

        createPersonAccounts(francisPersonID, createPersonService);
        createPersonCategories(francisPersonID, createPersonService);
        createPersonTransactions(francisPersonID, createPersonService);


        // Siblings [i] + [ii]
        createPersonService.addSiblingToPerson(miguelPersonID, bernardoPersonID);
        createPersonService.addSiblingToPerson(bernardoPersonID, miguelPersonID);


        // Groups [2]

        // [i] Lemos Family
        String lemosFamilyDenomination = "Lemos Family";
        String lemosFamilyDescription = "All members from Lemos family";

        GroupID lemosFamilyID = GroupID.createGroupID(lemosFamilyDenomination);
        createGroupService.createAndSaveGroup(lemosFamilyDenomination, lemosFamilyDescription, pedroPersonID);

        createGroupService.addAdminToGroup(lemosFamilyID, mariaPersonID);
        createGroupService.addMemberToGroup(lemosFamilyID, bernardoPersonID);
        createGroupService.addMemberToGroup(lemosFamilyID, miguelPersonID);

        createGroupAccounts(lemosFamilyID, createGroupService);
        createGroupCategories(lemosFamilyID, createGroupService);
        createGroupTransactions(lemosFamilyID, createGroupService);


        // [ii] Skate Family

        String skateFamilyDenomination = "Skate Friends";
        String skateFamilyDescription = "All members from skate friends";

        GroupID skateFamilyID = GroupID.createGroupID(skateFamilyDenomination);
        createGroupService.createAndSaveGroup(skateFamilyDenomination, skateFamilyDescription, miguelPersonID);

        createGroupService.addMemberToGroup(skateFamilyID, bernardoPersonID);
        createGroupService.addMemberToGroup(skateFamilyID, francisPersonID);

        createGroupAccounts(skateFamilyID, createGroupService);
        createGroupCategories(skateFamilyID, createGroupService);
        createGroupTransactions(skateFamilyID, createGroupService);


        // Accounts Lemos Family

        // Company

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        createGroupService.addAccountToGroup(lemosFamilyID, companyDenomination, companyDescription);
        AccountID companyAccountID = AccountID.createAccountID(companyDenomination, lemosFamilyID);

        // Bank Account

        String bankAccountDenomination = "Bank Account";
        String bankAccountDescription = "Personal bank account";

        createGroupService.addAccountToGroup(lemosFamilyID, bankAccountDenomination, bankAccountDescription);
        AccountID bankAccountID = AccountID.createAccountID(bankAccountDenomination, lemosFamilyID);

        // Categories Lemos Family
        // Salary

        String salaryDenomination = "Salary";

        createGroupService.addCategoryToGroup(lemosFamilyID, salaryDenomination);
        CategoryID salaryCategoryID = CategoryID.createCategoryID(salaryDenomination, lemosFamilyID);

        // Transactions Lemos Family

        // Salary January

        String credit = "credit";
        String salaryJanuaryDescription = "January salary";
        double salaryJanuaryAmount = 1500;
        LocalDate salaryJanuaryDate = LocalDate.of(2020, 01, 21);
        createGroupService.addGroupTransaction(lemosFamilyID, salaryCategoryID, credit, salaryJanuaryDescription,
                salaryJanuaryAmount, salaryJanuaryDate, companyAccountID, bankAccountID);
    }


    // Person Accounts [3]


    private static void createPersonAccounts(PersonID personID, CreatePersonService createPersonService) {

        // [i] Company

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        //  Create CreatePersonAccountDTO
        CreatePersonAccountDTO createPersonCompanyAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), companyDescription, companyDenomination);

        createPersonService.addAccountToPerson(createPersonCompanyAccountDTO);


        // [ii] Bank Account

        String bankAccountDenomination = "Bank Account";
        String bankAccountDescription = "Personal bank account";

        // Create CreatePersonAccountDTO
        CreatePersonAccountDTO createPersonBankAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), bankAccountDescription, bankAccountDenomination);

        createPersonService.addAccountToPerson(createPersonBankAccountDTO);

        // [iii] Wallet

        String walletDenomination = "Wallet";
        String walletDescription = "My wallet";

        //        Create CreatePersonAccountDTO
        CreatePersonAccountDTO createPersonWalletAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), walletDescription, walletDenomination);

        createPersonService.addAccountToPerson(createPersonWalletAccountDTO);
    }


    // Person Categories [3]

    private static void createPersonCategories(PersonID personID, CreatePersonService createPersonService) {

        // [i] Salary
        String salaryDenomination = "Salary";

        CreatePersonCategoryDTO addSalaryCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), salaryDenomination);

        createPersonService.addCategoryToPerson(addSalaryCategoryDTO);


        // [ii] IRS
        String irsDenomination = "IRS";

        CreatePersonCategoryDTO addIRSCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), irsDenomination);

        createPersonService.addCategoryToPerson(addIRSCategoryDTO);

        // [iii] Food
        String foodDenomination = "Food";

        CreatePersonCategoryDTO addFoodCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes
                (personID.getEmail().getEmail(), foodDenomination);

        createPersonService.addCategoryToPerson(addFoodCategoryDTO);
    }


    // Person Transactions [3]


    private static void createPersonTransactions(PersonID personID, CreatePersonService createPersonService) {

        //  Get Email from Person ID
        String personID_email = personID.getEmail().getEmail();

        // AccountID
        AccountID companyAccountID = AccountID.createAccountID("Company", personID);
        AccountID bankAccountID = AccountID.createAccountID("Bank Account", personID);
        AccountID walletAccountID = AccountID.createAccountID("Wallet", personID);

        // Get Account from AccountID
        String companyAccountID_Account = companyAccountID.getDenomination().getDenomination();
        String bankAccountID_Account = bankAccountID.getDenomination().getDenomination();
        String walletAccountID_Account = walletAccountID.getDenomination().getDenomination();

        // CategoryID
        CategoryID salaryCategoryID = CategoryID.createCategoryID("Salary", personID);
        CategoryID irsCategoryID = CategoryID.createCategoryID("IRS", personID);
        CategoryID foodCategoryID = CategoryID.createCategoryID("Food", personID);

        // Get category from CategoryID
        String salaryCategoryID_Category = salaryCategoryID.getDenomination().getDenomination();
        String irsCategoryID_Category = irsCategoryID.getDenomination().getDenomination();
        String foodCategoryID_Category = foodCategoryID.getDenomination().getDenomination();

        // Types
        String credit = "credit";
        String debit = "debit";


        // [i] Salary January

        String salaryJanuaryDescription = "January salary";
        double salaryJanuaryAmount = 1500;
        String salaryJanuaryDate = "2020-01-21";

        // Create a Person Transaction DTO
        CreatePersonTransactionDTO createPersonJanuarySalaryTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personID_email,
                salaryCategoryID_Category, credit, salaryJanuaryDescription, salaryJanuaryAmount, companyAccountID_Account,
                bankAccountID_Account, salaryJanuaryDate);

        createPersonService.addPersonTransaction(createPersonJanuarySalaryTransactionDTO);

        createPersonService.addPersonTransaction(createPersonJanuarySalaryTransactionDTO);


        // [ii] IRS January

        String irsJanuaryDescription = "January IRS";
        double irsJanuaryAmount = 150;
        String irsJanuaryDate = "2020-01-27";

        // Create a Person Transaction DTO
        CreatePersonTransactionDTO createPersonIRSTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personID_email,
                irsCategoryID_Category, debit, irsJanuaryDescription, irsJanuaryAmount, bankAccountID_Account, bankAccountID_Account, irsJanuaryDate);

        createPersonService.addPersonTransaction(createPersonIRSTransactionDTO);


        // [iii] Food January

        String foodJanuaryDescription = "January food";
        double foodJanuaryAmount = 50;
        String foodJanuaryDate = "2020-01-29";

        // Create a Person Transaction DTO
        CreatePersonTransactionDTO createPersonFoodTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personID_email,
                foodCategoryID_Category, debit, foodJanuaryDescription, foodJanuaryAmount, walletAccountID_Account, companyAccountID_Account, foodJanuaryDate);

        createPersonService.addPersonTransaction(createPersonFoodTransactionDTO);
    }


    // Group Accounts [3]

    private static void createGroupAccounts(GroupID groupID, CreateGroupService createGroupService) {

        // [i] Company

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        createGroupService.addAccountToGroup(groupID, companyDenomination, companyDescription);

        // [ii] Bank Account

        String bankAccountDenomination = "Bank Account";
        String bankAccountDescription = "Personal bank account";

        createGroupService.addAccountToGroup(groupID, bankAccountDenomination, bankAccountDescription);

        // [iii] Wallet

        String walletDenomination = "Wallet";
        String walletDescription = "My wallet";

        createGroupService.addAccountToGroup(groupID, walletDenomination, walletDescription);
    }


    // Group Categories [3]

    private static void createGroupCategories(GroupID groupID, CreateGroupService createGroupService) {

        // [i] Salary
        String salaryDenomination = "Salary";
        createGroupService.addCategoryToGroup(groupID, salaryDenomination);

        // [ii] IRS
        String irsDenomination = "IRS";
        createGroupService.addCategoryToGroup(groupID, irsDenomination);

        // [iii] Food
        String foodDenomination = "Food";
        createGroupService.addCategoryToGroup(groupID, foodDenomination);
    }


    // Group Transactions [3]

    private static void createGroupTransactions(GroupID groupID, CreateGroupService createGroupService) {

        // AccountID
        AccountID companyAccountID = AccountID.createAccountID("Company", groupID);
        AccountID bankAccountID = AccountID.createAccountID("Bank Account", groupID);
        AccountID walletAccountID = AccountID.createAccountID("Wallet", groupID);

        // CategoryID
        CategoryID salaryCategoryID = CategoryID.createCategoryID("Salary", groupID);
        CategoryID irsCategoryID = CategoryID.createCategoryID("IRS", groupID);
        CategoryID foodCategoryID = CategoryID.createCategoryID("Food", groupID);

        // Types
        String credit = "credit";
        String debit = "debit";


        // [i] Salary January
        String salaryJanuaryDescription = "January salary";
        double salaryJanuaryAmount = 1500;
        LocalDate salaryJanuaryDate = LocalDate.of(2020, 01, 21);
        createGroupService.addGroupTransaction(groupID, salaryCategoryID, credit, salaryJanuaryDescription, salaryJanuaryAmount, salaryJanuaryDate, companyAccountID, bankAccountID);

        // [ii] IRS January

        String irsJanuaryDescription = "January IRS";
        double irsJanuaryAmount = 150;
        LocalDate irsJanuaryDate = LocalDate.of(2020, 01, 27);
        createGroupService.addGroupTransaction(groupID, irsCategoryID, debit, irsJanuaryDescription, irsJanuaryAmount, irsJanuaryDate, bankAccountID, bankAccountID);

        // [iii] Food January

        String foodJanuaryDescription = "January food";
        double foodJanuaryAmount = 50;
        LocalDate foodJanuaryDate = LocalDate.of(2020, 01, 29);
        createGroupService.addGroupTransaction(groupID, foodCategoryID, debit, foodJanuaryDescription, foodJanuaryAmount, foodJanuaryDate, walletAccountID, companyAccountID);
    }
}

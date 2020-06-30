package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.dtos.dtos.GroupDTO;

import java.util.ArrayList;
import java.util.List;

public class GroupDTOAssembler {

    /**
     * DTO assembler receive input only domain objects referred below | JPA (to)-> Domain
     *
     * @param denomination
     * @param description
     * @param dateOfCreation
     * @return
     */
    public static GroupDTO createDTOFromDomainObject(Denomination denomination, Description description, DateOfCreation dateOfCreation) {
        String groupDenomination = denomination.getDenomination();
        String groupDescription = description.getDescription();
        String groupDateOfCreation = dateOfCreation.getDateOfCreation().toString();

        GroupDTO groupDTO = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation);
        return groupDTO;
    }

    /**
     * DTO assembler receive input only domain objects referred below | JPA -> Domain
     *
     * @param denomination
     * @param personInCharge
     * @param description
     * @param dateOfCreation
     * @return
     */

    public static GroupDTO createDTOFromDomainObject(Denomination denomination, PersonID personInCharge, Description description,
                                                     DateOfCreation dateOfCreation) {


        String toDTODenomination = denomination.getDenomination();
        List<PersonID> toDTOPersonInChargeList = new ArrayList<PersonID>();
        toDTOPersonInChargeList.add(personInCharge);
        String toDTODescription = description.getDescription();
        String toDTODateOfCreation = dateOfCreation.getDateOfCreation().toString();


        GroupDTO groupDTO = new GroupDTO(toDTODenomination, toDTODescription, toDTODateOfCreation);
        return groupDTO;

    }

    /**
     * DTO assembler receive input only domain objects referred below | JPA -> Domain
     *
     * @param denomination
     * @param personInCharge
     * @param description
     * @param dateOfCreation
     * @return
     */

    /**
     * DTO assembler receive input only domain objects referred below | JPA (to)-> Domain
     *
     * @param denomination
     * @param description
     * @param dateOfCreation
     * @return
     */
    public static GroupDTO createDTOFromDomainObject(Denomination denomination, Description description, DateOfCreation dateOfCreation, LedgerID ledgerID) {
        String groupDenomination = denomination.getDenomination();
        String groupDescription = description.getDescription();
        String groupDateOfCreation = dateOfCreation.getDateOfCreation().toString();
        String groupLedgerID = ledgerID.getLedgerID();

        GroupDTO groupDTO = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation, groupLedgerID);
        return groupDTO;
    }

    /**
     * DTO assembler receive input only primitive types referred below | JPA -> Domain
     *
     * @param denomination
     * @param personInCharge
     * @param description
     * @param dateOfCreation
     * @return
     */

    public static GroupDTO createDTOFromDomainObjectPT(String denomination, String personInCharge, String description,
                                                       String dateOfCreation) {

        GroupDTO groupDTO = new GroupDTO(denomination, description, dateOfCreation);
        return groupDTO;

    }
}

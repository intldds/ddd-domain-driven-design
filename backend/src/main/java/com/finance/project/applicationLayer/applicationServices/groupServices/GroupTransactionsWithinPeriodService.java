package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.dtos.dtosAssemblers.GroupTransactionsWithinPeriodDTOoutAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOin;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOout;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Service
public class GroupTransactionsWithinPeriodService {

    @Autowired
    private final IGroupRepository groupRepository;
    @Autowired
    private final ILedgerRepository ledgerRepository;

    // Return messages

    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public static final String GROUP_DOES_NOT_EXIST = "Group does not exist in the system";
    /**
     * The constant PERSON_NOT_MEMBER.
     */
    public static final String PERSON_NOT_MEMBER = "Person is not member of the group";
    /**
     * The constant NO_TRANSACTIONS_TO_REPORT.
     */
    public static final String NO_TRANSACTIONS_TO_REPORT = "Within the given period, there are no transactions to report";


    public GroupTransactionsWithinPeriodService(IGroupRepository groupRepository, ILedgerRepository ledgerRepository) {
        this.groupRepository = groupRepository;
        this.ledgerRepository = ledgerRepository;
    }


    public GroupTransactionsWithinPeriodDTOout getGroupTransactionsWithinPeriod(GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin)
            throws NotFoundArgumentsBusinessException {

        Group group;
        List<Transaction> groupTransactions;

        GroupID groupID = GroupID.createGroupID(groupTransactionsWithinPeriodDTOin.getGroupDenomination());
        Optional<Group> optGroup = groupRepository.findById(groupID);

        // if group does not exist, transactions' report will not be created
        if (!optGroup.isPresent()) {
            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = optGroup.get();

            // If person is not member of the group, transactions' report cannot be created
            // Member of the group means that person may be person in charge, or merely member

            PersonID personID = PersonID.createPersonID(groupTransactionsWithinPeriodDTOin.getPersonEmail());
            boolean isPersonGroupMember = group.isPersonAlreadyMember(personID);

            if (!isPersonGroupMember) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_MEMBER);

            } else {

                //If group exist, it will have a ledger
                LedgerID groupLedgerID = group.getLedgerID();
                Optional<Ledger> optLedger = ledgerRepository.findById(groupLedgerID);
                groupTransactions = optLedger
                        .filter(groupLedger -> !groupLedger.getRecordsBetweenTwoDates(groupTransactionsWithinPeriodDTOin.getStartDate(), groupTransactionsWithinPeriodDTOin.getEndDate()).isEmpty())
                        .map(groupLedger -> groupLedger.getRecordsBetweenTwoDates(groupTransactionsWithinPeriodDTOin.getStartDate(), groupTransactionsWithinPeriodDTOin.getEndDate()))
                        .orElseThrow(() -> new NotFoundArgumentsBusinessException(NO_TRANSACTIONS_TO_REPORT));
            }
        }
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout =
                GroupTransactionsWithinPeriodDTOoutAssembler.getGroupTransactionsWithinPeriodDTOout((ArrayList<Transaction>) groupTransactions);

        return  groupTransactionsWithinPeriodDTOout;
    }
}

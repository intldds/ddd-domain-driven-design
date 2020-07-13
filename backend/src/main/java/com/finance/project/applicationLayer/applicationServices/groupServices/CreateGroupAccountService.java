package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.CreateGroupAccountDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.Optional;


@Service
public class CreateGroupAccountService {

    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private IAccountRepository accountRepository;

    public CreateGroupAccountService(IGroupRepository groupRepository, IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.groupRepository = groupRepository;
    }

    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Account created and added";
    /**
     * The constant ACCOUNT_ALREADY_EXIST.
     */
    public final static String ACCOUNT_ALREADY_EXIST = "Account already exists";
    /**
     * The constant PERSON_NOT_IN_CHARGE.
     */
    public final static String PERSON_NOT_IN_CHARGE = "Person is not in charge";
    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public final static String GROUP_DOES_NOT_EXIST = "Group does not exist";


    public GroupDTO createAccountAsPeopleInCharge(CreateGroupAccountDTO createGroupAccountDTO) {

        Group group;

        GroupID groupID = GroupID.createGroupID(createGroupAccountDTO.getGroupDenomination());
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {
            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = optGroup.get();

            // If Person is PeopleInCharge of a group, he/she already exists in personRepository
            PersonID personID = PersonID.createPersonID(createGroupAccountDTO.getPersonEmail());
            boolean isPeopleInCharge = group.isPersonPeopleInCharge(personID);

            AccountID accountID = AccountID.createAccountID(createGroupAccountDTO.getAccountDenomination(), groupID);
            boolean accountExistsInRepo = accountRepository.existsById(accountID);

            if (!isPeopleInCharge) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_IN_CHARGE);

            } else if (accountExistsInRepo) {
                throw new InvalidArgumentsBusinessException(ACCOUNT_ALREADY_EXIST);

            } else {
                group.addAccount(AccountID.createAccountID(createGroupAccountDTO.getAccountDenomination(), groupID));
                groupRepository.addAndSaveAccount(group, createGroupAccountDTO.getAccountDescription());
            }
        }
        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                group.getGroupID().getDenomination(),
                group.getDescription(),
                group.getDateOfCreation());

        return groupDTO;
    }
}


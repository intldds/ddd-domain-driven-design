package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;
import com.finance.project.dtos.dtos.PersonDTO;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;

import java.util.Optional;


@Service
public class CreatePersonAccountService {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IAccountRepository accountRepository;

    // Return messages

    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Account created and added";
    /**
     * The constant ACCOUNT_ALREADY_EXIST.
     */
    public final static String ACCOUNT_ALREADY_EXIST = "Account already exists";
    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public final static String PERSON_DOES_NOT_EXIST = "Person does not exist in the system";


    public CreatePersonAccountService(IPersonRepository personRepository, IAccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }


    public PersonDTO createAccount(CreatePersonAccountDTO createPersonAccountDTO) {

        Person person;

        PersonID personID = PersonID.createPersonID(createPersonAccountDTO.getEmail());
        Optional<Person> opPerson = personRepository.findById(personID);

        // If person does not exist, the account will not be created

        if (!opPerson.isPresent()) {
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {
            person = opPerson.get();

            AccountID accountID = AccountID.createAccountID(createPersonAccountDTO.getDenomination(), personID);
            boolean accountExists = accountRepository.existsById(accountID);

            if (accountExists) {
                throw new InvalidArgumentsBusinessException(ACCOUNT_ALREADY_EXIST);

            } else {
                person.addAccount(accountID);
                personRepository.addAndSaveAccount(person, createPersonAccountDTO.getDescription());
            }
        }
        PersonDTO personDTO = PersonDTOAssembler.createDTOFromDomainObject(
                person.getPersonID().getEmail(),
                person.getName(),
                person.getBirthdate(),
                person.getBirthplace(),
                person.getMother(),
                person.getFather());

        return personDTO;
    }
}
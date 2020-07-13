package com.finance.project.applicationLayer.applicationServices.otherServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.BooleanDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.BooleanDTO;
import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.Optional;


@Service
public class CheckSiblingsService {

    @Autowired
    private IPersonRepository personRepository;

    private String msg;

    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Siblings";
    /**
     * The constant FAIL.
     */
    public final static String FAIL = "Not Siblings";
    /**
     * The constant NOT_EXIST_1.
     */
    public final static String NOT_EXIST_1 = "First person does not exist";
    /**
     * The constant NOT_EXIST_2.
     */
    public final static String NOT_EXIST_2 = "Second person does not exist";


    public CheckSiblingsService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public BooleanDTO checkIfSiblings(CheckIfSiblingsDTO checkIfSiblingsDTO) throws RuntimeException {

        boolean isSibling;

        PersonID personA_ID = PersonID.createPersonID(checkIfSiblingsDTO.getEmail());
        PersonID personB_ID = PersonID.createPersonID(checkIfSiblingsDTO.getSiblingEmail());

        Optional<Person> opPersonA_ = personRepository.findById(personA_ID);
        Optional<Person> opPersonB_ = personRepository.findById(personB_ID);

        //      Check if each person exists in the DB
        if (!opPersonA_.isPresent()) {
            isSibling = false;
            throw new NotFoundArgumentsBusinessException(NOT_EXIST_1);
        }
        if (!opPersonB_.isPresent()) {
            isSibling = false;
            throw new NotFoundArgumentsBusinessException(NOT_EXIST_2);
        }

        //        If the person exists assign to a new variable, in this case with the type Person
        Person personA_ = opPersonA_.get();
        Person personB_ = opPersonB_.get();

        //      Proceed with the analysis if two persons are siblings, or not
        if (personA_.verifySiblingsOrHalfSiblings(personB_)) {
            isSibling = true;
            msg = SUCCESS;
        } else {
            isSibling = false;
            throw new InvalidArgumentsBusinessException(FAIL);
        }

        BooleanDTO booleanDTO = BooleanDTOAssembler.createDTOFromPrimitiveTypes(isSibling, msg);
        return booleanDTO;
    }
}

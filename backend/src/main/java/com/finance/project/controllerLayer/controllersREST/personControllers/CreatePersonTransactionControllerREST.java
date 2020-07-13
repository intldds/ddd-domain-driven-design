package com.finance.project.controllerLayer.controllersREST.personControllers;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonTransactionService;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.CreatePersonTransactionDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.DeletePersonTransactionDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finance.project.dtos.dtosAssemblers.UpdatePersonTransactionDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
public class CreatePersonTransactionControllerREST {

    @Autowired
    private CreatePersonTransactionService service;

    // Create transaction
    @PostMapping("/persons/{personEmail}/ledgers/records")
    public ResponseEntity<Object> createPersonTransaction(@RequestBody NewPersonTransactionInfoDTO info, @PathVariable final String personEmail) {

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personEmail, info.getDenominationCategory(), info.getType(), info.getDescription(), info.getAmount(), info.getDenominationAccountDeb(), info.getDenominationAccountCred(), info.getDate());

        PersonDTO result = service.createTransaction(createPersonTransactionDTO);

        Link link_to_siblings = linkTo(methodOn(CreatePersonControllerREST.class).getPersonSiblings(personEmail)).withRel("siblings");
        Link link_to_personLedger = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("records");
        Link link_to_personAccounts = linkTo(methodOn(CreatePersonControllerREST.class).getPersonAccounts(personEmail)).withRel("accounts");
        Link link_to_personCategories = linkTo(methodOn(CreatePersonControllerREST.class).getPersonCategories(personEmail)).withRel("categories");

        result.add(link_to_siblings);
        result.add(link_to_personLedger);
        result.add(link_to_personAccounts);
        result.add(link_to_personCategories);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Update transaction
    @PutMapping("/persons/{personEmail}/ledgers/records/{transactionNumber}")
    public ResponseEntity<Object> updatePersonTransaction(@RequestBody NewPersonTransactionInfoDTO info, @PathVariable final String personEmail, @PathVariable final int transactionNumber) {

        UpdatePersonTransactionDTO updatePersonTransactionDTO = UpdatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, personEmail, info.getDenominationCategory(), info.getType(), info.getDescription(), info.getAmount(), info.getDenominationAccountDeb(), info.getDenominationAccountCred());

        PersonDTO result = service.updateTransaction(updatePersonTransactionDTO);

        Link link_to_siblings = linkTo(methodOn(CreatePersonControllerREST.class).getPersonSiblings(personEmail)).withRel("siblings");
        Link link_to_personLedger = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("records");
        Link link_to_personAccounts = linkTo(methodOn(CreatePersonControllerREST.class).getPersonAccounts(personEmail)).withRel("accounts");
        Link link_to_personCategories = linkTo(methodOn(CreatePersonControllerREST.class).getPersonCategories(personEmail)).withRel("categories");

        result.add(link_to_siblings);
        result.add(link_to_personLedger);
        result.add(link_to_personAccounts);
        result.add(link_to_personCategories);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // Delete transaction
    @DeleteMapping("/persons/{personEmail}/ledgers/records/{transactionNumber}")
    public ResponseEntity<Object> deletePersonTransaction(@PathVariable final String personEmail, @PathVariable final int transactionNumber) {

        DeletePersonTransactionDTO deletePersonTransactionDTO = DeletePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, personEmail);

        PersonDTO result = service.deleteTransaction(deletePersonTransactionDTO);

        Link link_to_siblings = linkTo(methodOn(CreatePersonControllerREST.class).getPersonSiblings(personEmail)).withRel("siblings");
        Link link_to_personLedger = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("records");
        Link link_to_personAccounts = linkTo(methodOn(CreatePersonControllerREST.class).getPersonAccounts(personEmail)).withRel("accounts");
        Link link_to_personCategories = linkTo(methodOn(CreatePersonControllerREST.class).getPersonCategories(personEmail)).withRel("categories");

        result.add(link_to_siblings);
        result.add(link_to_personLedger);
        result.add(link_to_personAccounts);
        result.add(link_to_personCategories);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
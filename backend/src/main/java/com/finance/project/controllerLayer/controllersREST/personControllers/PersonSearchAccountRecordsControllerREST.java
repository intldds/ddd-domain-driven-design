package com.finance.project.controllerLayer.controllersREST.personControllers;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.applicationLayer.applicationServices.personServices.PersonSearchAccountRecordsService;
import com.finance.project.dtos.dtosAssemblers.PersonSearchAccountRecordsInDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonSearchAccountRecordsControllerREST {

    @Autowired
    private PersonSearchAccountRecordsService searchPersonAccountRecordsService;
    @Autowired
    private CreatePersonService personRecordsService;

    // Search account records within a period of dates
    @GetMapping("/persons/{personID}/ledgers/records")
    public ResponseEntity<Object> searchPersonRecords(@RequestParam(value = "accountName", defaultValue = "") String accountDenomination,
                                                      @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                                      @RequestParam(value = "endDate", defaultValue = "") String endDate,
                                                      @PathVariable(value = "personID") String personEmail) {

        // DTO for passing info to service that searches account records within period
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        // Info to return whether form is empty or not
        boolean searchFormEmpty = (accountDenomination.isEmpty() && startDate.isEmpty() && endDate.isEmpty());

        if (!searchFormEmpty) {

            PersonSearchAccountRecordsOutDTO searchResult = searchPersonAccountRecordsService.getPersonAccountTransactionsWithinPeriod(personSearchAccountRecordsInDTO);

            for (int i = 0; i < searchResult.getTransactions().size(); i++) {
                Link link_to_editTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).updatePersonTransaction(null, personEmail, i + 1)).withRel("editTransaction");
                Link link_to_deleteTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).deletePersonTransaction(personEmail, i + 1)).withRel("deleteTransaction");
                searchResult.getTransactions().get(i).add(link_to_editTransaction);
                searchResult.getTransactions().get(i).add(link_to_deleteTransaction);
            }

            Link link_to_addTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).createPersonTransaction(null, personEmail)).withRel("addTransaction");
            Link link_to_searchTransaction = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("searchTransaction");
            searchResult.add(link_to_addTransaction);
            searchResult.add(link_to_searchTransaction);

            return new ResponseEntity<>(searchResult, HttpStatus.OK);

        } else {

            PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);
            TransactionsDTO defaultResult = personRecordsService.getPersonLedger(personEmailDTO);

            for (int i = 0; i < defaultResult.getTransactions().size(); i++) {
                Link link_to_editTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).updatePersonTransaction(null, personEmail, i + 1)).withRel("editTransaction");
                Link link_to_deleteTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).deletePersonTransaction(personEmail, i + 1)).withRel("deleteTransaction");
                defaultResult.getTransactions().get(i).add(link_to_editTransaction);
                defaultResult.getTransactions().get(i).add(link_to_deleteTransaction);
            }

            Link link_to_addTransaction = linkTo(methodOn(CreatePersonTransactionControllerREST.class).createPersonTransaction(null, personEmail)).withRel("addTransaction");
            defaultResult.add(link_to_addTransaction);

            if (!defaultResult.getTransactions().isEmpty()) {
                Link link_to_searchTransaction = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("searchTransaction");
                defaultResult.add(link_to_searchTransaction);
            }

            return new ResponseEntity<>(defaultResult, HttpStatus.OK);
        }
    }

}

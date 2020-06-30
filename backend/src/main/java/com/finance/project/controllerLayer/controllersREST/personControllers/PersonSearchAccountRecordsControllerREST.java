package com.finance.project.controllerLayer.controllersREST.personControllers;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.applicationLayer.applicationServices.personServices.PersonSearchAccountRecordsService;
import com.finance.project.dtos.dtosAssemblers.PersonSearchAccountRecordsInDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.PersonEmailDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.SearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtos.TransactionsDTO;

@RestController
public class PersonSearchAccountRecordsControllerREST {

    @Autowired
    private PersonSearchAccountRecordsService searchPersonAccountRecordsService;
    @Autowired
    private CreatePersonService personRecordsService;

    //Search account records within a period of dates
    @GetMapping("/persons/{personID}/ledgers/records")
    public ResponseEntity<Object> searchPersonRecords(@RequestParam(value = "accountName", defaultValue = "") String accountDenomination,
                                                      @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                                      @RequestParam(value = "endDate", defaultValue = "") String endDate,
                                                      @PathVariable(value = "personID") String personEmail) {

        //DTO for passing info to service that searches account records within period
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Info to return whether form is empty or not
        boolean searchFormEmpty = (accountDenomination.isEmpty() && startDate.isEmpty() && endDate.isEmpty());

        if (!searchFormEmpty) {

            SearchAccountRecordsOutDTO searchResult = searchPersonAccountRecordsService.getPersonAccountTransactionsWithinPeriod(personSearchAccountRecordsInDTO);

            return new ResponseEntity<>(searchResult, HttpStatus.OK);

        } else {

            PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);
            TransactionsDTO defaultResult = personRecordsService.getPersonLedger(personEmailDTO);

            return new ResponseEntity<>(defaultResult, HttpStatus.OK);
        }
    }

}

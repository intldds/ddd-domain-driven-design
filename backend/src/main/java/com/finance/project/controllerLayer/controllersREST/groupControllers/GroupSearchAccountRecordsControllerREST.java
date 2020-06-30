package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import com.finance.project.applicationLayer.applicationServices.groupServices.GroupSearchAccountRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.SearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtos.TransactionsDTO;
import com.finance.project.dtos.dtosAssemblers.GroupSearchAccountRecordsInDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class GroupSearchAccountRecordsControllerREST {

    @Autowired
    private GroupSearchAccountRecordsService searchGroupAccountRecordsService;
    @Autowired
    private CreateGroupService groupService;

    /**
     * Search account records of a group, within a period of dates.
     *
     * @param accountDenomination the account denomination
     * @param startDate           the start date
     * @param endDate             the end date
     * @param personEmail         the person email
     * @param groupDenomination   the group denomination
     * @return the response entity
     */
    @GetMapping("/persons/{personEmail}/groups/{groupDenomination}/ledgers/records")
    public ResponseEntity<Object> searchGroupRecords(@RequestParam(value = "accountName", defaultValue = "") String accountDenomination,
                                                     @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                                     @RequestParam(value = "endDate", defaultValue = "") String endDate,
                                                     @PathVariable(value = "personEmail") String personEmail,
                                                     @PathVariable(value = "groupDenomination") String groupDenomination){

        //DTO for passing info to service that searches account records within period
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Info to return considering if form is empty or not
        boolean searchFormEmpty = (accountDenomination.isEmpty() && startDate.isEmpty() && endDate.isEmpty());

        if (!searchFormEmpty) {
            SearchAccountRecordsOutDTO searchResult = searchGroupAccountRecordsService.getGroupAccountTransactionsWithinPeriod(groupSearchAccountRecordsInDTO);
            return new ResponseEntity<>(searchResult, HttpStatus.OK);

        } else {
            TransactionsDTO searchDefaultResult = groupService.getGroupLedger(groupDenomination);
            return new ResponseEntity<>(searchDefaultResult, HttpStatus.OK);
        }
    }

}

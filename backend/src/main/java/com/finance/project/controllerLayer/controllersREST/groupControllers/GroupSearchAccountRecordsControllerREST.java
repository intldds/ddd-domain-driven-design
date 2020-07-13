package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import com.finance.project.applicationLayer.applicationServices.groupServices.GroupSearchAccountRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtos.TransactionsDTO;
import com.finance.project.dtos.dtosAssemblers.GroupSearchAccountRecordsInDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class GroupSearchAccountRecordsControllerREST {

    @Autowired
    private GroupSearchAccountRecordsService searchGroupAccountRecordsService;
    @Autowired
    private CreateGroupService groupService;


    @GetMapping("/persons/{personEmail}/groups/{groupDenomination}/ledgers/records")
    public ResponseEntity<Object> searchGroupRecords(@RequestParam(value = "accountName", defaultValue = "") String accountDenomination,
                                                     @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                                     @RequestParam(value = "endDate", defaultValue = "") String endDate,
                                                     @PathVariable(value = "personEmail") String personEmail,
                                                     @PathVariable(value = "groupDenomination") String groupDenomination) {

        // DTO for passing info to service that searches account records within period
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        // Info to return considering if form is empty or not
        boolean searchFormEmpty = (accountDenomination.isEmpty() && startDate.isEmpty() && endDate.isEmpty());

        if (!searchFormEmpty) {
            PersonSearchAccountRecordsOutDTO searchResult = searchGroupAccountRecordsService.getGroupAccountTransactionsWithinPeriod(groupSearchAccountRecordsInDTO);

            for (int i = 0; i < searchResult.getTransactions().size(); i++) {
                Link link_to_editTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).updateGroupTransaction(null, personEmail, groupDenomination, i + 1)).withRel("editTransaction");
                Link link_to_deleteTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).deleteGroupTransaction(personEmail, groupDenomination, i + 1)).withRel("deleteTransaction");
                searchResult.getTransactions().get(i).add(link_to_editTransaction);
                searchResult.getTransactions().get(i).add(link_to_deleteTransaction);
            }

            Link link_to_addTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).createGroupTransaction(null, personEmail, groupDenomination)).withRel("addTransaction");
            Link link_to_searchTransaction = linkTo(methodOn(GroupSearchAccountRecordsControllerREST.class).searchGroupRecords("", "", "", personEmail, groupDenomination)).withRel("searchTransaction");
            searchResult.add(link_to_addTransaction);
            searchResult.add(link_to_searchTransaction);

            return new ResponseEntity<>(searchResult, HttpStatus.OK);

        } else {
            TransactionsDTO searchDefaultResult = groupService.getGroupLedger(groupDenomination);

            for (int i = 0; i < searchDefaultResult.getTransactions().size(); i++) {
                Link link_to_editTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).updateGroupTransaction(null, personEmail, groupDenomination, i + 1)).withRel("editTransaction");
                Link link_to_deleteTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).deleteGroupTransaction(personEmail, groupDenomination, i + 1)).withRel("deleteTransaction");
                searchDefaultResult.getTransactions().get(i).add(link_to_editTransaction);
                searchDefaultResult.getTransactions().get(i).add(link_to_deleteTransaction);
            }

            Link link_to_addTransaction = linkTo(methodOn(CreateGroupTransactionControllerREST.class).createGroupTransaction(null, personEmail, groupDenomination)).withRel("addTransaction");
            searchDefaultResult.add(link_to_addTransaction);

            if (!searchDefaultResult.getTransactions().isEmpty()) {
                Link link_to_searchTransaction = linkTo(methodOn(GroupSearchAccountRecordsControllerREST.class).searchGroupRecords("", "", "", personEmail, groupDenomination)).withRel("searchTransaction");
                searchDefaultResult.add(link_to_searchTransaction);
            }

            return new ResponseEntity<>(searchDefaultResult, HttpStatus.OK);
        }
    }

}


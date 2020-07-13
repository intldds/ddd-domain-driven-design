package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupTransactionService;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.CreateGroupTransactionDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finance.project.dtos.dtosAssemblers.DeleteGroupTransactionDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.UpdateGroupTransactionDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CreateGroupTransactionControllerREST {

    @Autowired
    private CreateGroupTransactionService service;


    @PostMapping("/persons/{personEmail}/groups/{groupDenomination}/ledgers/records")
    public ResponseEntity<Object> createGroupTransaction(@RequestBody NewGroupTransactionInfoDTO info,
                                                         @PathVariable final String personEmail,
                                                         @PathVariable final String groupDenomination) {

        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, info.getDenominationCategory(), info.getDenominationAccountDeb(), info.getDenominationAccountCred(), info.getAmount(), info.getType(), info.getDescription(), info.getDate());

        GroupDTO result = service.createGroupTransaction(createGroupTransactionDTO);

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(groupDenomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(groupDenomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(groupDenomination)).withRel("records");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts(personEmail, groupDenomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories(personEmail, groupDenomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/persons/{personEmail}/groups/{groupDenomination}/ledgers/records/{transactionNumber}")
    public ResponseEntity<Object> updateGroupTransaction(@RequestBody NewGroupTransactionInfoDTO info,
                                                         @PathVariable final String personEmail,
                                                         @PathVariable final String groupDenomination,
                                                         @PathVariable final int transactionNumber) {

        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, groupDenomination, personEmail, info.getDenominationCategory(), info.getDenominationAccountDeb(), info.getDenominationAccountCred(), info.getAmount(), info.getType(), info.getDescription());

        GroupDTO result = service.updateGroupTransaction(updateGroupTransactionDTO);

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(groupDenomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(groupDenomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(groupDenomination)).withRel("records");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts(personEmail, groupDenomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories(personEmail, groupDenomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/persons/{personEmail}/groups/{groupDenomination}/ledgers/records/{transactionNumber}")
    public ResponseEntity<Object> deleteGroupTransaction(@PathVariable final String personEmail,
                                                         @PathVariable final String groupDenomination,
                                                         @PathVariable final int transactionNumber) {

        DeleteGroupTransactionDTO deleteGroupTransactionDTO = DeleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, groupDenomination, personEmail);

        GroupDTO result = service.deleteGroupTransaction(deleteGroupTransactionDTO);

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(groupDenomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(groupDenomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(groupDenomination)).withRel("records");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts(personEmail, groupDenomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories(personEmail, groupDenomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupAccountService;
import com.finance.project.dtos.dtosAssemblers.CreateGroupAccountDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.CreateGroupAccountDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.NewGroupAccountInfoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CreateGroupAccountControllerREST {

    @Autowired
    private CreateGroupAccountService createGroupAccountService;

    @PostMapping("/persons/{personEmail}/groups/{groupDenomination}/accounts")
    public ResponseEntity<Object> createGroupAccount(@RequestBody NewGroupAccountInfoDTO info,
                                                     @PathVariable final String personEmail,
                                                     @PathVariable final String groupDenomination) {

        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(
                personEmail, groupDenomination, info.getAccountDescription(), info.getAccountDenomination());

        GroupDTO result = createGroupAccountService.createAccountAsPeopleInCharge(createGroupAccountDTO);

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(groupDenomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(groupDenomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(groupDenomination)).withRel("ledger");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts(personEmail, groupDenomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories(personEmail, groupDenomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}

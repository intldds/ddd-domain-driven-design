package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import com.finance.project.dtos.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finance.project.dtos.dtosAssemblers.CreateGroupDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CreateGroupControllerREST {

    @Autowired
    private CreateGroupService service002_1;

    /**
     * PostMapping of create group and become person in charge
     *
     * @param info The info that compose the dto to create the group
     * @return The response entity of object Group creation
     */

    @PostMapping("/groups")
    public ResponseEntity<Object> createGroupAsPersonInCharge(@RequestBody NewCreateGroupInfoDTO info) {

        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(info.getEmail(), info.getDenomination(), info.getDescription());

        GroupDTO result = service002_1.createGroupAsPersonInCharge(createGroupDTO);

        Link self_link = linkTo(methodOn(CreateGroupControllerREST.class).getGroupByDenomination(info.getDenomination())).withSelfRel();

        result.add(self_link);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/groups/{groupDenomination}")
    public ResponseEntity<Object> getGroupByDenomination(@PathVariable final String groupDenomination) {

        GroupDTO result = service002_1.getGroupByDenomination(groupDenomination);

        // !!!!!!    Alterar URL para partir de person e corrigir link_to_accounts   !!!!!!!

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(groupDenomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(groupDenomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(groupDenomination)).withRel("records");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts("", groupDenomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories("",groupDenomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupDenomination}/admins")
    public ResponseEntity<Object> getGroupAdmins(@PathVariable final String groupDenomination) {

        GroupAdminsDTO result = service002_1.getGroupAdmins(groupDenomination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupDenomination}/members")
    public ResponseEntity<Object> getGroupMembers(@PathVariable final String groupDenomination) {

        GroupMembersDTO result = service002_1.getGroupMembers(groupDenomination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupDenomination}/allMembers")
    public ResponseEntity<Object> getGroupAllMembers(@PathVariable final String groupDenomination) {

        GroupAllMembersDTO result = service002_1.getGroupAllMembers(groupDenomination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupDenomination}/ledgers/records")
    public ResponseEntity<Object> getGroupLedger(@PathVariable final String groupDenomination) {

        TransactionsDTO result = service002_1.getGroupLedger(groupDenomination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/persons/{personEmail}/groups/{groupDenomination}/accounts")
    public ResponseEntity<Object> getGroupAccounts(@PathVariable final String personEmail,
                                                   @PathVariable final String groupDenomination) {

        boolean isAdmin = false;

        AccountsDTO result = service002_1.getGroupAccounts(groupDenomination);

        if (!personEmail.isEmpty()) {
            isAdmin = service002_1.isAdmin(groupDenomination, personEmail);
        }

        if (isAdmin) {

            Link link_to_addAccount = linkTo(methodOn(CreateGroupAccountControllerREST.class).createGroupAccount(null, personEmail, groupDenomination)).withRel("addAccount");
            result.add(link_to_addAccount);

        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/persons/{personEmail}/groups/{groupDenomination}/categories")
    public ResponseEntity<Object> getGroupCategories(@PathVariable final String personEmail,
                                                     @PathVariable final String groupDenomination) {

        boolean isAdmin = false;

        CategoriesDTO result = service002_1.getGroupCategories(groupDenomination);

        isAdmin = service002_1.isAdmin(groupDenomination, personEmail);

        if (isAdmin) {
            Link link_to_addCategory = linkTo(methodOn(CreateGroupCategoryControllerREST.class).createGroupCategory(null, personEmail, groupDenomination)).withRel("addCategory");
            result.add(link_to_addCategory);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

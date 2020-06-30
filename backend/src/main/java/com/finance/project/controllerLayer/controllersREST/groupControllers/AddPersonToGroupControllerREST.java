package com.finance.project.controllerLayer.controllersREST.groupControllers;

import com.finance.project.applicationLayer.applicationServices.groupServices.AddPersonToGroupService;
import com.finance.project.dtos.dtosAssemblers.AddPersonToGroupDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.AddPersonToGroupDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.NewAddPersonToGroupInfoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class AddPersonToGroupControllerREST {

    @Autowired
    private AddPersonToGroupService serviceUS003;


    /**
     * Add person to group 2 response entity.
     *
     * @param info the info
     * @return the response entity
     */



    @PostMapping("/groups/{denomination}/members")
    public ResponseEntity<Object> addPersonToGroupP(@RequestBody NewAddPersonToGroupInfoDTO info,
                                                    @PathVariable final String denomination
    ) {

        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(info.getEmail(),denomination);

//        The new TDO to be used is addPersonToGroupDTOInput
        GroupDTO result = serviceUS003.addPersonToGroup(addPersonToGroupDTO);

        Link link_to_admins = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAdmins(denomination)).withRel("admins");
        Link link_to_members = linkTo(methodOn(CreateGroupControllerREST.class).getGroupMembers(denomination)).withRel("members");
        Link link_to_ledger = linkTo(methodOn(CreateGroupControllerREST.class).getGroupLedger(denomination)).withRel("ledger");
        Link link_to_accounts = linkTo(methodOn(CreateGroupControllerREST.class).getGroupAccounts(info.getEmail(), denomination)).withRel("accounts");
        Link link_to_categories = linkTo(methodOn(CreateGroupControllerREST.class).getGroupCategories(info.getEmail(),denomination)).withRel("categories");

        result.add(link_to_admins);
        result.add(link_to_members);
        result.add(link_to_ledger);
        result.add(link_to_accounts);
        result.add(link_to_categories);


        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}

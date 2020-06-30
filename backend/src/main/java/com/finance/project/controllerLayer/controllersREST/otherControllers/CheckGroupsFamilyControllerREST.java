package com.finance.project.controllerLayer.controllersREST.otherControllers;

import com.finance.project.applicationLayer.applicationServices.otherServices.CheckGroupsFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.GroupMembersDTO;
import com.finance.project.dtos.dtos.GroupsThatAreFamilyDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CheckGroupsFamilyControllerREST {

    @Autowired
    private CheckGroupsFamilyService serviceUS004;

    /**
     * Groups that are family.
     *
     * @return the response entity
     */

    @GetMapping("/groups/areFamily")
    public ResponseEntity<Object> getGroupsThatAreFamily() {

        GroupsThatAreFamilyDTO result = serviceUS004.groupsThatAreFamily();

        for(int i = 0; i < result.getGroupThatAreFamily().size(); i++) {
            String denomination = result.getGroupThatAreFamily().get(i).getDenomination();
            Link self_link = linkTo(methodOn(CheckGroupsFamilyControllerREST.class).getGroupByDenomination(denomination)).withSelfRel();
            result.add(self_link);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/groups/areFamily/{groupDenomination}")
    public ResponseEntity<Object> getGroupByDenomination(@PathVariable final String groupDenomination) {

        GroupDTO result = serviceUS004.getGroupByDenomination(groupDenomination);

        Link link_to_allMembers = linkTo(methodOn(CheckGroupsFamilyControllerREST.class).getGroupAllMembers(groupDenomination)).withRel("allMembers");

        result.add(link_to_allMembers);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/groups/areFamily/{groupDenomination}/allMembers")
    public ResponseEntity<Object> getGroupAllMembers(@PathVariable final String groupDenomination) {

        GroupMembersDTO result = serviceUS004.getGroupAllMembers(groupDenomination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
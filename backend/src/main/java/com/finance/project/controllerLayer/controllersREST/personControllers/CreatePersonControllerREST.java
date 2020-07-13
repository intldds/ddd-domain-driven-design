package com.finance.project.controllerLayer.controllersREST.personControllers;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.CreateGroupControllerREST;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.CreatePersonDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupListDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CreatePersonControllerREST {

    @Autowired
    private CreatePersonService service;

    // Information related to URL for persons
    @PostMapping("/persons")
    public ResponseEntity<Object> createPerson(@RequestBody NewCreatePersonInfoDTO info) {

        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(info.getEmail(), info.getName(), info.getBirthdate(), info.getBirthplace());

        PersonDTO result = service.createPerson(createPersonDTO);

        Link self_link = linkTo(methodOn(CreatePersonControllerREST.class).getPersonByEmail(info.getEmail())).withSelfRel();

        result.add(self_link);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @GetMapping("/persons/{personEmail}")
    public ResponseEntity<Object> getPersonByEmail(@PathVariable final String personEmail) {

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);

        PersonDTO result = service.getPersonByEmail(personEmailDTO);

        Link link_to_siblings = linkTo(methodOn(CreatePersonControllerREST.class).getPersonSiblings(personEmail)).withRel("siblings");
        Link link_to_personLedger = WebMvcLinkBuilder.linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("records");
        Link link_to_personAccounts = linkTo(methodOn(CreatePersonControllerREST.class).getPersonAccounts(personEmail)).withRel("accounts");
        Link link_to_personCategories = linkTo(methodOn(CreatePersonControllerREST.class).getPersonCategories(personEmail)).withRel("categories");

        result.add(link_to_siblings);
        result.add(link_to_personLedger);
        result.add(link_to_personAccounts);
        result.add(link_to_personCategories);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/persons/{personEmail}/accounts")
    public ResponseEntity<Object> getPersonAccounts(@PathVariable final String personEmail) {

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);

        AccountsDTO result = service.getPersonAccounts(personEmailDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/persons/{personEmail}/categories")
    public ResponseEntity<Object> getPersonCategories(@PathVariable final String personEmail) {

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);

        CategoriesDTO result = service.getPersonCategories(personEmailDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/persons/{personEmail}/siblings")
    public ResponseEntity<Object> getPersonSiblings(@PathVariable final String personEmail) {

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);

        SiblingsDTO result = service.getPersonSiblings(personEmailDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/persons/{personEmail}/groups")
    public ResponseEntity<Object> getPersonGroups(@PathVariable final String personEmail) {

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(personEmail);

        List<GroupDTO> result = service.getPersonGroups(personEmailDTO);

        for (GroupDTO groupDTO : result) {
            Link self_link = WebMvcLinkBuilder.linkTo(methodOn(CreateGroupControllerREST.class).getGroupByDenomination(groupDTO.getDenomination())).withSelfRel();
            groupDTO.add(self_link);
        }

        GroupListDTO groupListDTO = GroupListDTOAssembler.createDTOFromDomainObject(result);

        return new ResponseEntity<>(groupListDTO, HttpStatus.OK);

    }
}

package com.finance.project.controllerLayer.controllersREST.personControllers;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonAccountService;
import com.finance.project.dtos.dtosAssemblers.CreatePersonAccountDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;
import com.finance.project.dtos.dtos.NewPersonAccountInfoDTO;
import com.finance.project.dtos.dtos.PersonDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CreatePersonAccountControllerREST {

    @Autowired
    private CreatePersonAccountService service;

    @PostMapping("/persons/{personEmail}/accounts")
    public ResponseEntity<Object> createPersonAccount(@RequestBody NewPersonAccountInfoDTO info, @PathVariable final String personEmail) {

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.
                createDTOFromPrimitiveTypes(personEmail, info.getDescription(), info.getDenomination());

        PersonDTO result = service.createAccount(createPersonAccountDTO);

        Link link_to_siblings = linkTo(methodOn(CreatePersonControllerREST.class).getPersonSiblings(personEmail)).withRel("siblings");
        Link link_to_personLedger = linkTo(methodOn(PersonSearchAccountRecordsControllerREST.class).searchPersonRecords("", "", "", personEmail)).withRel("records");
        Link link_to_personAccounts = linkTo(methodOn(CreatePersonControllerREST.class).getPersonAccounts(personEmail)).withRel("accounts");
        Link link_to_personCategories = linkTo(methodOn(CreatePersonControllerREST.class).getPersonCategories(personEmail)).withRel("categories");

        result.add(link_to_siblings);
        result.add(link_to_personLedger);
        result.add(link_to_personAccounts);
        result.add(link_to_personCategories);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}

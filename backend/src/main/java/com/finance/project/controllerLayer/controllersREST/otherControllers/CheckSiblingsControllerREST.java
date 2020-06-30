package com.finance.project.controllerLayer.controllersREST.otherControllers;

import com.finance.project.applicationLayer.applicationServices.otherServices.CheckSiblingsService;
import com.finance.project.dtos.dtosAssemblers.CheckIfSiblingsDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.finance.project.dtos.dtos.BooleanDTO;
import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class CheckSiblingsControllerREST {

    @Autowired
    CheckSiblingsService checkSiblingsService;


    @GetMapping("/persons/{id}/siblings/{id_otherPerson}")
    public ResponseEntity<Object> checkIfSiblings(@PathVariable final String id,
                                                  @PathVariable final String id_otherPerson) {


//        Create a DTO to pass all the info to the service

        CheckIfSiblingsDTO checkIfSiblingsDTO = CheckIfSiblingsDTOAssembler.createDTOFromPrimitiveTypes(id, id_otherPerson);

//        Create a DTO with the info received from the service

        BooleanDTO infoReceivedFromService = checkSiblingsService.checkIfSiblings(checkIfSiblingsDTO);

//        Create Hypermedia links to add to the DTO received from the service

//              Hypermedia link of a person
        Link link_to_id = linkTo(CheckSiblingsControllerREST.class)
                .slash(id)
                .slash("person")
                .withRel("person");

//               Hypermedia link of a sibling

        Link link_to_otherPerson = linkTo(CheckSiblingsControllerREST.class)
                .slash(id_otherPerson)
                .slash("sibling")
                .withRel("sibling");

//        Add the hypermedia links to the DTO received from the service

        infoReceivedFromService.add(link_to_id);
        infoReceivedFromService.add(link_to_otherPerson);

        return new ResponseEntity<>(infoReceivedFromService, HttpStatus.OK);

    }


}

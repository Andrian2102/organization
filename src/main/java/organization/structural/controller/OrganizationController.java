package organization.structural.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import organization.structural.services.PersonService;

import java.util.UUID;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity getPersonById (@PathVariable(value = "id") UUID id, HttpServletRequest request){
        ResponseEntity responseEntity = null;
        responseEntity = personService.getDataPerson(id);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById (@PathVariable(value = "id") UUID id, HttpServletRequest request){
        ResponseEntity responseEntity = null;
        responseEntity = personService.deleteDataPerson(id);
        return responseEntity;
    }
}

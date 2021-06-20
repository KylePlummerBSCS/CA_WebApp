package com.revature.WebApp.AllSamples.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.AllSamples.entities.PrototypeEntity;
import com.revature.WebApp.AllSamples.repositories.PrototypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This prototype controller can be used as a template for others. mapped to HTTP endpoints, annotated
 * with @Restcontroller. Has private fields for common objects, including the corresponding repository interface
 * autowired in constructor.
 */
@RestController
public class PrototypeController {

    private final PrototypeRepository protoRepo;
    private final ObjectMapper json;

    @Autowired
    public PrototypeController(PrototypeRepository protoRepo) {
        this.protoRepo = protoRepo;
        json = new ObjectMapper();
    }

    /**
     * This GET servlet forms a list of all prototype entities in the database and returns them in a json string.
     * @param response - HTTP response object
     * @return json formatted string with query results
     * @throws JsonProcessingException thrown if jackson can't parse JSON
     */
    @GetMapping(value="/getprototypeentities", produces = "application/json")
    public String listAllPrototypeEntities(HttpServletResponse response) throws JsonProcessingException {
        ArrayList<PrototypeEntity> prototypeEntityList = (ArrayList<PrototypeEntity>) protoRepo.findAll();
        response.setStatus(200);
        return json.writeValueAsString(prototypeEntityList);
    }

    /**
     * This POST servlet will create a new entity using the json formatted body text, and persist it in the database
     * @param prototypeEntity - autopopulated from request body
     * @param response - HTTP response object
     * @return - json formatted string of newly created object
     * @throws JsonProcessingException thrown if jackson can't parse JSON
     */
    @PostMapping(value = "/createprototypeentity", consumes = "application/json", produces = "application/json")
    public String createNewPrototypeEntity(@RequestBody PrototypeEntity prototypeEntity, HttpServletResponse response) throws JsonProcessingException {
        protoRepo.save(prototypeEntity);
        response.setStatus(201);
        return json.writeValueAsString(prototypeEntity);
    }

    /**
     * This PUT mapping will load an entity from database based on Id, and will update and save the other fields
     * @param updateEntity - autopopulated from request body
     * @param response - HTTP response object
     * @return - json formatted string of newly pdated object
     * @throws JsonProcessingException thrown if jackson can't parse JSON
     */
    @PutMapping(value = "/updateprototypeentity", consumes = "application/json", produces = "application/json")
    public String updatePrototypeEntity(@RequestBody PrototypeEntity updateEntity, HttpServletResponse response) throws JsonProcessingException {
        Optional<PrototypeEntity> prototypeEntityOptional = protoRepo.findById(updateEntity.getId());
        if(prototypeEntityOptional.isPresent()) {
            prototypeEntityOptional.get().setString(updateEntity.getString());
            prototypeEntityOptional.get().setDbl(updateEntity.getDbl());
            prototypeEntityOptional.get().setBool(updateEntity.getBool());
            protoRepo.save(prototypeEntityOptional.get());
            response.setStatus(200);
            return json.writeValueAsString(prototypeEntityOptional.get());
        } else {
            response.setStatus(404);
            return "Object with Id = " + updateEntity.getId() + " not found.";
        }
    }

    /**
     * This DELETE mapping will delete an entity from the database after grabbing the row by ID
     * @param deleteEntity - autopopulated from request body
     * @param response - plain text and status code
     * @return - plain text string corresponding to status code
     */
    @DeleteMapping(value = "/deleteprototypeentity", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public String deletePrototypeEntity(@RequestBody PrototypeEntity deleteEntity, HttpServletResponse response) {
        Optional<PrototypeEntity> prototypeEntityOptional = protoRepo.findById(deleteEntity.getId());
        if(prototypeEntityOptional.isPresent()) {
            protoRepo.delete(prototypeEntityOptional.get());
            response.setStatus(200);
            return "Deleted.";
        } else {
            response.setStatus(404);
            return "Resource not found.";
        }
    }
}

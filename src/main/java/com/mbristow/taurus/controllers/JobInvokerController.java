package com.mbristow.taurus.controllers;

import com.mbristow.taurus.model.User;
import com.mbristow.taurus.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller
 *
 */
@RestController
public class JobInvokerController {

    @Autowired
    ApplicationService applicationService;

    /**
     * GET
     *
     * @return user
     */
    @GetMapping(
            value="/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List< User >> getAllUsers() {
        return ResponseEntity.ok().body(applicationService.getAllUsers());
    }

    /**
     *
     * @param
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity < User > getUserById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(applicationService.getUserById(id));
    }

    /**
     * POST
     *
     * @param user
     * @return
     */
    @PostMapping(
            value = "/user",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity < User > createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(this.applicationService.createUser(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity < User > updateProduct(@PathVariable long id, @RequestBody User user) throws Exception {
        user.setId(id);
        return ResponseEntity.ok().body(this.applicationService.updateUser(user));
    }

    /**
     *
     */
    @DeleteMapping(value = "/user/{id}")
    public HttpStatus invokeDeleteService(@PathVariable("id") long id) throws Exception {
        this.applicationService.deleteUser(id);
        return HttpStatus.OK;
    }
}

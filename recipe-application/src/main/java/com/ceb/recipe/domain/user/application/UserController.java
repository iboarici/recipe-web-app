package com.ceb.recipe.domain.user.application;

import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.ceb.recipe.domain.user.core.model.Exception.UserAlreadyExistsException;
import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;
import com.ceb.recipe.domain.user.core.ports.incoming.UserOperations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@Api("User Operations Controller")
public class UserController {

    @Qualifier("userOperations")
    private final UserOperations userOperations;

    public UserController(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @ApiOperation(value = "returns all users in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return users successfully"),
            @ApiResponse(code = 400, message = "error occured while retrieving users")
    })
    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = userOperations.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "add new user to the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User added successfully"),
            @ApiResponse(code = 404, message = "error occured while adding users")
    })
    @PostMapping("")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody AddUserCommand addUserCommand) {
        try {
            userOperations.addNewUser(addUserCommand);
            return new ResponseEntity<>("New user was added", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param username user name of the authenticated user
     * @return delete specific user from system.
     */
    @ApiOperation(value = "Remove user from the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User removed successfully"),
            @ApiResponse(code = 404, message = "error occured while removing users")
    })
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        try {
            userOperations.deleteUser(username);
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

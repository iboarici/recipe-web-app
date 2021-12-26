package com.ceb.recipe.domain.inventory.application;

import com.ceb.recipe.domain.inventory.core.model.Category;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotFoundException;
import com.ceb.recipe.domain.inventory.core.ports.incoming.CategoryOperations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
@Api("Category Operations Controller")
public class CategoryController {

    @Qualifier("categoryOperations")
    private final CategoryOperations categoryOperations;

    public CategoryController(CategoryOperations categoryOperations) {
        this.categoryOperations = categoryOperations;
    }
/*
    @Autowired



 */
    /**
     *
     * @return All categories in the system.
     */
    @ApiOperation(value = "returns all categories in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return categories successfully"),
            @ApiResponse(code = 400, message = "error occured while retrieving categories")
    })
    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryOperations.getAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (RecipeNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param name Name of the Category
     * @return Http Response
     */

    @ApiOperation(value = "Add new category to the system if authenticated user role is admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "category successfully added"),
            @ApiResponse(code = 400, message = "error occured while adding new category"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin")
    })
    @PostMapping("/{name}")
    public ResponseEntity<String> addNewCategory(@PathVariable("name") String name) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                categoryOperations.addCategory(Category.builder().name(name).build());
                return new ResponseEntity("Category successfully created...", HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    /**
     *
     * @param principal authenticated user info. Filled Automatically by spring security
     * @param id id of the category will be deleted.
     * @return Http Response
     */
    @ApiOperation(value = "Delete category from the system if authenticated user role is admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "category successfully removed"),
            @ApiResponse(code = 400, message = "error occured while adding new category"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(Principal principal, @PathVariable("id") Long id) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                categoryOperations.removeCategory(Category.builder().id(id).build());
                return new ResponseEntity("Category successfully deleted...", HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param principal authenticated user info. Filled Automatically by spring security
     * @param id of the category will be updated.
     * @param name updated name of the category.
     * @return status of the category update operation
     */
    @ApiOperation(value = "Update name of the category in the system if authenticated user role is admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "category successfully updated"),
            @ApiResponse(code = 400, message = "error occured while updating category"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin")
    })
    @PutMapping("/{id}/{name}")
    public ResponseEntity<String> updateCategory(Principal principal, @PathVariable("id") Long id, @PathVariable("name") String name) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                categoryOperations.updateCategory(Category.builder().id(id).name(name).build());
                return new ResponseEntity("Category successfully updated...", HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return role of the authenticated user. (USER or ADMIN)
     */
    private String getRole() {
        List<String > authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return authorities.isEmpty() ? null : authorities.get(0);
    }
}

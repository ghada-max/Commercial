package com.Commercial.commercial.Controller;


import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.category;
import com.Commercial.commercial.DAO.client;
import com.Commercial.commercial.Service.categoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/category")
public class categoryController {
    public final categoryService categoryService;
    @PostMapping(path = "/AddCategory")
    public ResponseEntity<String> addClient(@Valid @RequestBody category c) throws Exception {
        try{  categoryService.addCategory(c); // Store the returned client from the service
            return new ResponseEntity<>("category created successfully", HttpStatus.OK); // Return the created client with a 201 status
        }catch (Exception e){
            return new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.OK); // Return the created client with a 201 status

        }
    }

    @GetMapping(path = "/getAllCategorys")
    public List<category> getAllCategorys() throws Exception {
        try{
            return categoryService.getAllCategory();

        }catch (Exception e){
            return (List<category>) new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.OK); // Return the created client with a 201 status

        }
    }

    @DeleteMapping(path = "/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Integer id) throws Exception {
        try{
            return categoryService.deleteCategory(id);

        }catch (Exception e){
            return String.valueOf(new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR)); // Return the created client with a 201 status

        }
    }

    @PutMapping(path = "/updateCategory/{id}")
    public category updateClient(@PathVariable Integer id, @RequestBody category c) throws Exception {

        return categoryService.updateCategory(id,c);


    }

    @GetMapping(path = "/getCategoryById/{id}")
    public category getCategoryById(@PathVariable Integer id)  {

        return categoryService.getCategoryById(id);


    }
}

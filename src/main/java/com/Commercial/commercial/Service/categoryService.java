package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.category;
import com.Commercial.commercial.DAO.client;
import com.Commercial.commercial.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService {
    @Autowired
    CategoryRepository categoryRepo;
    public category addCategory(category catg) {

        categoryRepo.save(catg);
        return catg;
    }

    public List<category> getAllCategory() {
       return categoryRepo.findAll();
    }

    public String deleteCategory(Integer id) {

            return  categoryRepo.findById(id).map(category -> {
                categoryRepo.deleteById(id);
                return "category deleted successfully";
            }).orElse("category with id "+id+" not found");
        }

    public category updateCategory(Integer id, category c) {
        return categoryRepo.findById(id).map(updatingCateg-> {
            updatingCateg.setName(c.getName());
            return updatingCateg;
        }).orElseThrow(()->new EntityNotFoundException("category not found while updating"));
    }

    public category getCategoryById(Integer id) {
        return categoryRepo.findById(id).orElseThrow(()->new EntityNotFoundException("category with id "+id+" not found"));
    }

}

package com.todolist.controller;

import com.todolist.model.Deal;
import com.todolist.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("deal")
public class DealController {
    private final DealRepository dealRepository;

    @Autowired
    public DealController(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<List<Deal>> saveDeals(@RequestBody List<Deal> list) {
        return new ResponseEntity<>(dealRepository.save(list), HttpStatus.CREATED);
    }

    @DeleteMapping("/clear-all-completed")
    public ResponseEntity clearAllCompleted() {
        dealRepository.deleteAllCompleted();
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

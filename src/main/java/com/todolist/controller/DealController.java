package com.todolist.controller;

import com.todolist.model.Deal;
import com.todolist.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
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
        return ResponseEntity.ok(dealRepository.save(list));
    }

    @DeleteMapping("/clear-all-completed")
    public ResponseEntity.HeadersBuilder<?> clearAllCompleted() {
        dealRepository.deleteAllCompleted();
        return ResponseEntity.noContent();
    }

}

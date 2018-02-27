package com.todolist.repository;


import com.todolist.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource(
        itemResourceRel = "deal", path = "deal", collectionResourceRel = "deals")
public interface DealRepository extends JpaRepository<Deal, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM  Deal d WHERE  d.completed=true")
    void deleteAllCompleted();
}

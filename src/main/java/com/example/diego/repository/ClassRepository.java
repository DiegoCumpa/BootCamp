package com.example.diego.repository;

import com.example.diego.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Classes,Integer> {

    @Query(value = "SELECT * from classes where isactive = 1 order by class_id ASC",nativeQuery = true)
    public List<Classes> findAll();

    @Modifying
    @Transactional
    @Query(value = "update classes set isactive=0 where class_id = ?1", nativeQuery = true)
    void deleteClass(Integer idClass);

}

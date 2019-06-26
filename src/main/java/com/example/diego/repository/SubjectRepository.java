package com.example.diego.repository;

import com.example.diego.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    @Query(value = "SELECT * from subjects where isactive = 1 order by subject_id ASC",nativeQuery = true)
    public List<Subject> findAll();

    @Modifying
    @Transactional
    @Query(value = "update subjects set isactive=0 where subject_id = ?1", nativeQuery = true)
    void deleteSubject(Integer idSuject);

    @Query(value = "SELECT * from subjects where isactive = 1 and subject_id = ?1",nativeQuery = true)
    public Optional<Subject> findById(Integer idSuject);

    @Query(value = "SELECT * from subjects where subject_id = ?1",nativeQuery = true)
    Subject findId(Integer idSuject);

}

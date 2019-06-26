package com.example.diego.repository;

import com.example.diego.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    /**
     * método para obtener todas los Teachers activos
     *
     */
    @Query(value = "SELECT * from teachers where isactive = 1 order by teacher_id ASC",nativeQuery = true)
    public List<Teacher> findAll();

    /**
     * método para cambiar el estado de Teacher
     * @param idTeacher parametro id de teacher que va ser eliminado
     */

    @Modifying
    @Transactional
    @Query(value = "update teachers set isactive=0 where teacher_id = ?1", nativeQuery = true)
    void deleteTeacher(Integer idTeacher);


}

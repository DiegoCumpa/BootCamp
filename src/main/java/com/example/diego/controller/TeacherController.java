package com.example.diego.controller;

import com.example.diego.model.Teacher;
import com.example.diego.repository.TeacherRepository;
import com.example.diego.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path ="/apirest/api/v1" )
@Api(value = "TeahcerControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE )
public class TeacherController {

    @Autowired(required = true)
    TeacherService teacherService;

    @Autowired(required = true)
    TeacherRepository teacherRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/teachers")
    @ResponseBody
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        log.info("Entró a getAllTeachers");
        return new ResponseEntity<List<Teacher>>(teacherService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/teachers")
    @ResponseBody
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {
        log.info("Entró a add");
        Optional<Teacher> OT = teacherRepository.findById(teacher.getTeacherId());
        if (OT.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Teacher>(teacherService.save(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/teachers/{teacherId}")
    @ResponseBody
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher, @PathVariable int teacherId) {
        log.info("Entró a update");
        Optional<Teacher> OT = teacherRepository.findById(teacherId);
        if (!OT.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Teacher>(teacherService.update(teacher, teacherId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/teachers/{teacherId}")
    @ResponseBody
    public ResponseEntity<Teacher> delete(@PathVariable Integer teacherId) {
        log.info("Entró a delete");
        Optional<Teacher> OT = teacherRepository.findById(teacherId);
        if (!OT.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        teacherService.delete(teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.example.diego.controller;


import com.example.diego.model.Subject;
import com.example.diego.repository.SubjectRepository;
import com.example.diego.service.SubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/apirest/api/v1" )
@Api(value = "SubjectControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE )
public class SubjectController {

    @Autowired(required = true)
    SubjectService subjectService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/subjects")
    @ResponseBody
    public ResponseEntity<List<Subject>> getAllSubjects() {
        log.info("Entró a getAllSubjects");
        return new ResponseEntity<List<Subject>>(subjectService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/subjects")
    @ResponseBody
    public ResponseEntity<Subject> add(@Valid @RequestBody Subject subject) {
        log.info("Entró a add");
        Optional<Subject> OT = subjectService.getOne(subject.getSubjectId());
        if (OT.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        subjectService.save(subject);

        return new ResponseEntity<Subject>(subject, HttpStatus.CREATED);
    }

    @PutMapping("/subjects/{subjectId}")
    @ResponseBody
    public ResponseEntity<Subject> update(@RequestBody Subject subject, @PathVariable int subjectId) {
        log.info("Entró a update");
        Optional<Subject> OT = subjectService.getOne(subjectId);
        if (!OT.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Subject>(subjectService.update(subject, subjectId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/subjects/{subjectId}")
    @ResponseBody
    public ResponseEntity<Subject> delete(@Validated @PathVariable Integer subjectId) {
        log.info("Entró a delete");
        Optional<Subject> OT = subjectService.getOne(subjectId);
        if (!OT.isPresent())
            return ResponseEntity.badRequest().build();

        subjectService.delete(subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/subjects/{subjectId}")
    public ResponseEntity<Subject> getOne(@PathVariable int subjectId) {
        log.info("Entró a getOne");
        /*Optional<Subject> OT = subjectService.getOne(subjectId);
        if (OT.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/

        return new ResponseEntity<Subject>(subjectService.findId(subjectId), HttpStatus.OK);
    }


}

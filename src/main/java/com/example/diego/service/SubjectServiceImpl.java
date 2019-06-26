package com.example.diego.service;

import com.example.diego.model.Subject;
import com.example.diego.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;



    @Override
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @Override
    public Subject save(Subject subject) {

        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Subject subject, int subjectId) {
        subjectRepository.findById(subjectId).ifPresent(p -> {
            subject.setSubjectId(subjectId);
            subjectRepository.save(subject);
        });
        return subject;
    }

    @Override
    public Subject delete(Integer subjectId) {
        subjectRepository.findById(subjectId).ifPresent(p -> {
            subjectRepository.deleteSubject(subjectId);
        });
        return null;
    }

    @Override
    public Optional<Subject> getOne(int subjectId) {
        Optional<Subject> optional = subjectRepository.findById(subjectId);
        return optional;
    }

    @Override
    public Subject findId(int subjectId) {
        Subject subject = subjectRepository.findId(subjectId);
        return subject;
    }


}

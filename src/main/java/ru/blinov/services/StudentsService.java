package ru.blinov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.blinov.entities.Student;
import ru.blinov.repositories.StudentsRepository;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public StudentsService() {
    }

    public Student getStudentById(Long id) {
        return studentsRepository.findStudentById(id);
    }

//    public List<Student> getAllStudents() {
//        return studentsRepository.findAllStudents();
////        return (List)studentsRepository.findAll();
////        return studentsRepository.getStudentsIdsByCoursesCountDesc();
//    }

}

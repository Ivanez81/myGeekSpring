package ru.blinov.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.blinov.entities.Student;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
    Student findStudentById(Long id);

//    @Query("SELECT s FROM Student s ")
//    List<Student> findAllStudents();

//    @Query(value = "SELECT s FROM student s " +
//            "LEFT JOIN student_course c on s.id = c.student_id " +
//            "GROUP BY s.id " +
//            "ORDER BY count(distinct c.course_id) DESC;", nativeQuery = true)
//    List<Student> getStudentsIdsByCoursesCountDesc();
}

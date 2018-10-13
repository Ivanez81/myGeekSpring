package ru.blinov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.blinov.entities.Student;
import ru.blinov.services.StudentCourseService;
import ru.blinov.services.StudentsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;
    private StudentCourseService studentCourseService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    public void setStudentCourseService(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    public StudentsController() {
    }

    // http://localhost:8189/students/showStudentById?id=2
    @RequestMapping(path = "/showStudentById", method = RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam Long id) {
        Student student = studentsService.getStudentById(id);
        model.addAttribute("student", student);
        return "student-by-id";
    }

    // http://localhost:8189/students/showStudents
    @RequestMapping("/showStudents")
    public String showStudents(Model model) {
        List<Long> studentIdList = studentCourseService.getAllStudents();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < studentIdList.size(); i++) {
            studentList.add(studentsService.getStudentById(studentIdList.get(i)));
        }
        model.addAttribute("students", studentList);
        return "student-all";
    }

//    @RequestMapping("/showForm")
//    public String showSimpleForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student-form";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getFirstName() + " " + student.getLastName());
//        return "student-form-result";
//    }
//
//    @RequestMapping(path="/getStudentById", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentById(@RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
//
//    @RequestMapping(path="/getStudentById/{sid}", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
}

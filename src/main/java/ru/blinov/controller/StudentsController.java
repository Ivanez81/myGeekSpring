package ru.blinov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.blinov.entities.Student;
import ru.blinov.services.StudentsService;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }    
    
    public StudentsController() {
    }    
    
    @RequestMapping("/showForm")
    public String showSimpleForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }
    
    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());        
        return "student-form-result";
    }
    
    // http://localhost:8189/students/showStudentById?id=5
    @RequestMapping(path="/showStudentById", method= RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam int id) {
        Student student = studentsService.getStudentById(new Long(id));
        model.addAttribute("student", student);
        return "student-form-result";
    }
    
    @RequestMapping(path="/getStudentById", method= RequestMethod.GET)
    @ResponseBody
    public Student getStudentById(@RequestParam int id) {
        Student student = studentsService.getStudentById(new Long(id));
        return student;
    }
    
    @RequestMapping(path="/getStudentById/{sid}", method= RequestMethod.GET)
    @ResponseBody
    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
        Student student = studentsService.getStudentById(new Long(id));
        return student;
    }
}

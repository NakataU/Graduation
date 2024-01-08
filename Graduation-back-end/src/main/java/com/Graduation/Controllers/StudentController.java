package com.Graduation.Controllers;

import com.Graduation.DTO.StudentDTO;
import com.Graduation.ServiceImpl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "student")
public class StudentController {

    private final StudentServiceImpl studentServiceimpl;
    @GetMapping("/all")
    public List<StudentDTO> getAllStudents(){
        return studentServiceimpl.findAll();
    }

    @GetMapping("/{studentId}")
    public StudentDTO getStudentById(@PathVariable("studentId") Long studentId){
        return studentServiceimpl.findById(studentId);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody StudentDTO studentDTO){
       studentServiceimpl.addStudent(studentDTO);
    }

    @PutMapping(value = "/edit/{studentId}")
    public void editStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDTO studentDTO) {
        studentServiceimpl.editStudent(studentId,studentDTO);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        this.studentServiceimpl.deleteStudent(studentId);
    }

}

package com.Graduation.Controllers;

import com.Graduation.DTO.TeacherDTO;
import com.Graduation.ServiceImpl.TeacherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "Teacher")
public class TeacherController {

    private final TeacherServiceImpl TeacherServiceimpl;

    @GetMapping("/all")
    public List<TeacherDTO> getAllTeachers(){
        return TeacherServiceimpl.findAll();
    }

    @GetMapping("/{TeacherId}")
    public TeacherDTO getTeacherById(@PathVariable("TeacherId") Long TeacherId){
        return TeacherServiceimpl.findById(TeacherId);
    }

    @PostMapping("/add")
    public void addTeacher(@RequestBody TeacherDTO TeacherDTO){
        TeacherServiceimpl.addTeacher(TeacherDTO);
    }

    @PutMapping(value = "/edit/{TeacherId}")
    public void editTeacher(@PathVariable("TeacherId") Long TeacherId, @RequestBody TeacherDTO TeacherDTO) {
        TeacherServiceimpl.editTeacher(TeacherId,TeacherDTO);
    }

    @DeleteMapping("/delete/{TeacherId}")
    public void deleteTeacher(@PathVariable("TeacherId") Long TeacherId){
        this.TeacherServiceimpl.deleteTeacher(TeacherId);
    }
}


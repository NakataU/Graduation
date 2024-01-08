package com.Graduation.Service;

import com.Graduation.DTO.StudentDTO;
import java.util.List;

public interface StudentService {
    public void addStudent(StudentDTO studentDTO);

    public void editStudent(Long id,StudentDTO studentDTO);
    public StudentDTO findById(Long studentId);
    public List<StudentDTO> findAll();
    public void deleteStudent(Long studentId);

    public StudentDTO findByName(String name);
}

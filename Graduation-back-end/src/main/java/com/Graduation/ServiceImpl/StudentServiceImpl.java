package com.Graduation.ServiceImpl;

import com.Graduation.DTO.StudentDTO;
import com.Graduation.Repos.StudentRepo;
import com.Graduation.Service.StudentService;
import com.Graduation.entity.Student;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    private final ModelMapper modelMapper;

    @Override
    public void addStudent(StudentDTO studentDTO) {
        studentRepo.save(modelMapper.map(studentDTO, Student.class));

    }

    @Override
    public void editStudent(Long studentId, StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();

            // Map the fields from the DTO to the existing student entity
            modelMapper.map(studentDTO, existingStudent);
            existingStudent.setId(studentId);

            // Save the updated student entity
            studentRepo.save(existingStudent);
        } else {
            // Handle the case where the student with the given ID is not found
            throw new EntityNotFoundException("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public StudentDTO findById(Long studentId) {
        return modelMapper.map(studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school Id:" + studentId)), StudentDTO.class);
    }


    @Override
    public List<StudentDTO> findAll() {
        return studentRepo.findAll().stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteStudent(Long studentId) {
        this.studentRepo.deleteById(studentId);
    }

    @Override
    public StudentDTO findByName(String name) {
        return this.studentRepo.findStudentByName(name);
    }

    private StudentDTO convertToStudentDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}

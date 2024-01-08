package com.Graduation.Repos;

import com.Graduation.DTO.StudentDTO;
import com.Graduation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    StudentDTO findStudentByName(String name);

}

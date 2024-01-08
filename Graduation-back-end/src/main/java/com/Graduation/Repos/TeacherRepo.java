package com.Graduation.Repos;

import com.Graduation.DTO.TeacherDTO;
import com.Graduation.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
}


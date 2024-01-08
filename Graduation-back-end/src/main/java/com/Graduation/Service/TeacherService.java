package com.Graduation.Service;

import com.Graduation.DTO.TeacherDTO;
import java.util.List;

public interface TeacherService {

    public void addTeacher(TeacherDTO TeacherDTO);
    public void editTeacher(Long id,TeacherDTO TeacherDTO);
    public TeacherDTO findById(Long TeacherId);
    public List<TeacherDTO> findAll();
    public void deleteTeacher(Long TeacherId);

}

package com.Graduation.Service;

import com.Graduation.DTO.ApplicationDocumentDTO;
import com.Graduation.DTO.EditApplicationDTO;

import java.util.List;

public interface ApplicationDocumentService{
    public void addApplicationDocument(ApplicationDocumentDTO ApplicationDocumentDTO);

    public void editApplicationDocument(Long id, EditApplicationDTO editApplicationDTO);
    public ApplicationDocumentDTO findById(Long ApplicationDocumentId);
    public List<ApplicationDocumentDTO> findAll();

//    public void addTeacher(Long id,Long teacherId);
//
//    public void addStudent(Long id,Long studentId);
    public void deleteApplicationDocument(Long ApplicationDocumentId);
}

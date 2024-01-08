package com.Graduation.ServiceImpl;

import com.Graduation.DTO.ApplicationDocumentDTO;
import com.Graduation.DTO.EditApplicationDTO;
import com.Graduation.DTO.StudentDTO;
import com.Graduation.DTO.TeacherDTO;
import com.Graduation.Repos.ApplicationDocumentRepo;
import com.Graduation.Repos.StudentRepo;
import com.Graduation.Repos.TeacherRepo;
import com.Graduation.Service.ApplicationDocumentService;
import com.Graduation.Service.StudentService;
import com.Graduation.Service.TeacherService;
import com.Graduation.entity.ApplicationDocument;
import com.Graduation.entity.Student;
import com.Graduation.entity.Teacher;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationDocumentServiceImpl implements ApplicationDocumentService {

    private final ApplicationDocumentRepo applicationDocumentRepo;

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final ModelMapper modelMapper;

    @Override
    public void addApplicationDocument(ApplicationDocumentDTO ApplicationDocumentDTO) {
        ApplicationDocumentDTO.setApproved(false);
        applicationDocumentRepo.save(modelMapper.map(ApplicationDocumentDTO, ApplicationDocument.class));

    }

    @Override
    public void editApplicationDocument(Long applicationDocumentId, EditApplicationDTO editApplicationDTO) {
        Optional<ApplicationDocument> optionalApplicationDocument = applicationDocumentRepo.findById(applicationDocumentId);

        if (optionalApplicationDocument.isPresent()) {
            ApplicationDocument existingApplicationDocument = optionalApplicationDocument.get();

            modelMapper.map(editApplicationDTO, existingApplicationDocument);

            // Set the ID
            existingApplicationDocument.setId(applicationDocumentId);
            System.out.println(editApplicationDTO.getTeacherLeader());

            System.out.println(editApplicationDTO);
            // Check for null or empty values in editApplicationDTO
            if (editApplicationDTO.getTeacherLeader() != null && !editApplicationDTO.getTeacherLeader().isEmpty()) {
                TeacherDTO teacherDTO = this.teacherService.findById(Long.parseLong(editApplicationDTO.getTeacherLeader()));
                existingApplicationDocument.setTeacherLeader(modelMapper.map(teacherDTO, Teacher.class));
            }

            if (editApplicationDTO.getStudent() != null && !editApplicationDTO.getStudent().isEmpty()) {
                StudentDTO studentDTO = this.studentService.findById(Long.parseLong(editApplicationDTO.getStudent()));
                existingApplicationDocument.setStudent(modelMapper.map(studentDTO, Student.class));
            }

            // Map other properties from editApplicationDTO

            // Save the updated ApplicationDocument entity
            applicationDocumentRepo.save(existingApplicationDocument);
        } else {
            // Handle the case where the ApplicationDocument with the given ID is not found
            throw new EntityNotFoundException("ApplicationDocument with ID " + applicationDocumentId + " not found");
        }
    }
    @Override
    public ApplicationDocumentDTO findById(Long ApplicationDocumentId) {
        return modelMapper.map(applicationDocumentRepo.findById(ApplicationDocumentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school Id:" + ApplicationDocumentId)), ApplicationDocumentDTO.class);
    }


    @Override
    public List<ApplicationDocumentDTO> findAll() {
        return applicationDocumentRepo.findAll().stream()
                .map(this::convertToApplicationDocumentDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public void addTeacher(Long id, Long teacherId) {
//        Teacher existingTeacher = this.studentService.findById(teacherId).get();
//
//        this.applicationDocumentRepo.findById(id).get().setTeacherLeader(existingTeacher);
//    }
//
//    @Override
//    public void addStudent(Long id, Long studentId) {
//        Student existingStudent = this.studentRepo.findById(studentId).get();
//
//        this.applicationDocumentRepo.findById(id).get().setStudent(existingStudent);
//    }


    @Override
    public void deleteApplicationDocument(Long ApplicationDocumentId) {
        this.applicationDocumentRepo.deleteById(ApplicationDocumentId);
    }

    private ApplicationDocumentDTO convertToApplicationDocumentDTO(ApplicationDocument ApplicationDocument) {
        return modelMapper.map(ApplicationDocument, ApplicationDocumentDTO.class);
    }
}

package com.Graduation.Controllers;

import com.Graduation.DTO.ApplicationDocumentDTO;
import com.Graduation.DTO.EditApplicationDTO;
import com.Graduation.ServiceImpl.ApplicationDocumentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "applicationDocument")
public class ApplicationDocumentController {
    private final ApplicationDocumentServiceImpl applicationDocumentService;

    @GetMapping("/all")
    public List<ApplicationDocumentDTO> getAllApplicationDocuments() {
        return applicationDocumentService.findAll();
    }

    @GetMapping("/{ApplicationDocumentId}")
    public ApplicationDocumentDTO getApplicationDocumentById(@PathVariable("ApplicationDocumentId") Long applicationDocumentId) {
        return applicationDocumentService.findById(applicationDocumentId);
    }

    @PostMapping("/add")
    public void addApplicationDocument(@RequestBody ApplicationDocumentDTO ApplicationDocumentDTO) {
        this.applicationDocumentService.addApplicationDocument(ApplicationDocumentDTO);
    }

    @PutMapping(value = "/edit/{applicationDocumentId}")
    public void editApplicationDocument(@PathVariable("applicationDocumentId") Long applicationDocumentId, @RequestBody EditApplicationDTO editApplicationDTO) {
        this.applicationDocumentService.editApplicationDocument(applicationDocumentId, editApplicationDTO);
        System.out.println("In controller" + editApplicationDTO);
    }

//    @PatchMapping(value = "/addTeacherToApplication/{applicationDocumentId}/{teacherId}")
//    public void addTeacherToApplication(@PathVariable("applicationDocumentId") Long applicationDocumentId, @PathVariable("teacherId") Long teacherId) {
//        this.applicationDocumentService.addTeacher(applicationDocumentId, teacherId);
//    }
//
//    @PatchMapping(value = "/addStudentToApplication/{applicationDocumentId}/{studentId}")
//    public void addStudentToApplication(@PathVariable("applicationDocumentId") Long applicationDocumentId, @PathVariable("studentId") Long studentId) {
//        this.applicationDocumentService.addStudent(applicationDocumentId, studentId);
//    }

    @DeleteMapping("/delete/{applicationDocumentId}")
    public void deleteApplicationDocument(@PathVariable("applicationDocumentId") Long applicationDocumentId) {
        this.applicationDocumentService.deleteApplicationDocument(applicationDocumentId);
    }


}

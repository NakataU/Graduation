package com.Graduation.ServiceImpl;

import com.Graduation.DTO.TeacherDTO;
import com.Graduation.Repos.TeacherRepo;
import com.Graduation.Service.TeacherService;
import com.Graduation.entity.Teacher;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo TeacherRepo;

    private final ModelMapper modelMapper;

    @Override
    public void addTeacher(TeacherDTO TeacherDTO) {
        TeacherRepo.save(modelMapper.map(TeacherDTO, Teacher.class));

    }

    @Override
    public void editTeacher(Long TeacherId, TeacherDTO TeacherDTO) {
        Optional<Teacher> optionalTeacher = TeacherRepo.findById(TeacherId);

        if (optionalTeacher.isPresent()) {
            Teacher existingTeacher = optionalTeacher.get();

            // Map the fields from the DTO to the existing Teacher entity
            modelMapper.map(TeacherDTO, existingTeacher);
            existingTeacher.setId(TeacherId);

            // Save the updated Teacher entity
            TeacherRepo.save(existingTeacher);
        } else {
            // Handle the case where the Teacher with the given ID is not found
            throw new EntityNotFoundException("Teacher with ID " + TeacherId + " not found");
        }
    }

    @Override
    public TeacherDTO findById(Long TeacherId) {
        return modelMapper.map(TeacherRepo.findById(TeacherId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school Id:" + TeacherId)), TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> findAll() {
        return TeacherRepo.findAll().stream()
                .map(this::convertToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeacher(Long TeacherId) {
        this.TeacherRepo.deleteById(TeacherId);
    }

    private TeacherDTO convertToTeacherDTO(Teacher Teacher) {
        return modelMapper.map(Teacher, TeacherDTO.class);
    }
}

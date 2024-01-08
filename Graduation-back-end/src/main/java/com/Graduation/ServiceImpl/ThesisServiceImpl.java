package com.Graduation.ServiceImpl;

import com.Graduation.DTO.ThesisDTO;
import com.Graduation.Repos.ThesisRepo;
import com.Graduation.Service.ThesisService;
import com.Graduation.entity.Thesis;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepo thesisRepo;

    private final ModelMapper modelMapper;

    @Override
    public void addThesis(ThesisDTO ThesisDTO) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        // Convert Timestamp to LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Extract LocalDate from LocalDateTime
        LocalDate localDate = localDateTime.toLocalDate();

        // Format LocalDate using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        ThesisDTO.setUploadDate(LocalDate.parse(formattedDate, formatter));

        thesisRepo.save(modelMapper.map(ThesisDTO, Thesis.class));


    }

    @Override
    public void editThesis(Long ThesisId, ThesisDTO ThesisDTO) {
        Optional<Thesis> optionalThesis = thesisRepo.findById(ThesisId);

        if (optionalThesis.isPresent()) {
            Thesis existingThesis = optionalThesis.get();

            modelMapper.map(ThesisDTO, existingThesis);
            existingThesis.setId(ThesisId);


            thesisRepo.save(existingThesis);
        } else {
            // Handle the case where the Thesis with the given ID is not found
            throw new EntityNotFoundException("Thesis with ID " + ThesisId + " not found");
        }
    }

    @Override
    public ThesisDTO findById(Long ThesisId) {
        return modelMapper.map(thesisRepo.findById(ThesisId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school Id:" + ThesisId)), ThesisDTO.class);
    }

    @Override
    public List<ThesisDTO> findAll() {
        return thesisRepo.findAll().stream()
                .map(this::convertToThesisDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteThesis(Long ThesisId) {
        this.thesisRepo.deleteById(ThesisId);
    }

    private ThesisDTO convertToThesisDTO(Thesis Thesis) {
        return modelMapper.map(Thesis, ThesisDTO.class);
    }
}

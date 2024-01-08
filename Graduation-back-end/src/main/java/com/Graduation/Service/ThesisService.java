package com.Graduation.Service;

import com.Graduation.DTO.ThesisDTO;


import java.util.List;


public interface ThesisService {

    public void addThesis(ThesisDTO ThesisDTO);

    public void editThesis(Long id,ThesisDTO ThesisDTO);
    public ThesisDTO findById(Long ThesisId);
    public List<ThesisDTO> findAll();
    public void deleteThesis(Long ThesisId);
}

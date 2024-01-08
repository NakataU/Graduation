package com.Graduation.Controllers;

import com.Graduation.DTO.ThesisDTO;
import com.Graduation.ServiceImpl.ThesisServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "thesis")
public class ThesisController {

    private final ThesisServiceImpl thesisServiceimpl;

    @GetMapping("/all")
    public List<ThesisDTO> getAllThesis(){
        return thesisServiceimpl.findAll();
    }

    @GetMapping("/{ThesisId}")
    public ThesisDTO getThesisById(@PathVariable("ThesisId") Long ThesisId){
        return thesisServiceimpl.findById(ThesisId);
    }

    @PostMapping("/add")
    public void addThesis(@RequestBody ThesisDTO ThesisDTO){
        thesisServiceimpl.addThesis(ThesisDTO);
    }

    @PutMapping(value = "/edit/{ThesisId}")
    public void editThesis(@PathVariable("ThesisId") Long ThesisId, @RequestBody ThesisDTO ThesisDTO) {
        thesisServiceimpl.editThesis(ThesisId,ThesisDTO);
    }

    @DeleteMapping("/delete/{ThesisId}")
    public void deleteThesis(@PathVariable("ThesisId") Long ThesisId){
        this.thesisServiceimpl.deleteThesis(ThesisId);
    }
}

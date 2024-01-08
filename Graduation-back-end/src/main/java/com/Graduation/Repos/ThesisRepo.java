package com.Graduation.Repos;

import com.Graduation.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisRepo extends  JpaRepository<Thesis,Long> {
}

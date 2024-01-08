package com.Graduation.Repos;

import com.Graduation.entity.ApplicationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDocumentRepo extends JpaRepository<ApplicationDocument,Long> {
}

package com.example.demo.repository;

import com.example.demo.entity.EdgeFileExport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeFileExportRepository extends
		JpaRepository<EdgeFileExport, Long>, JpaSpecificationExecutor {

	List<EdgeFileExport> findAllByOrderByRequestTimeDesc();
}

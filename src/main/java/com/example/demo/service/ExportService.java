package com.example.demo.service;

import com.example.demo.data.EdgeFileExportData;
import com.example.demo.entity.EdgeFileExport;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * @author qsky on 2018/7/2
 */
public interface ExportService {

	List<EdgeFileExportData> getAll();

	Page<EdgeFileExport> listRecord(Date startTime, Date endTime, String name);
}

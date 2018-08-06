package com.example.demo.controller;

import com.example.demo.data.EdgeFileExportData;
import com.example.demo.page.PageConverter;
import com.example.demo.page.PageData;
import com.example.demo.service.ExportService;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsky on 2018/8/3
 */
@RestController
@RequestMapping("/export")
public class ExportController {

	@Resource
	private ExportService exportService;

	@GetMapping(path = "/listRecord")
	public PageData<EdgeFileExportData> listRecord(
			@RequestParam(name = "startTime", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@RequestParam(name = "endTime", required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
			@RequestParam(name = "name", required = false) String name) {
		return PageConverter.convert(exportService.listRecord(startTime, endTime, name), EdgeFileExportData.class);
	}
}

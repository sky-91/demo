package com.example.demo.service.impl;

import com.example.demo.data.EdgeFileExportData;
import com.example.demo.entity.EdgeFileExport;
import com.example.demo.repository.EdgeFileExportRepository;
import com.example.demo.service.ExportService;
import com.example.demo.util.CglibBeanUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author qsky on 2018/7/2
 */
@Service
public class ExportServiceImpl implements ExportService {

	@Resource
	private EdgeFileExportRepository exportRepository;

	@Override
	public List<EdgeFileExportData> getAll() {
		return CglibBeanUtil.converterList(exportRepository.findAllByOrderByRequestTimeDesc(), EdgeFileExportData.class);
	}

	@Override
	public Page<EdgeFileExport> listRecord(Date startTime, Date endTime, String name) {
		Specification specification = (root, query, cb) -> {
			List<Predicate> andList = new ArrayList<>();
			if (null != startTime && null != endTime) {
				andList.add(cb.greaterThanOrEqualTo(root.get("dataDate"), startTime));
				andList.add(cb.lessThanOrEqualTo(root.get("dataDate"), endTime));
			}
			if (null != startTime && null == endTime) {
				andList.add(cb.greaterThanOrEqualTo(root.get("dataDate"), startTime));
			}
			if (null == startTime && null != endTime) {
				andList.add(cb.lessThanOrEqualTo(root.get("dataDate"), endTime));
			}
			Predicate[] predicatesAndArr = new Predicate[andList.size()];
			Predicate predicatesAnd = cb.and(andList.toArray(predicatesAndArr));
			List<Predicate> orList = new ArrayList<>();
			if (StringUtils.isNotBlank(name)) {
				orList.add(cb.like(cb.upper(root.get("inputName")), "%" + StringUtils.upperCase(StringUtils.trim(name)) + "%"));
				orList.add(cb.isNull(root.get("inputName")));
			}
			Predicate[] predicatesOrArr = new Predicate[orList.size()];
			Predicate predicatesOr = cb.or(orList.toArray(predicatesOrArr));
			return query.where(predicatesAnd, predicatesOr).getRestriction();
		};
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "requestTime"));
		EdgeFileExport model = exportRepository.findById(Long.valueOf("123")).get();
		return exportRepository.findAll(specification, PageRequest.of(0, 1, sort));
	}
}

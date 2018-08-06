package com.example.demo.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

/**
 * @author qsky on 2018/4/4
 */
public class PageConverter {

	public static <T, S> PageData<T> convert(Page<S> page, Class<T> tClass) {
		if (page.getTotalElements() == 0) {
			return new PageData<>(page.getNumber(),
					page.getSize(), page.getTotalPages(), page.getTotalElements(), Collections.emptyList());
		}
		List<T> targetList = new ArrayList<>();
		for (S source : page.getContent()) {
			T target = BeanUtils.instantiate(tClass);
			BeanUtils.copyProperties(source, target);
			targetList.add(target);
		}
		return new PageData<>(page.getNumber(),
				page.getSize(), page.getTotalPages(), page.getTotalElements(), targetList);
	}
}

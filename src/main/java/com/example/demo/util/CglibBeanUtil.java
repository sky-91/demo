package com.example.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author qsky
 */
public class CglibBeanUtil {

	private static ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

	/**
	 * @param source 资源类
	 * @param target 目标类
	 * @Title: copyProperties
	 * @Description: bean属性转换
	 */
	public static void copyProperties(Object source, Object target) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	public static <T> T copyProperties(Object source, Class<T> targetClass) {
		Object target = BeanUtils.instantiate(targetClass);
		copyProperties(source, target);
		return (T) target;
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

	/**
	 * list对象转换
	 *
	 * @param inList 源list对象
	 * @param o 目标对象
	 * @param <O> 目标对象
	 * @param <I> 源对象
	 * @return 目标对象list
	 */
	public static <O, I> List<O> converterList(List<I> inList, Class<O> o) {
		if (inList == null || inList.size() == 0) {
			return Collections.emptyList();
		}
		List<O> outList = new ArrayList<>(inList.size());
		for (I i : inList) {
			O oo = BeanUtils.instantiate(o);
			copyProperties(i, oo);
			outList.add(oo);
		}
		return outList;
	}
}

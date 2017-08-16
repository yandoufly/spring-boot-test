package com.yjy.core.vo.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;

public abstract class AbstractQueryVo<T> implements QueryVo<T> {

	private static final long serialVersionUID = -1735327084549814922L;

	/**
	 * 页码
	 */
	private int pageNumber;

	/**
	 * 每页数量
	 */
	private int pageSize;

	/**
	 * 排序字符串，支持code:asc，name desc
	 */
	private String sortString;

	/**
	 * 排序集合
	 */
	private List<Order> orders = new ArrayList<Order>();

	@Override
	public Pageable getPageable() {
		return new PageRequest(pageNumber, pageSize, getSort());
	}

	@Override
	public Sort getSort() {
		if (!StringUtils.isEmpty(sortString)) {
			String[] sortStrings = StringUtils.split(sortString, ",");
			for (String string : sortStrings) {
				String[] sorts = StringUtils.split(string, ":");
				if (sorts.length > 1) {
					Direction direction = Direction.fromStringOrNull(sorts[1]);
					addSort(direction == null ? Direction.ASC : direction, sorts[0]);
				} else {
					addSort(Direction.ASC, sorts[0]);
				}
			}
		} else {
			addSort(Direction.ASC, "id");
		}
		return new Sort(orders);
	}

	/** 页码 */
	public int getPageNumber() {
		return pageNumber;
	}

	/** 页码 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**每页数量*/
	public int getPageSize() {
		return pageSize;
	}

	/**每页数量*/
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**排序字符串，支持code:asc，name desc*/
	public String getSortString() {
		return sortString;
	}

	/**排序字符串，支持code:asc，name desc*/
	public void setSortString(String sortString) {
		this.sortString = sortString;
	}

	 /**添加排序*/
	private void addSort(Direction direction, String... properties) {
		if (properties != null) {
			for (String property : properties) {
				orders.add(new Order(direction, property));
			}
		}
	}

}

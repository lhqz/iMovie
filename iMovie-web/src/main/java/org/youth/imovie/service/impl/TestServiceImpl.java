package org.youth.imovie.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youth.imovie.dao.TestMapper;
import org.youth.imovie.pojo.entity.Test;
import org.youth.imovie.service.TestService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TestServiceImpl implements TestService {

	@Resource
	private TestMapper testMapper;
	
	@Override
	public PageInfo<Test> findAll(Page<Test> page) {
		PageHelper.startPage(page.getStartRow(), page.getPageSize());
		List<Test> list =  testMapper.selectByExample(null);
		//用PageInfo对结果进行包装
		PageInfo<Test> result = new PageInfo<Test>(list);
		return result;
	}

}

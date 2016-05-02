package org.youth.imovie.service;

import org.youth.imovie.pojo.entity.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface TestService {

	PageInfo<Test> findAll(Page<Test> page);
	
}

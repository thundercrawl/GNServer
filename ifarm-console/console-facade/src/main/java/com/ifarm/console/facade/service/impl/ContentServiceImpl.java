package com.ifarm.console.facade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifarm.console.facade.service.IContentService;
import com.ifarm.console.mapper.ContentMapper;
import com.ifarm.console.shared.domain.po.ContentPo;

@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	ContentMapper cmaper;
	@Override
	public List<ContentPo> getAllContents() {
		// TODO Auto-generated method stub
		return cmaper.getAll();
		
	}

}

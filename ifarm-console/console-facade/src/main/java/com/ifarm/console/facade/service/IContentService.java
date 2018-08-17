package com.ifarm.console.facade.service;

import java.util.List;

import com.ifarm.console.shared.domain.po.ContentPo;

public interface IContentService {
	List<ContentPo> getAllContents();
}

package com.ifarm.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import com.ifarm.console.shared.domain.po.ContentPo;

public interface ContentMapper {
	List<ContentPo> getAll();
	ContentPo getById(String id);
}

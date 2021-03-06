package kr.ssulsday.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.CardInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;

@Mapper
public interface CardInfoMapper extends CmBaseMapper<CardInfoVO, SearchPageVO> {

	List<?> selectListData(SearchPageVO schVO);

	int selectListCount(SearchPageVO schVO);

	int updateViewCount(CardInfoVO cardvo);

	int updateCount(@Param("post_id") int post_id, @Param("count") int count, @Param("flag") String flag);
	
	List<?> selectListDataByRange(@Param("latitude") double latitude, @Param("longitude") double longitude);
	
	CardInfoVO selectData(int post_id);	
	
}

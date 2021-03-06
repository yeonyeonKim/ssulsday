package kr.cubex.comm.vo;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.ssulsday.cms.vo.UserInfoVO;

public class PagingListVO {
	//
	public static final String ATTR_RESULT_LIST = "resultList";

	private SearchPageVO searchPage = null;
	private PaginationInfo pagingInfo = new PaginationInfo();;

	private List<?> items;

	//
	public PagingListVO(SearchPageVO schPage) {
		this.searchPage = schPage;

		doPageData();
		//
		// @Resource(name = "propertiesService")
		// protected EgovPropertyService propertiesService;
		// pageVO.setPageUnit(propertiesService.getInt("pageUnit"));
		// pageVO.setPageSize(propertiesService.getInt("pageSize"));
	}
	public SearchPageVO getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(SearchPageVO schPage) {
		this.searchPage = schPage;
	}

	public PaginationInfo getPagingInfo() {
		return pagingInfo;
	}

	public void setPagingInfo(PaginationInfo info) {
		this.pagingInfo = info;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	//
	private void doPageData() {
		if (this.searchPage == null)
			this.searchPage = new SearchPageVO();

		pagingInfo.setCurrentPageNo(searchPage.getPageIndex());
		pagingInfo.setRecordCountPerPage(searchPage.getRecordCountPerPage());
		pagingInfo.setPageSize(searchPage.getPageSize());

		searchPage.setFirstIndex(pagingInfo.getFirstRecordIndex());
		searchPage.setLastIndex(pagingInfo.getLastRecordIndex());
	}

	//
	public void setTotalCount(int nTotCnt) {
		pagingInfo.setTotalRecordCount(nTotCnt);
	}

	//
	public void setTotalCount(List<?> totCnt) {
	}
}

package kr.ssulsday.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.CardInfoService;
import kr.ssulsday.cms.service.CommentInfoService;
import kr.ssulsday.cms.service.PostInfoService;
import kr.ssulsday.cms.vo.CommentInfoVO;

@Controller
@RequestMapping(value = "/cms/comment")
public class CommentInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CommentInfoController.class);

	@Resource
	private CommentInfoService CommentInfoService;
	@Resource
	private PostInfoService PostInfoService;
	@Resource
	private CardInfoService CardInfoService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/list.do/{post_id}", method=RequestMethod.GET)
	public @ResponseBody List<?> 
	commentListForm(@PathVariable int post_id, @ModelAttribute("searchVO") SearchPageVO searchVO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());

		searchVO.setPost_id(post_id);
		PagingListVO listVO = CommentInfoService.selectListPage(searchVO);
		
		logger.info(listVO.getItems().toString());

		return listVO.getItems();

	}

	@RequestMapping(value = "/reg_action.do",method = RequestMethod.POST)
	public @ResponseBody BaseResult commentCreateAction(@RequestBody CommentInfoVO commentvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO = new ResultData();

		try {
			SearchPageVO searchVO = new SearchPageVO();
			searchVO.setPost_id(commentvo.getPost_id());
			CommentInfoService.insertData(commentvo);
			PostInfoService.updateCount(commentvo.getPost_id(), CommentInfoService.selectListCount(searchVO), "C");
			CardInfoService.updateCount(commentvo.getPost_id(), CommentInfoService.selectListCount(searchVO), "C");
			resVO.setRetCode(ResultData.RET_OK, messageSource);
		} catch (DataAccessException e) {
			if (DbUtils.getErrorCode(e) == DbUtils.ERR_DB_DUPLICATE_KEY) {
				resVO.setRetCode(ResultData.ERR_DB_DUPLICATE_KEY, messageSource);
			} else {
				resVO.setRetCode(ResultData.ERR_RESULT_FAIL, messageSource);
			}
			logger.error(request.getServletPath() + ", Insert Error => " + e.getMessage());
		}

		return resVO;
	}

	@RequestMapping(value="/edit_action.do",method = RequestMethod.PUT)
	public @ResponseBody BaseResult commentEditAction(@RequestBody CommentInfoVO commentvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		try {
			if (CommentInfoService.updateData(commentvo) > 0)
				nRetCode = ResultData.RET_OK;
		} catch (DataAccessException e) {
			nRetCode = ResultData.ERR_RESULT_FAIL;
			logger.error(request.getServletPath() + ", Update Error => " + e.getMessage());
		}

		return ResultData.create(nRetCode);
	}

	@RequestMapping(value="/del_action.do",method = RequestMethod.DELETE)
	public @ResponseBody BaseResult commentDelAction(@RequestBody CommentInfoVO commentvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		if (CommentInfoService.deleteData(commentvo) > 0)
			nRetCode = ResultData.RET_OK;

		return ResultData.create(nRetCode);
	}

}

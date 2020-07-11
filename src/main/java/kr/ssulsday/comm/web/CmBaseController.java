package kr.ssulsday.comm.web;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import kr.cubex.utils.ComStr;

import egovframework.rte.fdl.property.EgovPropertyService;

public class CmBaseController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(CmBaseController.class);

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	protected HttpSession	httpSession;
	
	@Autowired
	private	HttpServletRequest request;


	protected boolean isEmptyParameter(Map<String, Object> params, String name) {
		return ComStr.isEmpty(params.get(name));
	}
	protected boolean isEmptyParameters(Map<String, Object> params, String ... names) {
		for ( String s : names ) {
			if (ComStr.isEmpty(params.get(s))) {
				return true;
			}
		}
		return false;
	}
	protected void DumpParameters(Map<String, Object> params) {
		Set<String>	keys = params.keySet();
		Iterator<String>	iter = keys.iterator();
		int					paramCnt = 0;
		System.out.println("PARAM ==> {");
		while ( iter.hasNext() ) {
			String	key = iter.next();
			System.out.println("      [" + key + "]=[" + params.get(key) + "]");
			paramCnt ++;
		}
		System.out.println("} = " + paramCnt);
	}

	public String getStr(Map<String, Object> params, String key) {
		return ComStr.toStr(params.get(key));
	}
	public int getInt(Map<String, Object> params, String key) {
		return ComStr.toInt(params.get(key));
	}
	protected String makeAlertMsg_HistoryBack(Model model, String sPopMsg) {
		return "/cmmn/alertmsg";
	}
	public String getWebHostName() {
		String	sHostName = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() == 80) {
			return sHostName;
		}
		sHostName += ":" + request.getServerPort();
		return sHostName;
	}
}

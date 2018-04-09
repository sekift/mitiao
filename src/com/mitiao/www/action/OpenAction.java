package com.mitiao.www.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitiao.www.Constants;
import com.mitiao.www.dao.OpenDao;
import com.mitiao.www.util.StringUtil;
import com.mitiao.www.vo.ContentVO;
import com.mitiao.www.vo.PageInfo;

/**
 * @version:
 */
@Controller
@RequestMapping("/open")
public class OpenAction {
	public static Logger log = Logger.getLogger(OpenAction.class);

	@Autowired
	private OpenDao dao;

	/**
	 * 刚打开
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String encodeGet(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model)
			throws IOException {
		PageInfo pageInfo = new PageInfo();
		List<Map<String, Object>> list = dao.search("", pageInfo);
		if (null == list) {
			model.put("error", "后台错误，请稍后再试。");
			return "open";
		}
		model.put("pageInfo", pageInfo);
		model.put("list", list);
		return "open";
	}

	/**
	 * 保存内容
	 * @param content
	 * @param day
	 * @param keyword
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String encodePost(@RequestParam(value = "content", defaultValue = "", required = true) String content,
			@RequestParam(value = "dtime", defaultValue = "", required = false) String dtime,
			@RequestParam(value = "title", defaultValue = "", required = false) String title,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {

		if ("".equals(content.trim())) {
			model.put("error", "对不起，输入内容不能为空。");
			return "open";
		}else if(content.trim().length() > 5000){
			model.put("error", "对不起，输入内容不能大于5000字。");
			return "open";
		}

		model.put("dtime", dtime);
		model.put("content", content);
		model.put("title", title);
		// 0.日期为不填就默认2000年，前面提供可选择有1,60,1440,43200,15768000,157680000分钟
		int addTime = 0;
		try{
			if(StringUtil.isNullOrBlank(dtime)){
				addTime = 15768000 * 100;
			}else{
			    addTime = Integer.valueOf(dtime);
			}
		}catch(Exception e){
			log.error("分钟数转换错误",e);
			model.put("error", "后台错误，请稍后再试。");
			return "open";
		}
		
		ContentVO vo = new ContentVO();
		vo.setHashNoteId(title);
		vo.setContent(content);
		vo.setStatus(Constants.status.UNUSER);
		vo.setMemo("");
		// 4.压入数据库
		boolean result = dao.writeContent(vo, addTime);

		if (!result) {
			model.put("error", "后台错误，请稍后再试。");
			return "open";
		}
		return encodeGet(request, response, model);
	}
	
	/**
	 * 搜索内容
	 * @param keyword
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchPost(
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			PageInfo pageInfo,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {
		List<Map<String, Object>> list = dao.search(keyword, pageInfo);
		model.put("keyword", keyword);
		if (null == list) {
			model.put("error", "后台错误，请稍后再试。");
			return "open";
		}
		model.put("list", list);
		return "open";
	}
	
}

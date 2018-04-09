package com.mitiao.www.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitiao.www.Constants;
import com.mitiao.www.dao.MitiaoDao;
import com.mitiao.www.encrypt.AESCoder;
import com.mitiao.www.encrypt.SHA1Coder;
import com.mitiao.www.util.DateUtil;
import com.mitiao.www.util.RandomUtil;
import com.mitiao.www.util.XssUtil;
import com.mitiao.www.vo.ContentVO;

/**
 * 
 * @author:sekift
 * @time:2016-7-27 上午10:15:30
 * @version:
 */
@Controller
@RequestMapping("/")
public class MitiaoAction {
	@Autowired
	private MitiaoDao dao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String encodeGet(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model)
			throws IOException {
		
		return "encode";
	}
	
	@RequestMapping(value = "/test/test", method = RequestMethod.GET)
	public String testGet(
			@RequestParam(value = "content", defaultValue = "", required = true) String content,
			@RequestParam(value = "uid", defaultValue = "", required = true) String uid,
			@RequestParam(value = "num", defaultValue = "0", required = true) int num,
			@RequestParam(value = "count", defaultValue = "0", required = true) int count,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model)
			throws IOException {
		if(count == 0 || num == 0){
			System.out.println(num+" "+count);
		}
		StringBuilder sb = new StringBuilder("begin");
		while(num < count){
			System.out.println(num+" "+count);
			sb.append(content);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(num+" "+count +" "+sb.toString());
		try {
			Thread.sleep(20*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "encode";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String encodePost(@RequestParam(value = "content", defaultValue = "", required = true) String content,
			@RequestParam(value = "dday", defaultValue = "", required = false) String day,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {

		if ("".equals(content.trim())) {
			model.put("error", "对不起，输入内容不能为空。");
			return "encode";
		}else if(content.trim().length() > 5000){
			model.put("error", "对不起，输入内容不能大于5000字。");
			return "encode";
		}

		model.put("dday", day);
		model.put("content", content);
		model.put("keyword", keyword);
		// 0.日期为不填就默认2000年，前面提供可选择有1,3,7,30,365,1825,365*1000天
		int addDay = 2000 * 365;
		if (!"".equals(day)) {
			try {
				addDay = Integer.valueOf(day);
			} catch (Exception e) {
				model.put("error", "天数时间输入错误，请输入正整数。");
				return "encode";
			}
		}
		ContentVO vo = new ContentVO();

		Date currentDate = new Date();
		Date availableDate = DateUtil.addDate("dd", addDay, currentDate);
		vo.setCreateTime(currentDate);
		vo.setUpdateTime(currentDate);
		vo.setAvailableTime(availableDate);

		// 1.首先分配16位的noteId(keyword自定义)
		String noteId = null;
		
		if("".equals(keyword.trim())){
			noteId = RandomUtil.getRandomWithNumberAndChar(16);
		}
		
		if(!"".equals(keyword.trim()) && (keyword.trim().length() < 6 || keyword.trim().length() > 15
				|| !keyword.trim().matches(Constants.regex_id.ALPHABETNUMBER))){
			model.put("error", "自定义秘钥必须是6-15位的数字字母组合，请更换。");
			return "encode";
		}else if(!"".equals(keyword.trim())){
			noteId = keyword.trim();
		}
		
		// 2.再将noteId经过sha1转成hashNoteId,若有则返回error，请更换
		String hashNoteId = SHA1Coder.encodeBySha1(noteId);
		if(dao.checkHashNoteId(hashNoteId)){
			model.put("error", "自定义秘钥已存在，请更换。");
			return "encode";
		}
		
		// 3.将content用AES加密
		byte[] encryptData = null;
		String contentData = null;
		try {
			encryptData = AESCoder.encrypt(content.getBytes(), noteId);
			contentData = AESCoder.parseByte2HexStr(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		vo.setHashNoteId(hashNoteId);
		vo.setContent(contentData);
		vo.setStatus(Constants.status.UNUSER);
		vo.setMemo("");
		// 4.压入数据库
		boolean result = dao.writeContent(vo);

		if (!result) {
			model.put("error", "后台错误，请稍后再试。");
			return "encode";
		}
		model.put("noteId", noteId);
		return "message";
	}

	@RequestMapping(value = "{noteId}", method = RequestMethod.GET)
	public String decodeGet(@PathVariable String noteId, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws IOException {

		if (null == noteId || "".equals(noteId.trim())) {
			model.put("error", "对不起，提取秘钥不能为空。");
			return "decode";
		}
		// 1.再将noteId经过sha1转成hashNoteId
		String hashNoteId = SHA1Coder.encodeBySha1(noteId);
		// 2.去数据库拿数据，判断时间
		ContentVO vo = dao.getContent(hashNoteId);

		if (null == vo || null == vo.getContent()) {
			model.put("error", "密条" + noteId + "不存在，请检查。");
			return "decode";
		}

		// 2.5 判断状态 -1
		if (vo.getStatus() == Constants.status.USERD) {
			model.put("error", "此密条在" + vo.getUpdateTime() + "时已经阅读并销毁，请确认。");
			return "decode";
		}

		Date currentDate = new Date();
		Date availableDate = DateUtil.addDate("yy", 1001, currentDate);
		// 3 判断时间
		if (vo.getAvailableTime().getTime() <= System.currentTimeMillis()) {
			model.put("error", "此密条在" + vo.getAvailableTime() + "时已经过期并被销毁。");
			return "decode";
		}

		// 4.拿出来后再使用noteId解密
		String contentData = vo.getContent();
		byte[] encryptData2 = AESCoder.parseHexStr2Byte(contentData);
		byte[] decryptData = null;
		try {
			decryptData = AESCoder.decrypt(encryptData2, noteId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (vo.getAvailableTime().getTime() > System.currentTimeMillis()
				&& vo.getAvailableTime().getTime() < availableDate.getTime()) {
			model.put("noteId", noteId);
			model.put("contentData", XssUtil.cleanXSS(new String(decryptData)));//XssUtil.cleanXSS()
			model.put("availableTime", vo.getAvailableTime());
			return "decode";
		}

		// 5.改变状态
		boolean result = dao.deleteContent(hashNoteId, Constants.status.USERD);
		if (!result) {
			model.put("error", "后台错误，请稍后再试。");
			return "decode";
		}
		model.put("noteId", noteId);
		model.put("contentData", XssUtil.cleanXSS(new String(decryptData)));
		return "decode";
	}

	@RequestMapping(value = "{noteId}", method = RequestMethod.DELETE)
	public String decodeDelete(@PathVariable String noteId,
			@RequestParam(value = "destroy", defaultValue = "", required = false) String destroy,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws IOException {
		if ("".equals(noteId.trim())) {
			model.put("error", "对不起，秘钥不能为空。");
			return "decode";
		}

		String hashNoteId = SHA1Coder.encodeBySha1(noteId);
		boolean result = false;
		boolean type = false;
		if (null == destroy || "".equals(destroy)) {
			result = dao.deleteContent(hashNoteId, Constants.status.USERD);
		} else if ("1".equals(destroy)) {
			type = true;
			result = dao.deleteContent(hashNoteId);
		}
		if (!result) {
			model.put("error", "后台错误，请稍后再试。");
			return "decode";
		}
		if(type){
			model.put("error", "密条" + noteId + "的内容已彻底销毁成功。");
		}else{
		    model.put("error", "密条" + noteId + "的内容已销毁成功。");
		}
		return "encode";
	}
}

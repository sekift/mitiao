package com.mitiao.www.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mitiao.www.encrypt.SHA1Coder;
import com.mitiao.www.util.Data2PageUtil;
import com.mitiao.www.util.StringUtil;
import com.mitiao.www.vo.ContentVO;
import com.mitiao.www.vo.PageInfo;

/**
 * 操作开放数据库的DAO层
 * 
 * @author:sekift
 * @time:2016-7-26 下午04:15:30
 */
@Component
public class OpenDao {
	private static Logger logger = LoggerFactory.getLogger(OpenDao.class);

	// 主库
	private static String aliasMaster = "mitiao_master";
	
	// 从库
	private static String aliasSlave = "mitiao_slave";
	
	/**
	 * 查询
	 * @param keyword
	 * @return
	 */
	public List<Map<String, Object>> search(String keyword, PageInfo pageInfo){
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sb.append("SELECT * FROM open WHERE availableTime > now()");
		if(!StringUtil.isNullOrBlank(keyword)){
			sb.append(" AND (hashNoteId like ? OR content like ?)");
			params.add("%"+keyword+"%");
		}
		sb.append(" ORDER BY createTime DESC");

		List<Map<String, Object>> list = null;
		try {
			list = Data2PageUtil.queryQuietly(aliasSlave, pageInfo, sb.toString(), new MapListHandler(), params.toArray());
		} catch (Exception e) {
			logger.debug("[后台]分页查找任务信息失败!", e);
		}
		return list;
	}

	public boolean writeContent(ContentVO vo,int addTime) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO open(hashNoteId,content,createTime,updateTime,availableTime,status,memo)"
				+ " VALUES(?,?,now(),now(),date_add(now(), interval ? minute),?,?) ";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getHashNoteId());
		params.add(vo.getContent());
		params.add(addTime);
		params.add(vo.getStatus());
		params.add(vo.getMemo());

		try {
			result = DBOperate.update(aliasMaster, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("插入数据出错", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public ContentVO getContent(String hashNoteId) {
		String sql = "SELECT * FROM open WHERE hashNoteId=?";
		ContentVO result = (ContentVO) DBOperate.queryQuietly(aliasSlave, sql, new BeanHandler(ContentVO.class), hashNoteId);
		return result;
	}
	
	public boolean checkHashNoteId(String hashNoteId) {
		String sql = "SELECT hashNoteId FROM open WHERE hashNoteId=?";
		String result = (String)DBOperate.queryQuietly(aliasSlave, sql,new ScalarHandler(), hashNoteId);
		if(null != result){
			return true;
		}
		return false;
	}
	
	public boolean updateContent(String hashNoteId){
		return false;
	}
	
	/**
	 * 删除
	 * @param hashNoteId
	 * @param status -1  阅读销毁
	 * @return
	 */
	public boolean deleteContent(String hashNoteId, int status){
		boolean result = false;
		if (null == hashNoteId || status > 0) {
			return result;
		}
		String sql = "UPDATE open SET status=?,updateTime=now() WHERE hashNoteId=?";
		try {
			result = DBOperate.update(aliasMaster, sql, status, hashNoteId) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("删除数据出错", e);
		}
		return result;
	}
	
	/**
	 * 物理删除
	 * @param hashNoteId
	 * @param status -1  阅读销毁
	 * @return
	 */
	public boolean deleteContent(String hashNoteId){
		boolean result = false;
		if (null == hashNoteId) {
			return result;
		}
		String sql = "DELETE FROM open WHERE hashNoteId=?";
		try {
			result = DBOperate.update(aliasMaster, sql, hashNoteId) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("物理删除数据出错", e);
		}
		return result;
	}

	public static void main(String args[]) {
		OpenDao dao = new OpenDao();
		ContentVO vo = new ContentVO();
//		String key = RandomUtil.getRandomWithNumberAndChar(16);
		String key = "12345fe";
//		System.out.println(key);
		vo.setHashNoteId(SHA1Coder.encodeBySha1(key));
		vo.setContent("1413");
		vo.setAvailableTime(new Date());
		vo.setStatus(1);
		vo.setMemo("");
//		System.out.println(vo);
		System.out.println(dao.writeContent(vo, 100));
//		System.out.println(dao.getContent("8CB2237D0679CA88DB6464EAC60DA96345513964"));
		System.out.println(dao.search("4", new PageInfo()));
	}
}

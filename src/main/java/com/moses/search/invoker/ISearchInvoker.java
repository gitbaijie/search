package com.moses.search.invoker;

import java.util.List;

import com.moses.search.pojo.SearchDetail;
import com.moses.search.pojo.SearchItem;

public interface ISearchInvoker {
	
	/** 
	* @Title: getList 
	* @Description: 搜索列表
	* @param @param url
	* @param @return
	* @return List<SearchList>    返回类型 
	* @throws 
	*/
	public List<SearchItem> getList(String url);
	
	/** 
	* @Title: getDetail 
	* @Description: 查询明细页
	* @param @param url
	* @param @return
	* @return List<SearchDetail>    返回类型 
	* @throws 
	*/
	public List<SearchDetail> getDetail(String url);

}

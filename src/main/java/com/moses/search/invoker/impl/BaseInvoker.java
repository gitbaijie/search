package com.moses.search.invoker.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.moses.search.invoker.ISearchInvoker;

/** 
* @ClassName: BaseInvoker 
* @Description: search 实现基类
* @author 白杰
* @date 2017年5月30日 下午5:36:38 
*  
*/
public abstract class BaseInvoker implements ISearchInvoker {
	
	/** 
	* @Title: getInvokerName 
	* @Description: 获取实现名称
	* @param @return
	* @return String    返回类型 
	* @throws 
	*/
	public abstract String getInvokerName();
	
	/** 
	* @Title: getDocument 
	* @Description: 抓取页面，获取doc
	* @param @param url
	* @param @return
	* @param @throws IOException
	* @return Document    返回类型 
	* @throws 
	*/
	Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
	}
	
}

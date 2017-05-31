package com.moses.search.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.moses.search.command.ISearchCommand;
import com.moses.search.invoker.ISearchInvoker;
import com.moses.search.pojo.SearchItem;

public class SearchCommandImpl implements ISearchCommand {

	public SearchCommandImpl(ISearchInvoker invoker, String searchUrl) {
		super();
		this.invoker = invoker;
		this.searchUrl = searchUrl;
	}

	@Override
	public void exe() {
		/* 搜索列表 */
		List<SearchItem> list = invoker.getList(searchUrl);
		for (int i = 0; i < list.size(); i++) {
			log.debug(i + ": " + list.get(i));
		}
	}

	private static Logger log = Logger.getLogger(SearchCommandImpl.class);

	/** search 实现 */
	private ISearchInvoker invoker;

	/** 要search的url */
	private String searchUrl;

}

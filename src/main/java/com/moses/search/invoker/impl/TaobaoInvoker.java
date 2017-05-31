package com.moses.search.invoker.impl;

import java.io.IOException;
import java.util.List;

import com.moses.search.pojo.SearchDetail;
import com.moses.search.pojo.SearchItem;

public class TaobaoInvoker extends BaseInvoker {

	@Override
	public List<SearchItem> getList(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SearchDetail> getDetail(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvokerName() {
		return "淘宝";
	}
	
	public static void main(String[] args) throws IOException {
		String url = "https://detail.tmall.com/item.htm?id=42048811061&ali_refid=a3_430583_1006:1110095938:N:%E4%B8%8A%E6%B5%B7%E9%B2%9C%E8%8A%B1%E5%90%8C%E5%9F%8E%E9%80%9F%E9%80%92%E7%94%9F%E6%97%A5:986c469276af6c961aaa7b396fbbe73a&ali_trackid=1_986c469276af6c961aaa7b396fbbe73a&spm=a230r.1.14.1.Utn9s5";
		System.out.println(new TaobaoInvoker().getDocument(url));
	}

}


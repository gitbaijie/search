package com.moses.pricecheck;

import java.util.List;

public interface ProductList {

	/**
	 * 爬取商品列表
	 * @return
	 */
	public List<ProductInfo> getProductList();
	
}

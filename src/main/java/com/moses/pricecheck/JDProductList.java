package com.moses.pricecheck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JDProductList implements ProductList {

	private String jdUrl;

	private String productName;

	private static PriceCheckUtil pcu = PriceCheckUtil.getInstance();

	public JDProductList(String jdUrl, String productName) {
		this.jdUrl = jdUrl;
		this.productName = productName;
	}


	@Override
	public List<ProductInfo> getProductList() {
		List<ProductInfo> jdProductList = new ArrayList<ProductInfo>();
		ProductInfo productInfo = null;
		String url = "";
		for (int i = 0; i < 15; i++) {
			try {
				System.out.println("JD Product 第[" + (i + 1) + "]页");
				if (i == 0) {
					url = jdUrl;
				} else {
					url = Constants.JDURL + pcu.getUrlCode(productName) + Constants.JDENC + Constants.JDPAGE
							+ (i * 2 + 1);
				}
				System.out.println(url);
				// Document document = Jsoup.connect(url).timeout(5000).get();
				Document doc = Jsoup.parse(pcu.getXmlByHtmlunit(url));
				Elements divs = doc.select("div[class=gl-i-wrap]");
				Iterator<Element> divIter = divs.iterator();
				System.out.println("size: " + divs.size());
				while (divIter.hasNext()) {
					Element li = divIter.next();
					productInfo = new ProductInfo();
					/* 名称 */
					Elements nameE = li.select("div[class=p-name p-name-type-2]>a");
					String name = nameE.attr("title");
					productInfo.setProductName(name);

					/* 价格 */
					Elements priceE = li.select("div[class=p-price]>strong");
					String price = priceE.text();
					productInfo.setProductPrice(price);

					/* url */
					Elements productE = li.select("div[class=p-img]>a");
					String productUrl = productE.attr("href").indexOf("http") > -1 ? productE.attr("href")
							: "https:" + productE.attr("href");
					productInfo.setProductUrl(productUrl);

					/* 网店信息 */
					Elements shopE = li.select("span[class=J_im_icon]>a");
					productInfo.setShopName(shopE.text());
					productInfo.setShopUrl(shopE.attr("href").indexOf("http") > -1 ? shopE.attr("href")
							: "https:" + shopE.attr("href"));

					jdProductList.add(productInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Get JD product has error [" + url + "]");
			}
		}
		return jdProductList;
	}

	public static void main(String[] args) {
		try {
			String productName = "可利鲜";
			String jdUrl = Constants.JDURL + pcu.getUrlCode(productName) + Constants.JDENC;
			List<ProductInfo> list = new JDProductList(jdUrl, productName).getProductList();
			System.out.println("list size: " + list.size());
			for (int i = 0; i < list.size(); i++) {
				ProductInfo pi = list.get(i);
				System.out.println((i + 1) + ": " + pi.getProductUrl() + " " + pi.getProductName() + "  "
						+ pi.getProductPrice() + " " + pi.getShopName() + " " + pi.getShopUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

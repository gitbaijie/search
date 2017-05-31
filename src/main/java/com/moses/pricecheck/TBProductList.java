package com.moses.pricecheck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TBProductList implements ProductList {

	private static PriceCheckUtil pcu = PriceCheckUtil.getInstance();

	private String tbUrl;

	private String productName;

	public TBProductList(String tbUrl, String productName) {
		this.tbUrl = tbUrl;
		this.productName = productName;
	}

	@Override
	public List<ProductInfo> getProductList() {
		List<ProductInfo> tbProductList = new ArrayList<ProductInfo>();
		String url = "";
		int pages = 1;
		int page = 0;
		for (int i = 0; i < pages; i++) {
			try {
				System.out.println("TB Product 第[" + (i + 1) + "]页");
				if (i == 0) {
					url = tbUrl;
				} else {
					page += 44;
					url = Constants.TBURL + pcu.getUrlCode(productName) + Constants.TBPAGE + page;
				}
				System.out.println(url);
				Document doc = Jsoup.parse(pcu.getXmlByHtmlunit(url));
				Elements itemlist = doc.select("div[class=m-itemlist]");
				Iterator<Element> it = itemlist.iterator();
				while (it.hasNext()) {
					Element item = it.next();
					Elements items = item.select("div[data-category=auctions]");
					System.out.println(items.size());
					Iterator<Element> one = items.iterator();
					while (one.hasNext()) {
						Element e = one.next();

						Elements urlE = e.select("div[class=row row-2 title]>a");
						String productUrl = urlE.attr("href").indexOf("http") > -1 ? urlE.attr("href")
								: "https:" + urlE.attr("href");

						// Elements price = e.select("div[class=price g_price
						// g_price-highlight]>strong");
						// String productPrice = price.text();
						// Elements title = e.select("div[class=row row-2
						// title]>a");
						// String productName = title.text();
						// productInfo = new ProductInfo();
						// productInfo.setProductName(productName);
						// productInfo.setProductPrice(productPrice);
						ProductInfo productInfo = null;
						if (productUrl.indexOf("tmall") > -1) {
							/* 天猫 */
							productInfo = getTMProductInfo(productUrl);
						} else {
							/* 淘宝 */
							productInfo = getTBProductInfo(productUrl);
						}
						tbProductList.add(productInfo);
					}

				}
			} catch (Exception e) {
				System.out.println("Get TB product has error");
				System.out.println(e.getMessage());
			}
		}

		return tbProductList;
	}

	/** 
	* @Title: getTMProductInfo 
	* @Description: 天猫明细
	* @param @param productUrl
	* @param @return
	* @param @throws Exception
	* @return ProductInfo    返回类型 
	* @throws 
	*/
	private ProductInfo getTMProductInfo(String productUrl) throws Exception {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductUrl(productUrl);
		Document doc = Jsoup.parse(pcu.getXmlByHtmlunit(productUrl));
		System.out.println(doc);
		/* 商品网店 */
		Elements shopE = doc.select("a[class=slogo-shopname]");
		String shopUrl = shopE.attr("href").indexOf("http") > -1 ? shopE.attr("href") : "https:" + shopE.attr("href");
		productInfo.setShopName(shopE.select("strong").text()); // 网店名称
		productInfo.setShopUrl(shopUrl); // 网店url
		
		/* 商品名称 */
		Elements productNameE = doc.select("div[class=tb-detail-hd]>h1");
		productInfo.setProductName(productNameE.text().trim());

		/* 价格 */
		Elements priceE = doc.select("dl[class=tm-promo-panel tm-promo-cur]");
		String yen = priceE.select("em[class=tm-yen]").text().trim();
		String place = priceE.select("span[class=tm-price]").text().trim();
		productInfo.setProductPrice(yen + place);

		return productInfo;
	}

	/** 
	* @Title: getTBProductInfo 
	* @Description: 淘宝明细
	* @param @param productUrl
	* @param @return
	* @param @throws Exception
	* @return ProductInfo    返回类型 
	* @throws 
	*/
	private ProductInfo getTBProductInfo(String productUrl) throws Exception {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductUrl(productUrl);
		Document doc = Jsoup.parse(pcu.getXmlByHtmlunit(productUrl));
		System.out.println(doc);
		/* 商品网店 */
		Elements shopE = doc.select("a[class=slogo-shopname]");
		String shopUrl = shopE.attr("href").indexOf("http") > -1 ? shopE.attr("href") : "https:" + shopE.attr("href");
		productInfo.setShopName(shopE.select("strong").text()); // 网店名称
		productInfo.setShopUrl(shopUrl); // 网店url
		
		/* 商品名称 */
		Elements productNameE = doc.select("div[class=tb-detail-hd]>h1");
		productInfo.setProductName(productNameE.text().trim());

		/* 价格 */
		Elements priceE = doc.select("dl[class=tm-promo-panel tm-promo-cur]");
		String yen = priceE.select("em[class=tm-yen]").text().trim();
		String place = priceE.select("span[class=tm-price]").text().trim();
		productInfo.setProductPrice(yen + place);

		return productInfo;
	}

	public static void main(String[] args) {
		try {
			String productName = "可利鲜";
			String tbUrl = Constants.TBURL + pcu.getUrlCode(productName);
			List<ProductInfo> list = new TBProductList(tbUrl, productName).getProductList();
			for (ProductInfo pi : list) {
				System.out.println("[" + pi.getProductName() + "]  [" + pi.getProductPrice() + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

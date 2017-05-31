package com.moses.excel;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.moses.pricecheck.ProductInfo;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlWrite {

	private static String[] cols = { "序号", "商品url", "商品名称", "商品金额", "网店名称", "网店url" };

	public static void writeExcel(List<ProductInfo> list, String storeName, String productName) {
		String day = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd");
		File xlsFile = new File("excel/" + storeName + "_" + productName + "_" + day + ".xls");
		// 创建一个工作簿
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		try {
			workbook = Workbook.createWorkbook(xlsFile);
			// 创建一个工作表
			sheet = workbook.createSheet("sheet1", 0);

			/* 创建列名 */
			int row = 0;
			for (int i = 0; i < cols.length; i++) {
				sheet.addCell(new Label(i, row, cols[i]));
			}

			/* 填充内容 */
			for (int i = 0; i < list.size(); i++) {
				row++;
				ProductInfo pi = list.get(i);
				int col = 0;
				sheet.addCell(new Label(col++, row, row + "")); // 序号
				sheet.addCell(new Label(col++, row, pi.getProductUrl())); // 商品url
				sheet.addCell(new Label(col++, row, pi.getProductName())); // 商品名称
				sheet.addCell(new Label(col++, row, pi.getProductPrice())); // 商品金额
				sheet.addCell(new Label(col++, row, pi.getShopName())); // 网店名称
				sheet.addCell(new Label(col++, row, pi.getShopUrl())); // 网店url
			}
			workbook.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("生成" + xlsFile.getName() + "失败");
			xlsFile.delete();
		} finally {
			try {
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

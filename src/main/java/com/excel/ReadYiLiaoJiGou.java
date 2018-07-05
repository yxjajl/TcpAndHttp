package com.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 读取有奖问答小程序医了机构excel中的文本
 * @author r6yuxx
 *
 */
public class ReadYiLiaoJiGou {
	public static void main(String[] args) throws Exception {
		String excelFile = "C:\\Users\\r6yuxx\\Desktop\\test.xls";
		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(excelFile);
		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);
		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(1);
		Cell cell1 = null;
		Cell cell2 = null;
		List<YiLiaoJiGou> tt = new ArrayList<>();
		YiLiaoJiGou vo = null;
		//System.out.println(sheet.getColumns() + "," + sheet.getRows());
		for (int i = 0; i < sheet.getRows(); i++) {
			cell1 = sheet.getCell(0, i);
			cell2 = sheet.getCell(1, i);
			String tmp = cell1.getContents();
			if (StringUtils.isNotBlank(tmp)) {
				vo = new YiLiaoJiGou();
				tt.add(vo);
				vo.setProvince(tmp);
			}
			if (vo != null) {
				vo.getList().add(cell2.getContents());
			} else {
				System.out.println("==========");
			}
			
		}
		System.out.println(JSON.toJSONString(tt));
	}
}

class YiLiaoJiGou {
	private String province;
	private List<String> list = new ArrayList<>();

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}

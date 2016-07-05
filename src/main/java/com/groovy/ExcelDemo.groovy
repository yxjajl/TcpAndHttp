package com.groovy

import java.io.FileInputStream;
import java.io.InputStream

import org.apache.commons.lang.StringUtils;

import com.excel.vo.CompanyVO;

import jxl.Cell
import jxl.Sheet;
import jxl.Workbook;

class ExcelDemo {
	static main(args) {
		readExcel()
	}
	//jxl暂不支持2007版
	static def readExcel() {
		def fn = "C:\\Users\\r6yuxx\\Desktop\\tmp\\test2003.xls";
		// 获取Excel文件对象
		def rwb = Workbook.getWorkbook(new FileInputStream(fn));
		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);
		def cell;

		//		println sheet.getColumns()  //列
		//		println sheet.getRows() //行

		//		println sheet.getCell(1, 1).getContents()
		//		println sheet.getCell(2, 1).getContents()

		int startRow = 2;//起始行 excel默认从0开始
		int startCol = 1; //起始列excel默认从0开始

		for(int row:startRow..<sheet.getRows()) {
			for(int col:startCol..<sheet.getColumns()) {
				cell = sheet.getCell(col, row)
				//print cell.getContents() + ","
				print cell.getCellFormat()
			}
			println ""
		}


		//		0.upto(sheet.getRows()-1) { row ->
		//			0.upto(sheet.getColumns()-1) { col->
		//				//println col+","+ row
		//				print sheet.getCell(col, row).getContents()+ ","
		//			}
		//			println ""
		//		}


	}
}

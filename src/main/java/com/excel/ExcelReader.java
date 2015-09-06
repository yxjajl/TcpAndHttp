package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Image;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excel.service.CompanyService;
import com.excel.service.ConfigConstants;
import com.excel.up.FileClient;
import com.excel.vo.CompanyVO;
import com.google.common.base.Splitter;

@Component
public class ExcelReader {
	@Autowired
	private CompanyService companyService;

	private HashMap<Integer, File> picMap = new HashMap<Integer, File>();

	public void read() throws Exception {
		companyService.initConfig();
		System.out.println("+====config end=========+");
		String excelFileName = "D:\\ExcelDemo.xls";
		String updateField = "tag";
		boolean isUpdateField = false;
		try {

			if (updateField != null && updateField.trim().length() > 0) {
				isUpdateField = true;
			}

			if (!isUpdateField) {
				readImg(new File(excelFileName), 1);
			}
			List<CompanyVO> list = ExcelReader.readExcel(new File(excelFileName), 2);

			if (!isUpdateField) {
				List<CompanyVO> remove = new ArrayList<CompanyVO>();
				for (CompanyVO companyVO : list) {
					if (companyService.exist(companyVO.getName())) {
						remove.add(companyVO);
					}
				}

				for (CompanyVO companyVO : remove) {
					list.remove(companyVO);
				}
			}

			for (CompanyVO companyVO : list) {
				if (isUpdateField) {
					HashMap<String, Object> map = new HashMap<>();

					for (String fieldName : Splitter.on(' ').trimResults().omitEmptyStrings().splitToList(updateField)) {
						Object value = BeanUtils.getPropertyDescriptor(CompanyVO.class, fieldName).getReadMethod().invoke(companyVO);
						map.put(fieldName, value);
						System.out.println(fieldName + "," + value);
					}

					 companyService.updateField(companyVO.getName(),map);
				} else {
					// companyVO.setLogo(upFile(picMap.get(companyVO.getRow())));
					System.out.println(companyVO.getRow() + "," + companyVO.getLogo() + "," + companyVO.getName());
					// companyService.insertCompany(companyVO);
				}

			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// String excelFileName = "D:\\ExcelDemo.xls";
		// new ExcelReader().readImg(new File(excelFileName), 1);
		// new ExcelReader().readExcel(new File(excelFileName), 2);
	}

	public void readImg(File excelFile, int rowNum) throws Exception {
		// 创建一个list用来存读取的内容
		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(excelFile);
		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);
		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);
		int pics = sheet.getNumberOfImages();
		System.out.println("logo数:" + pics);
		for (int i = 0; i < sheet.getNumberOfImages(); i++) {
			Image image = sheet.getDrawing(i);
			int row = (int) Math.round(image.getRow());
			// System.out.println("" + row + "," + "D:\\pic\\" + row + ".jpg" +
			// "," + image.getRow());
			if (picMap.get(row) != null) {
				System.out.println("===========error ===========");
			}

			File file = new File("D:\\pic\\" + row + ".jpg");
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(image.getImageData());
			fo.close();

			picMap.put(row, file);
		}
		System.out.println("logo数:" + picMap.size());

		if (picMap.size() != pics) {
			throw new Exception("图片数目错误");
		}

		// for (int i = 0; i < sheet.getNumberOfImages(); i++) {
		// System.out.println("logorow:" + sheet.getDrawing(i).getRow());
		// 图片的大小
		// if (sheet.getDrawing(i).getImageData().length > 0) {
		// // 生成图片，结束For 语句
		// // icon = new
		// javax.swing.ImageIcon(sheet.getDrawing(i).getImageData());
		// break;
		// }
		// }
	}

	public String upFile(File file) throws IOException {
		FileClient fc = MyFileUpload.upload(file);

		if (fc.isUploadOk()) {
			return fc.getFilename();
		} else {
			return null;
		}
	}

	public static List<CompanyVO> readExcel(File excelFile, int rowNum) throws BiffException, IOException {

		// 创建一个list用来存读取的内容
		List<CompanyVO> list = new ArrayList<CompanyVO>();
		Workbook rwb = null;
		Cell cell = null;

		// 创建输入流
		InputStream stream = new FileInputStream(excelFile);
		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);
		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);
		// 行数(表头的目录不需要，从1开始)
		for (int i = rowNum - 1; i < sheet.getRows(); i++) {
			// 创建一个数组 用来存储每一列的值
			int maxColumns = 10;
			String[] str = new String[maxColumns]; // sheet.getColumns()
			// 列数
			for (int j = 0; j < maxColumns; j++) {
				// 获取第i行，第j列的值
				cell = sheet.getCell(j, i);
				str[j] = cell.getContents();
			}

			if (StringUtils.isEmpty(str[0]) && StringUtils.isEmpty(str[1]) && StringUtils.isEmpty(str[2])) {
				break;
			}

			CompanyVO cc = toCompany(str);
			cc.setRow(i);
			list.add(cc);
		}

		// System.out.println("logo数:" + sheet.getNumberOfImages());

		// for (int i = 0; i < sheet.getNumberOfImages(); i++) {
		// System.out.println("logorow:" + sheet.getDrawing(i).getRow());
		// 图片的大小
		// if (sheet.getDrawing(i).getImageData().length > 0) {
		// // 生成图片，结束For 语句
		// // icon = new
		// javax.swing.ImageIcon(sheet.getDrawing(i).getImageData());
		// break;
		// }
		// }

		return list;
	}

	public static CompanyVO toCompany(String[] arr) {

		CompanyVO companyVO = new CompanyVO();
		int n = 0;
		companyVO.setIndustry(ConfigConstants.getIndustryID(arr[n++]));
		companyVO.setLogo("");
		n++;
		companyVO.setName(arr[n++]);
		companyVO.setVcompany_size(arr[n++]);
		companyVO.setBusiness_scope(arr[n++]);
		companyVO.setWebsite(arr[n++]);
		companyVO.setAddress(arr[n++]);
		companyVO.setCity(ConfigConstants.getCityID(arr[n++]));
		companyVO.setTag(arr[n++]);
		companyVO.setDetail(arr[n++]);
		companyVO.setShort_name(companyVO.getName());

		return companyVO;
	}
}
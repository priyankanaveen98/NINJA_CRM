package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericutilities.ExcelFileUtility;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		ExcelFileUtility elib=new ExcelFileUtility();
		int rowCount = elib.getRowCount("Practice");
		for(int row=1;row<=rowCount;row++) {
	//		String data = sh.getRow(row).getCell(0).getStringCellValue();
			String data = elib.readDataFromExcelFile("Practice", row, 0);
			System.out.println(data);
		}
	}

}

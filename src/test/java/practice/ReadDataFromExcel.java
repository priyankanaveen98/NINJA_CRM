package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\PRIYANKA\\Advanced Selenium\\Data\\NINJA_CRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("campaigns");
		Row r = sh.getRow(1);
		Cell c = r.getCell(2);
		String campaignName = c.getStringCellValue();
		System.out.println(campaignName);
		String targetSize = wb.getSheet("campaigns").getRow(1).getCell(3).getStringCellValue();
		System.out.println(targetSize);
		wb.close();

	}

}

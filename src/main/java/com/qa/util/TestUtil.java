package com.qa.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qa.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends BaseClass {

    public static long PAGE_LOAD_TIME_OUT = 10;
    public static long IMPLICIT_WAIT = 20;

    public static Workbook book;
    public static Sheet sheet;
    public static JavascriptExecutor js;
    public static String TEST_DATA_SHEET_PATH = "//home//milan.kuilya@wakefit.me//IdeaProjects//PageObjectModel//src//main//java//com//qa//testdata//data.xlsx";
    //public static String sheetName = "contacts";
    public void switchToFrame() {
        driver.switchTo().frame("mainpanel");
    }

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TEST_DATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
          sheet = book.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }

    public static void takeScreenShotAtEndTest() throws IOException {
        File srcFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("screenshot");
        String currentDirectory = System.getProperty("user.dir");
        FileUtils.copyFile(srcFile , new File(currentDirectory+" /screenshots/" + System.currentTimeMillis() +".png"));
    }
}


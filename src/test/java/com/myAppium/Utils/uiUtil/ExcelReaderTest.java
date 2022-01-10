package com.myAppium.Utils.uiUtil;

import com.myAppium.Utils.excel.ExcelReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Disabled
class ExcelReaderTest {
    ExcelReader excelReaderXls = new ExcelReader("C:\\Users\\yangdang\\Desktop\\新建 XLS 工作表 (2).xls");

    ExcelReader excelReaderXlsx = new ExcelReader("C:\\Users\\yangdang\\Desktop\\新建 XLSX 工作表.xlsx");
    @Test
    void getNumberOfSheets() {
        System.out.println(excelReaderXls.getNumberOfSheets());
    }

    @Test
    void getAllSheetNames() {
        System.out.println(excelReaderXls.getAllSheetNames());
    }

    @Test
    void readLine() {
        System.out.println(excelReaderXls.readLine(-500));
        System.out.println(excelReaderXls.readLine(0));
        System.out.println(excelReaderXls.readLine(1));
        System.out.println(excelReaderXls.readLine(2));
        System.out.println(excelReaderXls.readLine(30000));

        System.out.println("******************");
        System.out.println(excelReaderXlsx.readLine(-500));
        System.out.println(excelReaderXlsx.readLine(0));
        System.out.println(excelReaderXlsx.readLine(1));
//        System.out.println(excelReaderXlsx.readLine(30000));
    }

    @Test
    void test1(){
        File file = new File("\\yangdang\\Desktop\\新建 XLSX 工作表`.xlsx");
        System.out.println(file.exists());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
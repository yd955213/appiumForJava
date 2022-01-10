package com.myAppium.Utils.uiUtil;

import com.myAppium.Utils.excel.ExcelReader;
import com.myAppium.Utils.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
class ExcelWriterTest {
    ExcelWriter excelWriter;
//    @BeforeEach
    @BeforeAll
    void setUp(){

        excelWriter = new ExcelWriter("C:\\Users\\yangdang\\Desktop\\新建 XLS 工作表 (2).xls", null);
        System.out.println("BeforeAll");
    }

//    @AfterEach
    @AfterAll
    void tearDown() {
        excelWriter.save();
        excelWriter.close();
        System.out.println("AfterAll");
    }

    @BeforeEach
    void myBeforeEach(){
        System.out.println("my - BeforeEach");
    }

    @AfterEach
    void myAfterEach(){
        System.out.println("my - AfterEach");
    }

    @Test
    void getCellStyle() {
        CellStyle cellStyle = excelWriter.getCellStyle(0, 0);
        System.out.println(cellStyle);
        assert cellStyle != null;

    }

    @Test
    void save() {
        int row = 8;
        int column = 2;
        String value = "哈哈123";
        excelWriter.writeCell(row, column, value);
        excelWriter.save();
        ExcelReader excelReader = new ExcelReader(excelWriter.getCopyExcelFilePath());
        String s = excelReader.readCell(row, column);
        System.out.println(s);
        excelReader.close();
        assert value.equals(s);
    }

    @Test
    void writeCell() {
        excelWriter.writeCell(4, 0, "哈哈");
        excelWriter.writeCell(4, 1, "哈哈");
        excelWriter.writeCell(5, 0, "哈哈1");
        excelWriter.writeCell(5, 1, "哈哈1");
        excelWriter.save();
    }
    @Test
    void writeLine() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        excelWriter.writeLine(10, list);
        excelWriter.save();
        ExcelReader excelReader = new ExcelReader(excelWriter.getCopyExcelFilePath());
        List<String> readLine = excelReader.readLine(10);
        System.out.println(readLine);
        excelReader.close();
        assert list.equals(readLine);
    }
}
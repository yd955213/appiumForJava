package com.myAppium.Utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ExcelOperation {
    public Workbook workbook;
    public Sheet sheet;
    public int maxRows = 0;
    public String filePath;
    public String exceptionInfo;


    public ExcelOperation() {
//        doCreateWorkbook(filePath);
    }

    /**
     * 获取所以sheet页个数
     * @return
     */
    public int getNumberOfSheets() {
        if (!isWorkbookIsNUll())
            return 0;
        return workbook.getNumberOfSheets();
    }

    /**
     * 获取Excel的所以sheet的名称
     * @return
     */
    public List<String> getAllSheetNames(){
        if (!isWorkbookIsNUll())
            return null;
        int number = getNumberOfSheets();
        List<String> list = new LinkedList<>();
        for (int i=0; i< number; i++){
            list.add(workbook.getSheetName(i));
        }
        return list;
    }

    public void useSheetByName(String sheetName){
        isWorkbookIsNUll();
        sheet = workbook.getSheet(sheetName);
        maxRows = sheet.getPhysicalNumberOfRows();
    }
    public void useSheetByIndex(int index){
        isWorkbookIsNUll();
        sheet = workbook.getSheetAt(index);
        maxRows = sheet.getPhysicalNumberOfRows();
    }

    public Sheet getSheet() {
        return sheet;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public boolean isWorkbookIsNUll(){
        try {
            if (null == workbook){
                doCreateWorkbook(this.filePath);
            }
            if (null == sheet){
                System.out.println(exceptionInfo);
                return true;
            }

            return false;
        }catch (Exception e) {
            return false;
        }
    }

    public Workbook doCreateWorkbook(String filePath){
        this.filePath = filePath;
        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream(new File(filePath));
            if(filePath.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(fileInputStream);
//                sheet = workbook.getSheetAt(0);
//                maxRows = sheet.getPhysicalNumberOfRows();
            }else if(filePath.endsWith(".xls")){
                workbook = new HSSFWorkbook(fileInputStream);
            }

            sheet = workbook.getSheetAt(0);
            maxRows = sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
//            e.printStackTrace();
            exceptionInfo = "Excel读取失败，异常信息："+e.getMessage();
            System.out.println(exceptionInfo);
//            try {
//                // 把异常抛出
//                throw e;
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
        }finally {
            if(null != fileInputStream){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( null == sheet){
                System.out.println("Excel文件打开失败！" + exceptionInfo);
            }
        }
        return workbook;
    }

    public void close(){
        if(null != workbook){
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(Workbook workbook){
        if(null != workbook){
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Row getRow(int row){
        Row myRow = null;
        try {
            myRow = sheet.getRow(row);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(null == myRow){
            myRow = sheet.createRow(row);
        }
        return myRow;
    }

    public Cell getCell(Row row, int column){
        Cell cell = null;
        try {
            cell = row.getCell(column);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(null == cell){
            cell = row.createCell(column);
        }
        return cell;
    }
}

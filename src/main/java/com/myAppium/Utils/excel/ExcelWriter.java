package com.myAppium.Utils.excel;

import com.myAppium.Utils.commm.FileUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;

public class ExcelWriter extends ExcelOperation{
    private String excelFilePath;
    private String copyExcelFilePath;
    private Workbook workbook;
    private Sheet sheet;
    private CellStyle cellStyle;

    public String getCopyExcelFilePath() {
        return copyExcelFilePath;
    }

    public ExcelWriter(String excelFilePath, String copyExcelFilePath){
        super(excelFilePath);
        this.copyExcelFilePath = copyExcelFilePath;
        copyExcelFilePath();
        createWorkbook();
    }


    public CellStyle getCellStyle(int row, int column) {
        CellStyle cellStyle= null;
        try {
            Row row1 = sheet.getRow(row);
            Cell cell = row1.getCell(column);
            cellStyle = cell.getCellStyle();
        }catch (Exception e){
            cellStyle = null;
            e.printStackTrace();
        }
        return cellStyle;
    }

    public void save(){
        copyExcelFile(workbook, new File(copyExcelFilePath));
    }


    /**
     *  使用默认格式 写单元格
     * @param row 所在行数
     * @param column 所在列数
     * @param value 值
     */
    public void writeCell(int row, int column, String value){
        Row cellRow = getRow(row);
        Cell cell = getCell(cellRow, column);

        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

    /**
     * * 使用默认格式 写单元格
     * @param row 所在行数
     * @param column 所在列数
     * @param value 值
     * @param cellStyle 自定义单元格格式
     */
    public void writeCell(int row, int column, String value, CellStyle cellStyle){
        Row cellRow = getRow(row);
        Cell cell = getCell(cellRow, column);

        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }
    public void writeLine(int row, List<String> list){
        Cell cell = null;
        Row cellRow = getRow(row);
        for (int i=0; i < list.size(); i++){
            cell = getCell(cellRow, i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i));
        }
    }



    private void copyExcelFilePath(){
        // 如果copyExcelFilePath为null, 则备份excel以excelFilePath加 _copy 命名
        if(null == copyExcelFilePath || copyExcelFilePath.length() <=0){
            String s = ".xls";
            String[] split = this.excelFilePath.split(".xls");
            copyExcelFilePath = split[0] +"_copy" + s;
            if (split.length > 1)
                copyExcelFilePath += split[1];
        }
    }

    private void copyExcelFile(Workbook workbook, File file){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            if (workbook != null) {
//                try {
//                    workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    private void createWorkbook(){
        Workbook excelPathWorkbook = super.doCreateWorkbook(this.excelFilePath);

        File copyExcelFile = new FileUtil().creatNewFile(copyExcelFilePath);

        copyExcelFile(excelPathWorkbook, copyExcelFile);
        close(excelPathWorkbook);

        this.workbook = super.doCreateWorkbook(copyExcelFilePath);
        this.sheet = super.sheet;
        this.maxRows = super.maxRows;
        this.cellStyle = getCellStyle(0 , 0);
    }

}

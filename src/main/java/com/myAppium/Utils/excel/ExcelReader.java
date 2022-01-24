package com.myAppium.Utils.excel;

import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 123
 */
public class ExcelReader extends ExcelOperation{

    private Workbook workbook;
//    private Sheet sheet;
//    private int maxRows = 0;
    private String filePath;
    private String exceptionInfo;


    public ExcelReader(String filePath) {
        super(filePath);
    }

    /**
     * 读取第 rowIndex 行数据， 当rowIndex 值过大或者过小或文件不存在时，返回 [] 空list
     * @param rowIndex
     * @return
     */
    public List<String> readLine(int rowIndex){
        List<String> list = new ArrayList<>();
        if(super.isNUllWorkbook())
            return list;

        Row row = sheet.getRow(rowIndex);
        if (null == row)
            return list;

        int cells = row.getLastCellNum();
        for (int i=0; i< cells; i++){
            list.add(getCellValue(row.getCell(i)));
        }
        return list;
    }

    public List<List<String>> readAll(){
        List<String> allSheetNames = getAllSheetNames();
        List<List<String>> list = new ArrayList<>();
        int maxRows =0;
        for(String sheetName: allSheetNames){
            useSheetByName(sheetName);
            maxRows = getMaxRows();
            for (int i = 0; i < maxRows; i++){
                list.add(readLine(i));
            }
        }
        return list;
    }

    public String readCell(int row, int column){
        if(super.isNUllWorkbook())
            return null;
        Row myRow = sheet.getRow(row);
        if (null == myRow)
            return null;
        return getCellValue(myRow.getCell(column));
    }

    /**
     * 根据单元格的类型获取 单元格的值
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        if (null == cell)
            return "";

        String cellValue="";
        try {
            CellType cellType = cell.getCellType();
            switch (cellType){
                case STRING:
//                    cellValue = cell.getStringCellValue();
//                    break;
                case BLANK:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cellValue = sdf.format(cell.getDateCellValue());
                    }else {
                        cellValue = Double.toString(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    cellValue = getCellValue(formulaEvaluator.evaluateInCell(cell));
                    break;
                case _NONE:
                    break;
                case ERROR:
//                    cellValue = "读取异常";
//                    break;
                default:
                    cellValue = "读取异常";
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cellValue;
    }

}

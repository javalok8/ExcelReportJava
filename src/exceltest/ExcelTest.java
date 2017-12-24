/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceltest;

import java.io.*;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import java.util.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author javalok
 */
public class ExcelTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ProductModel pmodel = new ProductModel();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Lok Product"); // you can add param as sheet also
            //create heading 
            Row rowHeading = sheet.createRow(0);
            rowHeading.createCell(0).setCellValue("ID");
            rowHeading.createCell(1).setCellValue("Name");
            rowHeading.createCell(2).setCellValue("Date");
            rowHeading.createCell(3).setCellValue("Price");
            rowHeading.createCell(4).setCellValue("Quantity");
            rowHeading.createCell(5).setCellValue("Sub Total");

            //styling heading
            for (int i = 0; i < 6; i++) {
                CellStyle stylerowHeading = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 11);
                stylerowHeading.setFont(font);
                stylerowHeading.setVerticalAlignment(VerticalAlignment.CENTER);
                rowHeading.getCell(i).setCellStyle(stylerowHeading);

            }

            int r = 1;

            for (Product p : pmodel.findAll()) {
                Row row = sheet.createRow(r);
                //id column 
                Cell cellId = row.createCell(0);
                cellId.setCellValue(p.getId());
                //name column 
                Cell cellName = row.createCell(1);
                cellName.setCellValue(p.getName());
                //date column 
                Cell cellDate = row.createCell(2);
                cellDate.setCellValue(p.getCreationDate());
                CellStyle styleCreationDate = workbook.createCellStyle();
                HSSFDataFormat dfCreationDate = workbook.createDataFormat();
                styleCreationDate.setDataFormat(dfCreationDate.getFormat("yyyy-mm-dd"));
                cellDate.setCellStyle(styleCreationDate);
                //price column 
                Cell cellPrice = row.createCell(3);
                cellPrice.setCellValue(p.getPrice());
                CellStyle stylePrice = workbook.createCellStyle();
                HSSFDataFormat cf = workbook.createDataFormat();
                stylePrice.setDataFormat(cf.getFormat("$#,##0.00"));
                cellPrice.setCellStyle(stylePrice);

                //qty column 
                Cell cellQty = row.createCell(4);
                cellQty.setCellValue(p.getQuantity());
                //subtotal column 
                Cell cellSubTotal = row.createCell(5);
                cellSubTotal.setCellValue(p.getQuantity() * p.getPrice());
                CellStyle styleSubTotal = workbook.createCellStyle();
                HSSFDataFormat cfsb = workbook.createDataFormat();
                styleSubTotal.setDataFormat(cfsb.getFormat("$#,##0.00"));
                cellSubTotal.setCellStyle(stylePrice);

                r++;
            }

            //total Column
            Row rowTotal = sheet.createRow(pmodel.findAll().size() + 1);
            Cell cellTextTotal = rowTotal.createCell(0);
            int total_size = pmodel.findAll().size() + 2;
            System.out.println("Total Size = " + total_size);
            cellTextTotal.setCellValue("Total ");
            CellRangeAddress region = CellRangeAddress.valueOf("A" + total_size + ":E" + total_size + "");
            sheet.addMergedRegion(region);
            CellStyle styleTotal = workbook.createCellStyle();
            Font fontTextTotal = workbook.createFont();
            fontTextTotal.setBold(true);
            fontTextTotal.setFontHeightInPoints((short) 10);
            fontTextTotal.setColor(HSSFColor.RED.index);
            styleTotal.setFont(fontTextTotal);
            styleTotal.setVerticalAlignment(VerticalAlignment.CENTER);
            cellTextTotal.setCellStyle(styleTotal);

            //Total Value
            Cell cellTotalValue = rowTotal.createCell(5);
            cellTotalValue.setCellFormula("sum(F2:F4)");
            HSSFDataFormat cfsb = workbook.createDataFormat();
            CellStyle styleTotalValue = workbook.createCellStyle();
            styleTotalValue.setDataFormat(cfsb.getFormat("$#,##0.00"));
            cellTotalValue.setCellStyle(styleTotalValue);
            //Autofit
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            //save to excel file
            FileOutputStream out = new FileOutputStream(new File("d:\\lokproduct.xls"));
            workbook.write(out);
            out.close();
            workbook.close();

            System.out.println("Excel written successfully....");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}

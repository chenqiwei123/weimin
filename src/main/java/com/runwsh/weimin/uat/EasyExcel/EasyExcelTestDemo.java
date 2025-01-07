package com.runwsh.weimin.uat.EasyExcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.runwsh.weimin.uat.EasyExcel.Listener.ExcelModelListener;

import java.io.FileInputStream;

public class EasyExcelTestDemo {
    public static void main(String[] args) {
        // 读取 excel 表格的路径
        String readPath = "./src/main/java/com/runwsh/weimin/uat/EasyExcel/EasyExcel.xlsx";

        try {
            Sheet sheet = new Sheet(1,1,ExcelMode.class);
            EasyExcelFactory.readBySax(new FileInputStream(readPath),sheet,new ExcelModelListener());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

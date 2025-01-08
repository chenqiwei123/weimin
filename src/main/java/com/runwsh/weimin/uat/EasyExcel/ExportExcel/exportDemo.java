package com.runwsh.weimin.uat.EasyExcel.ExportExcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.runwsh.weimin.uat.EasyExcel.ExcelMode;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class exportDemo {
    /**
     * 导出到excel
     */
    public static void main(String[] args) {
        String fileName = "./src/main/java/com/runwsh/weimin/uat/EasyExcel/导出用户信息.xlsx";
        List<ExcelMode> list = new ArrayList<ExcelMode>();
        //模拟1000条数据---分成10次写进去。
        for (int i = 0; i < 1000; i++) {
            ExcelMode mode = new ExcelMode();
            mode.setColumn1(String.valueOf(i));
            mode.setColumn2("用户" + i);
            list.add(mode);
        }

        // 写入 Excel
        Sheet sheet = new Sheet(1,0,ExcelMode.class);
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(fileName))) {
            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(list, sheet);
            writer.finish(); // 关闭writer

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

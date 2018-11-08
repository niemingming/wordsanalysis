package com.nmm.study.word.service;

import com.nmm.study.word.config.SysProp;
import com.nmm.study.word.mode.OrderInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;

@Service
public class ExcelService {
    @Autowired
    private SysProp sysProp;

    /**
     * 导出数据到excel
     * @param infos
     */
    public void exportInfoToExcel(List<OrderInfo> infos) throws IOException {
        String filename = createFileNameByNow();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("data");
        //到处数据
        int i = 0;
        for (OrderInfo orderInfo:infos) {
            HSSFRow row = sheet.createRow(i++);
            int j = 0;
            //下单日期
            HSSFCell cell = row.createCell(j++);
            cell.setCellValue(orderInfo.getCreateTime());
            //发货日期
            cell = row.createCell(j++);
            cell.setCellValue(orderInfo.getSendTime());
            //实际发货日期
            cell = row.createCell(j++);
            cell = row.createCell(j++);//订单号
            cell.setCellValue(orderInfo.getOrderNo());
            cell = row.createCell(j++);//产品
            cell.setCellValue(orderInfo.getProduct());
            cell = row.createCell(j++);//寄件人
            cell = row.createCell(j++);//收货人
            cell.setCellValue(orderInfo.getUsername());
        }
        workbook.write(new File(sysProp.getExcelDir(),filename));
        workbook.close();
    }

    /**
     * 生成文件名
     * @return
     */
    private String createFileNameByNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date() )+ ".xls";
    }
}

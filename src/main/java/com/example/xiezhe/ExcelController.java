package com.example.xiezhe;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author xiezhe
 * @date 2020/3/9 15:05
 * @mondify TODO
 * @copyright gofun
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping("/readExcle")
    @ResponseBody
    public int readExcle() throws IOException, BiffException {
        // String path = "C:\\Users\\Administrator\\Desktop\\8088需求记录\\user_qcq.xls";
        String path = "C:\\Users\\admin\\Desktop\\配件库.xls";  //表格的地址
        //String tabaleName = "t_user";
        String tabaleName = "repair_part";  //表名
        insert(path, tabaleName);



        return 1;
    }
    /**
     * @param path      要解析的excel文件路径
     * @param dataTable 要写入到数据库中的表名
     * @throws IOException
     */
    public void insert(String path, String dataTable) throws IOException, BiffException {
        RepairPartTypeEnum[] values = RepairPartTypeEnum.values();
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        for (RepairPartTypeEnum value : values) {
            objectObjectHashMap.put(value.getTitle(),value.getCode());
        }
        java.io.File file = new File(path);
        // 创建新的Excel 工作簿
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        String toFileName = "D:/repair_part.sql";  //写出的文件地址和名称
        //String toFileName = "D:/user_qcq.sql";
        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();// 列数
        int rsRows = sheet.getRows();// 行数
        String simNumber = "";//每个单元格中的数据
        String sqlFinel = "";

        String str = "";//拼接要插入的列
        for (int j = 0; j < rsColumns ; j++) {
            Cell cell = sheet.getCell(j, 0);
            simNumber = cell.getContents();
            if (j == rsColumns - 1) {
                str += simNumber;
            } else {
                str += simNumber + ",";
            }

        }
        for (int i = 1; i < rsRows; i++) {
            String id = UUID.randomUUID().toString().replace("-", "");;
            String sql = "INSERT INTO " + dataTable + "(id," +
                    "state," +
                    "create_user_id," +
                    "create_user_name," +
                    "create_time," +
                    "update_time," +
                    "part_name," +
                    "part_type," +
                    "part_specification," +
                    "procurement_unit," +
                    "part_brand," +
                    "quality_classify," +
                    "cartype_id,car_type_brand_code,car_type_brand,car_type_name,car_type_series_code,car_type_series,product_year," +
                    "stand_price," +
                    "insure_price," +
                    "remark" +

                    ") values(";//拼接sql
            sql += "'" + id + "',";
            sql += "'1',";
            sql += "'K816015823de524556966cafdbf32ec816',";
            sql += "'jobadmin',";

            sql += "NOW(),";
            sql += "NOW(),";
            //添加用户时 需要添加角色 默认都是管理员
            //String sql1 = "insert into  user_role_relation (user_id,role_id) values(";
            //sql1 += "'" + id + "','" + "0158b84de66a0002');";  //管理员id去现场重新编写 切记切记！！！
            //System.out.println(sql1);
            for (int j = 0; j < rsColumns; j++) {
            Cell cell = sheet.getCell(j, i);
            simNumber = cell.getContents();
                if (j == rsColumns - 1) {
                    sql += "'" + simNumber + "'";
                } else {
                    if(j==1){
                        sql += "'" + objectObjectHashMap.get(simNumber) + "',";
                    }else if(j==6) {
                        sql += "'" + simNumber + "id',";
                        sql += "'" + simNumber + "brand_code',";
                        sql += "'" + simNumber + "brand',";
                        sql += "'" + simNumber + "',";
                        sql += "'" + simNumber + "type_series_code',";
                        sql += "'" + simNumber + "type_series',";
                        sql += "'" + simNumber + "product_year',";
                    }else if(j==7){
                        sql += "0.00,";
                    }else if(j==8){
                        sql += "0.00,";
                    }else {
                        sql += "'" + simNumber + "',";
                    }
                }
            }
            sql += " );";
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(toFileName,true);//创建文本文件  true属性不覆盖
                fileWriter.write(sql + "\r\n");//写入 \r\n换行
                //fileWriter.write(sql1 + "\r\n");//写入 \r\n换行
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(sql);
        }
    }

}

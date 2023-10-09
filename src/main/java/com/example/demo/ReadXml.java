package com.example.demo;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadXml {

        public static String filename="2stu.xlsx";
        public static void main(String[] args) throws Exception  {
            chooes();
            //pickone(5);
            testXlsx();
        }
        public static void testXlsx() throws Exception {
            File file = new File( filename);
            InputStream stream = new FileInputStream(file);
            Workbook xssfWorkbook = new XSSFWorkbook(stream); //表1:数据量较少
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            //获取输入流
            List<Row> listRow = new ArrayList<Row>();
            for(Row row2 : sheet){
                listRow.add(row2);
                System.out.println(row2.getCell(0)+","+row2.getCell(3)+", "+row2.getCell(4));
            }
            if(stream != null){
                stream.close();
            }
        }
        public static void pickone(int num) throws Exception {
            File file = new File( filename);
            InputStream stream = new FileInputStream(file);
            Workbook xssfWorkbook = new XSSFWorkbook(stream); //表1:数据量较少
            Sheet sheet = xssfWorkbook.getSheetAt(0);

            //获取输入流
            List<Row> listRow = new ArrayList<Row>();
            for(Row row2 : sheet){
                int cid=(int)Double.parseDouble(row2.getCell(0).toString());
                if(cid==num) row2.getCell(4).setCellValue(1);
            }
            OutputStream fileOut=new FileOutputStream(filename);
            xssfWorkbook.write(fileOut);
            if(stream != null){
                stream.close();
                fileOut.close();
            }
        }
        public static void chooes() throws Exception{
            // 读文件
            File file = new File( filename);
            InputStream stream = new FileInputStream(file);
            Workbook xssfWorkbook = new XSSFWorkbook(stream); //表1:数据量较少
            Sheet sheet = xssfWorkbook.getSheetAt(0);

            // 把第五列读取下来
            List<Row> listRow = new ArrayList<Row>();
//            for(Row row2 : sheet){
//                int status=(int)Double.parseDouble(row2.getCell(4).toString());
//                if(status == 0) listRow.add(row2);
//            }
            for(Row row2 : sheet){
                listRow.add(row2);
            }



            // 处理全都点过一遍的情况
            int count = 0;
            for(Row i : listRow){
                count += (int)Double.parseDouble(i.getCell(4).toString());
            }
            if(count >= 35){

            }

            // 随机抽取一个学生
            int len = listRow.size() - 1;
            // 创建一个 Random 对象
            Random random = new Random();
            // 生成一个随机整数
            if(len == 0){
                // 当名单里只有一个人的时候
                len = 1;
            }
            int randomNumber = random.nextInt(len);
            // 调用pickone取出一个学生
            Row lucky = listRow.get(randomNumber);
            System.out.println("今天是" + lucky.getCell(3) + "玩原神！");
            pickone((int)Double.parseDouble(lucky.getCell(0).toString()));
        }

}

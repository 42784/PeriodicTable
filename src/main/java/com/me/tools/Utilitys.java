package com.me.tools;

import org.apache.log4j.Logger;

import java.math.BigDecimal;;
import java.util.Scanner;

/**
 * 常用工具类
 */
public class Utilitys {
    /**
     * 获取扫描器
     */
    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static final Logger logger = Logger.getLogger(Utilitys.class);
    public static Integer userNextInt(String title) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            logger.info("请输入" + title + ": ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            logger.warn("错误");
        }
    }
    public static BigDecimal userNextBigDecimal(String title) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            logger.info("请输入" + title + ": ");
            if (scanner.hasNextLine()) {
                try{
                    return new BigDecimal(scanner.nextLine());
                }catch (Exception ignored){}
            }
            logger.warn("错误");
        }
    }

    public static String userNextLine(String title) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            logger.info("请输入" + title + ": ");
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            logger.warn("错误");
        }
    }



}

package com.me.tools;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
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

    public static Boolean isSure() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            logger.info("你确定吗(y/n)");
            if (scanner.hasNextLine()) {
                char userChoice = scanner.nextLine().toLowerCase().charAt(0);//用户的选择
                //先变成小写 再获取第一个字符
                switch (userChoice) {
                    case 'y' -> {
                        return true;
                    }
                    case 'n' -> {
                        return false;
                    }
                }
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

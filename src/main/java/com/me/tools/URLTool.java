package com.me.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.me.PeriodicTable;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

/**
 * @author 秃头老狗
 * @version 1.0
 * <a href="https://ptable.com/JSON/compounds/formula=Li">...</a>
 * <a href="https://ptable.com/JSON/properties-90d5338.json">...</a>
 */
public class URLTool {
    public static final Logger logger = Logger.getLogger(URLTool.class);

    public static File requestServer(String urlS, String fileName) {
        try {
            URL url = new URL(urlS);
            String json = getJsonFromFromUrl(url);
            String convert = jsonConvertToYaml(json);//转yaml
            return writeFileToTemp(fileName, convert);
        } catch (IOException e) {
            logger.error("获取服务器资源错误");
        }
        return null;


    }

    /**
     * 保存文件到Temp文件夹
     */
    public static File writeFileToTemp(String filename, String content) throws IOException {

        File file = new File(PeriodicTable.softwareAddress + "\\Temp\\" + filename);
        if (!file.exists() && !file.isDirectory()) {
            logger.debug("file.getParentFile().mkdir() = " + file.getParentFile().mkdir());
            logger.debug("file.createNewFile() = " + file.createNewFile());
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));//文件写入

        //写入文件
        logger.debug(String.format("Write File To Temp(%s)", filename));
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
        return file;


    }

    private static String getJsonFromFromUrl(URL url) throws IOException {


        InputStream inputStream = url.openStream();//开启流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//缓冲读取
        StringBuilder stringBuilder = new StringBuilder();
        logger.debug("Request Server");

        String line;
        while ((line = bufferedReader.readLine()) != null) {//读取流
            stringBuilder.append(line).append('\n');
        }

        if (stringBuilder.toString().isEmpty()) {
            throw new IOException("未知的Stream错误");
        }
        inputStream.close();
        bufferedReader.close();
        logger.debug("get Json: "+stringBuilder.toString());
        return stringBuilder.toString();

    }

    /**
     * json字符串转yaml字符串
     */
    public static String jsonConvertToYaml(String jsonString) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        return (new YAMLMapper().writeValueAsString(jsonNode)).replace("---", "main:");
    }
}

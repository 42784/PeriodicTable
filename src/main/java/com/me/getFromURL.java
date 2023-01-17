package com.me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

/**
 * @author 秃头老狗
 * @version 1.0
 * <a href="https://ptable.com/JSON/compounds/formula=Li">...</a>
 * <a href="https://ptable.com/JSON/properties-90d5338.json">...</a>
 */
public class getFromURL {
    public static final Logger logger = Logger.getLogger(getFromURL.class);
    public static void main(String[] args) {
        try {
            requestToGetYAML(new URL("https://ptable.com/JSON/properties-90d5338.json"));
        } catch (IOException e) {
            logger.error("获取服务器资源错误(请检查网络连接)");
        }


    }
    /**
     * 请求服务器获取json并且自动转为yaml
     */
    public static void requestToGetYAML(URL url) throws IOException{

        InputStream inputStream = url.openStream();//开启流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//缓冲读取
        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(
                                        PeriodicTable.softwareAddress+"\\test.yaml")));//文件写入
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {//读取流
            stringBuilder.append(line).append('\n');
        }

        if (stringBuilder.toString().isEmpty()){
            throw new IOException("未知的Stream错误");
        }
        String convert = jsonConvertToYaml(stringBuilder.toString());
        logger.debug("jsonConvertToYaml() = " + convert);
        //写入文件
        bufferedWriter.write(convert);
        bufferedWriter.flush();


    }

    /**
     * json字符串转yaml字符串
     */
    public static String jsonConvertToYaml(String jsonString) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        return (new YAMLMapper().writeValueAsString(jsonNode)).replace("---","main:");
    }
}

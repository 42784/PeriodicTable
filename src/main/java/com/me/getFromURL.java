package com.me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.*;
import java.net.URL;

/**
 * @author 秃头老狗
 * @version 1.0
 * <a href="https://ptable.com/JSON/compounds/formula=He">...</a>
 * <a href="https://ptable.com/JSON/properties-90d5338.json">...</a>
 */
public class getFromURL {
    public static void main(String[] args) throws IOException {
        URL url = new URL("file:///D:/Downloads/formula=Li");


        InputStream inputStream = url.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.json")));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            System.out.println("line = " + line);
        }
        bufferedWriter.flush();

        System.out.println("jsonConvertToYaml() = " + jsonConvertToYaml());




    }

    /**
     * json字符串转yaml字符串
     */
    public static String jsonConvertToYaml(String jsonString) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        return new YAMLMapper().writeValueAsString(jsonNode);
    }
}

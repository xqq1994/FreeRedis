package freeredis.util;

import com.alibaba.fastjson.JSON;
import freeredis.entity.HostAndPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static freeredis.util.Constants.redisClientIp;

public class Persistant {

    // 文件名可随意指定，你可以用文本编辑器打开这个文件（注意，记事本无法处理换行）
    static String filename = redisClientIp;

    // 保存 Person 对象到文件。保存格式为：   
    // 1、每个 Person 一行   
    // 2、每行依次存放 name、birthday、male 三个属性值，用 tab 隔开   
    // 3、birthday 属性保存的是毫秒数，male 属性保存的是字符串   
    public static void savePersons(List<HostAndPort> hostAndPorts) throws IOException {

        // 生成文件内容   
        StringBuilder data = new StringBuilder();
        for (HostAndPort hostAndPort : hostAndPorts) {
            data.append(getPersonString(hostAndPort)).append("\n");
        }

        // 保存文件内容   
        FileWriter writer = new FileWriter(filename);
        writer.write(data.toString());
        writer.close();
        System.out.println("对象保存完毕。");
    }

    private static String getPersonString(HostAndPort hostAndPort) {
        return JSON.toJSONString(hostAndPort);
    }

    // 从文件中读取 Person 对象   
    public static ObservableList<HostAndPort> readPersons() throws IOException {
        ObservableList<HostAndPort> result = FXCollections.observableArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(getPersonFromString(line));
        }

        return result;
    }

    // 通过一行文件内容生成一个 Person 对象   
    private static HostAndPort getPersonFromString(String line) {
        return JSON.parseObject(line, HostAndPort.class);
    }

}

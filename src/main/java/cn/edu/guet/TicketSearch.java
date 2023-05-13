package cn.edu.guet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketSearch {
    public static String search(String fromStation,String toStation,String date) throws IOException {
        Map<String, String> nameCodeMap = new HashMap<String, String>();
        Map<String, String> codeNameMap = new HashMap<String, String>();

        String station_names ="a";
                Matcher matcher = Pattern.compile("((?<=@)[^@]+)").matcher(station_names);
        while (matcher.find()) {
            String tmp = matcher.group();
            String[] contents = tmp.split("\\|");
            String name = contents[1];
            String code = contents[2];
            nameCodeMap.put(name, code);
            codeNameMap.put(code, name);
        }
        OkHttpClient client = new OkHttpClient();

        String urlStr = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + date
                + "&leftTicketDTO.from_station=" + nameCodeMap.get(fromStation) + "&leftTicketDTO.to_station="
                + nameCodeMap.get(toStation) + "&purpose_codes=ADULT";
        Request request = new Request.Builder().url(urlStr).get()
                .addHeader("Cookie", "_jc_save_fromStation="+nameCodeMap.get(fromStation))
                .build();

        Response response = client.newCall(request).execute();
        String jsonStr = response.body().string();
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        JSONArray jsonArr = (JSONArray) ((JSONObject) jsonObj.get("data")).get("result");

        List<Ticket> ticketList=new ArrayList<>();//集合：容器，放Ticket的容器

        for (Object item : jsonArr.toArray()) {
            String record = item.toString();
            String[] recordArr = record.split("\\|");

            Ticket ticket=new Ticket();
            ticket.setTrainNumber(recordArr[3]);
            ticket.setFormStation(recordArr[6]);
            ticket.setToStation(recordArr[7]);
            ticket.setDepartureTime(recordArr[8]);
            ticket.setArrviaTime(recordArr[9]);
            ticket.setDuration(recordArr[10]);

            ticketList.add(ticket);
        }
        return JSONObject.toJSONString(ticketList);
    }
}

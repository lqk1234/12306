package cn.edu.guet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fromStation = request.getParameter("fromStation");
        System.out.println("起始站：" + fromStation);
        String toStation = request.getParameter("toStation");
        System.out.println("终点站："+toStation);

        String departureTime=request.getParameter("departureTime");
        System.out.println("出发日期："+departureTime);

        String ticketList = TicketSearch.search(fromStation, toStation, departureTime);

        String json=JSON.toJSONString(ticketList, SerializerFeature.PrettyFormat);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package cn.edu.guet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("余票查询");
        String startStation = request.getParameter("startStation");
        System.out.println("起始站: " + startStation);
        String endStation = request.getParameter("endStation");
        System.out.println("终点站: " + endStation);
        String departureDate = request.getParameter("departureDate");
        System.out.println("到站日期: " + departureDate);
        String allTickets = TicketSearch.search(startStation,endStation,departureDate);
        //设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(allTickets);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package jp.ohara;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import tool.Page;

@WebServlet("/countdate")
@SuppressWarnings("serial")
public class CountDate extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        // 本日の日付を取得するためカレンダーインスタンスを取得する
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //この部分を入力getParameterに変更する
        String inputString = "1970/02/09";
        String today = sdf.format(cal.getTime());
        try {
            //Dateのインスタンスを取得する
            Date date1 = sdf.parse(inputString);
            Date date2 = sdf.parse(today);
            //今日の日付と入力された日付の差分を取得単位はミリ秒
            long diff = date2.getTime() - date1.getTime();

            //HTML出力
            Page.header(out);
            out.println("今日の日付は" + sdf.format(cal.getTime()) + "です<br/>");
            out.println("ささきさんがいままで生きた日数は" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + "日です");
            out.println(diff / (1000 * 60 * 60 * 24 ));
            Page.footer(out);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
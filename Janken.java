package jp.ohara;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Page;
//乱数を使うためのインポート（クラスライブラリ）
import java.util.Random;

@WebServlet("/janken")
@SuppressWarnings("serial")
public class Janken extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            request.setCharacterEncoding("UTF-8");
            String human = request.getParameter("te");
            //ここで0-2の整数（int）で乱数を発生
            int comp = new Random().nextInt(3);
 
            Page.header(out);
            out.println("あなたは" + human + "<br/>");
            if(comp == 0){
                //乱数が0のときはぐー
                out.println("コンピューターは ぐー");
            }else if(comp == 1){
                //乱数が1のときはちょき
                out.println("コンピューターは ちょき");
            }else{
                //乱数が2のときはぱー
                out.println("コンピューターは ぱー");                    
            }
            Page.footer(out);

            
            var c = (comp -Integer.parseInt(human) + 3)% 3;
            if (c == 0) {
                out.println("DRAW");
            } else if (c == 2) {
                out.println("LOSE");
            } else {
                out.println("WIN");
            }
        }
    }

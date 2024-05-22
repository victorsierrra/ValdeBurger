package Controller;

import Controller.Actions.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("text/plain;charset=UTF-8");
        //request.getMethod()
        //request.getQueryString()
        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");
        String[] arrayAction= new String[2];;
        if (strAction != "")
        {
            arrayAction = strAction.split("\\."); //[0] PELICULA <-> [1] FIND_ALL
        }
        switch (arrayAction[0].toUpperCase())
        {
            case "CATEGORIA":
            {
                out.print(new CategoriaAction().execute(request,response, arrayAction[1]));
                break;
            }
            case "DEPARTAMENTOS":
            {
                out.print(new DepartamentoAction().execute(request,response,arrayAction[1]));
                break;
            }
            case "TRABAJO":
            {
                out.print(new TrabajoAction().execute(request,response,arrayAction[1]));
                break;
            }
            case "EMPLEADOS":
            {
                out.print(new EmpleadosAction().execute(request,response,arrayAction[1]));
                break;
            }
            case "PROMOCION":
            {
                out.print(new PromocionAction().execute(request,response,arrayAction[1]));
                break;
            }
            default:
            {
                System.out.println(arrayAction[0]);
                throw new ServletException ("Acción " + arrayAction[0] +" no valida");
            }
        }
        System.out.println(strAction);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }
    private void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("text/plain;charset=UTF-8");
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        // Person p  = gson.fromJson(parser.parse(getBody(request)), Person.class);

       // System.out.println(gson.toJson(p));

        // response.getWriter().print("hola " + p.name + "\r\n");
    }
    private static String getBody(HttpServletRequest request)  {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            // throw ex;
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {

                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
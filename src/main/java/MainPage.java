import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/s")
public class MainPage extends HttpServlet {
    List<Blydo> lb;
    List<Sotrudnik> sotrudniki;
    String emptyTextArea;
    String record;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        record = null;

        if (request.getParameter("search") != null) {
            String text = request.getParameter("search_text");
            if (text.trim().equals("")) {
                emptyTextArea = "Please fill the field correctly";
                request.setAttribute("error", emptyTextArea);
                pageRedirector("/mypage.jsp", request, response);
            } else {
                record = text;
                request.setAttribute("result", record);
                pageRedirector("/secondpage.jsp", request, response);
            }
        }
        if (request.getParameter("mainpage") != null){
            pageRedirector("/mypage.jsp", request, response);
        }
        if (request.getParameter("restaurantmap") != null){
            pageRedirector("/restaurantmap.jsp", request, response);
        }
        if (request.getParameter("restaurantstaff") != null){
            HttpSession session = request.getSession();
            session.setAttribute("sotrudniki", sotrudniki);
            pageRedirector("/restaurantstaff.jsp", request, response);
        }
        if (request.getParameter("contacts") != null){
            pageRedirector("/contacts.jsp", request, response);
        }
    }

    private void pageRedirector(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        record = null;
        if (request.getParameter("blydoname") != null) {
            String text1 = request.getParameter("blydoname");
            record = text1;
            request.setAttribute("result", record);
            pageRedirector("/secondpage.jsp", request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("lb", lb);
            pageRedirector("/mypage.jsp", request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        lb = DBConnection.dbUpdate();
        sotrudniki = DBConnection.getStaff();
        emptyTextArea = new String();
        record = new String("");
    }
}

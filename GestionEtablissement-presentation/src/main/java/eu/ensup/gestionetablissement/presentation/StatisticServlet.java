package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private int NIVEAU_EXCELLENT = 17;
    private int NIVEAU_BON = 12;
    private int NIVEAU_MOYEN = 8;

    public StatisticServlet()
    {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        doAction( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        doAction(request, response);
    }

    public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if( request.getSession().getAttribute("role") == null )
            this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );

        int role = Integer.parseInt(request.getSession().getAttribute("role").toString());

        this.getGraphique(request, response);
        /*String servletPath = request.getServletPath().replace("/", "");
        String[] pathInfo = (request.getPathInfo() == null ? new String[0] : (request.getPathInfo().substring(1)).split("/"));
        String  action    = null;

        if( servletPath.equals("mark") && pathInfo.length == 1 )
            action = pathInfo[0];
        else
            action = "student";

        if( action.equals("student") )
            this.student(request, response);
        else if( action.equals("course") )
            this.course(request, response);
        else if( action.equals("mark") )
            this.mark(request, response);
        else
            this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );*/
    }

    public void getGraphique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        try {
            List<MarkDTO> allMarks = MarkServlet.getAllMark();
            AtomicInteger note_mauvais = new AtomicInteger();
            AtomicInteger note_moyen = new AtomicInteger();
            AtomicInteger note_bon = new AtomicInteger();
            AtomicInteger note_excellent = new AtomicInteger();
            allMarks.forEach((markDTO) -> {
                float mark = markDTO.getMark();
                if (mark >= NIVEAU_EXCELLENT) {
                    note_excellent.getAndIncrement();
                } else if (mark >= NIVEAU_BON) {
                    note_bon.getAndIncrement();
                } else if (mark >= NIVEAU_MOYEN) {
                    note_moyen.getAndIncrement();
                } else {
                    note_mauvais.getAndIncrement();
                }
            });
            float perc_mauvais = 0.f;
            float perc_moyen = 0.f;
            float perc_bon = 0.f;
            float perc_excellent = 0.f;
            float nbNote = allMarks.size();

            perc_excellent = (note_excellent.get() / nbNote) * 100;
            perc_bon = (note_bon.get() / nbNote) * 100;
            perc_moyen = (note_moyen.get() / nbNote) * 100;
            perc_mauvais = (note_mauvais.get() / nbNote) * 100;

            request.setAttribute("mauvais", perc_mauvais);
            request.setAttribute("moyen", perc_moyen);
            request.setAttribute("bon", perc_bon);
            request.setAttribute("excellent", perc_excellent);
        }
        catch (Exception e) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", e.getMessage());
            requestDispatcher = request.getRequestDispatcher("statistics.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        requestDispatcher = request.getRequestDispatcher("statistics.jsp");
        requestDispatcher.forward(request, response);
    }

    public void student(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        try {
            request.setAttribute("student", StudentServlet.getAllStudent());
            dispatcher= request.getRequestDispatcher("/get_all_student.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    public void course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        try {
            request.setAttribute("course", CourseServlet.getAllCourse());
            dispatcher= request.getRequestDispatcher("/get_all_course.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    public void mark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        MarkService cs = new MarkService();
        try {
            request.setAttribute("marks", cs.getAll());
            dispatcher= request.getRequestDispatcher("/get_all_mark.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }
}

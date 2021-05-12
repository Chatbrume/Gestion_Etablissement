package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.ExceptionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarkServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public MarkServlet()
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

        String servletPath = request.getServletPath().replace("/", "");
        String[] pathInfo  = (request.getPathInfo() == null ? new String[0] : (request.getPathInfo().substring(1)).split("/"));
        String  action = null;
        Integer idMark = null;

        if( servletPath.equals("mark") && pathInfo.length == 2 )
        {
            action = pathInfo[0];
            idMark = Integer.parseInt(pathInfo[1]);
        }
        else if( servletPath.equals("mark") && pathInfo.length == 1 && pathInfo[0].contains("create") )
        {
            action = "create";
        }
        else if( servletPath.equals("marks") && pathInfo.length == 0 && role == 1 )
        {
            action = "getall";
        }

        if(request.getParameter("mark") != null)
        {
            MarkDTO mark = new MarkDTO();
            mark.setMark(Float.parseFloat(request.getParameter("mark")));
            mark.setAssessment(request.getParameter("assessment"));
            mark.setIdCourse(Integer.parseInt(request.getParameter("idCourse")));
            mark.setIdStudent(Integer.parseInt(request.getParameter("idStudent")));

            request.setAttribute("mark", mark);
        }

        if( action.equals("create") )
            this.create(request, response);
        else if( action.equals("getall") )
            this.getAll(request, response);
        else if( action.equals("get") )
            this.get(request, response, idMark);
        else if( action.equals("update") )
            this.update(request, response, idMark);
        else if( action.equals("delete") )
            this.delete(request, response, idMark);
        else
            this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        MarkDTO mark = null;
        if( request.getAttribute("mark") != null )
        {
            mark = (MarkDTO) request.getAttribute("mark");

            MarkService cs = new MarkService();
            try {
                cs.create(mark);
                dispatcher= request.getRequestDispatcher("/accueil.jsp");
            }
            catch (ExceptionService exceptionService) {
                request.setAttribute("alert_type", "danger");
                request.setAttribute("alert_text", exceptionService.getMessage());
                dispatcher= request.getRequestDispatcher("/mark.jsp");
            }
        }
        else
        {
            request.setAttribute("title", "Ajouter une nouvelle notes");
            request.setAttribute("formaction", "/GestionEtablissement/mark/create");
            dispatcher= request.getRequestDispatcher("/mark.jsp");
        }

        //Redirection
        dispatcher.forward(request, response);
    }

    public void get(HttpServletRequest request, HttpServletResponse response, int idMark) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        MarkService cs = new MarkService();
        try {
            System.out.println("Est passer 1");
            System.out.println("idMark: "+idMark);
            if( cs.get(idMark) != null )
            {
                System.out.println("Est passer 2");
                MarkDTO mark = cs.get(idMark);

                request.setAttribute("title", "Notes");
                request.setAttribute("formaction", "/GestionEtablissement/mark/update/" + idMark);
                request.setAttribute("mark", mark);
                dispatcher = request.getRequestDispatcher("/mark.jsp");
            }
            else
            {
                dispatcher= request.getRequestDispatcher("/accueil.jsp");
            }
        }
        catch (ExceptionService exceptionService)
        {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }
        //Redirection
        dispatcher.forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response, int idMark) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher = null;

        MarkService cs = new MarkService();

        try {
            if( request.getAttribute("mark") != null )
            {
                MarkDTO mark = (MarkDTO) request.getAttribute("mark");

                cs.update((MarkDTO) request.getAttribute("mark"));

                request.setAttribute("alert_type", "info");
                request.setAttribute("alert_text", "Vous avez mis a jour les infomations de la notes: "+mark.getMark());

                dispatcher= request.getRequestDispatcher("/accueil.jsp");
            }
            else
            {
                MarkDTO mark = (MarkDTO) cs.get(idMark);

                request.setAttribute("title", "Modifier la notes");
                request.setAttribute("formaction", "/GestionEtablissement/mark/update/" + idMark);
                request.setAttribute("mark", mark);

                dispatcher = request.getRequestDispatcher("/mark.jsp");
            }
        }
        catch (ExceptionService exceptionService)
        {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        if( dispatcher == null )
            dispatcher = request.getRequestDispatcher("/accueil.jsp");

        //Redirection
        dispatcher.forward(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response, int idMark) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        MarkService cs = new MarkService();
        try {
            MarkDTO mark = cs.get(idMark);

            if( mark == null )
            {
                request.setAttribute("alert_type", "warning");
                request.setAttribute("alert_text", "La note que vous avez voulue supprimer n'existe pas !");
            }
            else
            {
                cs.delete(idMark);
                request.setAttribute("alert_type", "info");
                request.setAttribute("alert_text", "Vous avez supprimer la notes: " + mark.getMark());
            }

            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }
        catch (ExceptionService exceptionService)
        {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }
        //Redirection
        dispatcher.forward(request, response);
    }
}

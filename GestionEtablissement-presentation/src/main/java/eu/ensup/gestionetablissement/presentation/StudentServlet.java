package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.business.Student;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StudentServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public StudentServlet()
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
        String[] pathInfo = (request.getPathInfo() == null ? new String[0] : (request.getPathInfo().substring(1)).split("/"));
        String  action    = null;
        Integer idStudent = null;

        if( servletPath.equals("student") && pathInfo.length == 2 )
        {
            action    = pathInfo[0];
            idStudent = Integer.parseInt(pathInfo[1]);
        }
        else if( servletPath.equals("student") && pathInfo.length == 1 && pathInfo[0].contains("get") )
        {
            action    = "get";
        }
        else if( servletPath.equals("student") && pathInfo.length == 1 && pathInfo[0].contains("create") )
        {
            action    = "create";
        }
        else if( servletPath.equals("students") && pathInfo.length == 0 && role == 1 )
        {
            action    = "getall";
        }

        if(request.getParameter("lastname") != null)
        {
            StudentDTO student = new StudentDTO();
            if( request.getParameter("role") == null )
                student.setRole(Role.STUDENT);
            else
                student.setRole(Role.valueOf(request.getParameter("role")) );
            student.setLastname(request.getParameter("lastname"));
            student.setFirstname(request.getParameter("firstname"));
            student.setDateOfBirth(transformStringToDate(request.getParameter("dateofbirth")));
            student.setAddress(request.getParameter("address"));
            student.setMailAddress(request.getParameter("email"));
            student.setPhoneNumber(request.getParameter("telephone"));
            student.setPassword(request.getParameter("password"));

            request.setAttribute("student", student);
        }

        if( action.equals("create") )
            this.createStudent(request, response);
        else if( action.equals("getall") )
            this.getAllStudent(request, response);
        else if( action.equals("get") )
            this.getStudent(request, response, idStudent);
        else if( action.equals("update") )
            this.updateStudent(request, response, idStudent);
        else if( action.equals("delete") )
            this.deleteStudent(request, response, idStudent);
        else
            this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
    }

    /*public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if( request.getSession().getAttribute("role") == null )
            this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );

        int role = Integer.parseInt(request.getSession().getAttribute("role").toString());

        String path = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
        HashMap<String, String> hmPath = ProcessingUrlPath.convert(path);

        String  parent    = hmPath.get("parent");
        String  action    = hmPath.get("action");
        Integer idStudent = (hmPath.get("student") == null ? null : Integer.parseInt(hmPath.get("student")));
        Integer idCourse  = (hmPath.get("course")  == null ? null : Integer.parseInt(hmPath.get("course")));
        Integer idMark    = (hmPath.get("mark")    == null ? null : Integer.parseInt(hmPath.get("mark")));

        if( parent.equals("student") || parent.equals("students") )
        {
            switch (action)
            {
                case "create":
                    this.createStudent(request, response);
                    break;
                case "all":
                    this.getAllStudent(request, response);
                    break;
                case "get":
                    this.getStudent(request, response, idStudent);
                    break;
                case "update":
                    this.updateStudent(request, response, idStudent);
                    break;
                case "delete":
                    this.deleteStudent(request, response, idStudent);
                    break;
                default:
                    this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
            }
        }
    }*/

    public static List<StudentDTO> getAllStudent() throws ExceptionService
    {
        List<StudentDTO> allStudent = new ArrayList<StudentDTO>();
        PersonService ps = new PersonService();

        for( PersonDTO person : ps.getAll() )
            if( person.getRole().getName().equals("Student") )
                allStudent.add((StudentDTO)person);

        return allStudent;
    }

    public void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        PersonService ps = new PersonService();
        try {
            request.setAttribute("students", getAllStudent());
            dispatcher= request.getRequestDispatcher("/get_all_student.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    public void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        StudentDTO student = null;
        if( request.getAttribute("student") != null )
        {
            student = (StudentDTO) request.getAttribute("student");

            PersonService ps = new PersonService();
            try {
                ps.create(student);
                dispatcher= request.getRequestDispatcher("/accueil.jsp");
            }
            catch (ExceptionService exceptionService) {
                request.setAttribute("alert_type", "danger");
                request.setAttribute("alert_text", exceptionService.getMessage());
                dispatcher= request.getRequestDispatcher("/student.jsp");//create_student.jsp
            }
        }
        else
        {
            request.setAttribute("title", "Ajouter un nouveau étudiant");
            request.setAttribute("formaction", "/GestionEtablissement/student/create");
            dispatcher= request.getRequestDispatcher("/student.jsp");
        }

        //Redirection
        System.out.println("createStudent: END");
        dispatcher.forward(request, response);
    }

    public void getStudent(HttpServletRequest request, HttpServletResponse response, Integer idStudent) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        PersonService ps = new PersonService();
        try {
            request.setAttribute("idstudent", (idStudent == null ? -1 : idStudent));
            request.setAttribute("students", getAllStudent());

            request.setAttribute("title", "Etudiant");
            request.setAttribute("formaction", "/GestionEtablissement/student/update/"+idStudent);
            dispatcher = request.getRequestDispatcher("/student.jsp");
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

    public void updateStudent(HttpServletRequest request, HttpServletResponse response, Integer idStudent) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher = null;

        PersonService ps = new PersonService();

        try {
            if( request.getAttribute("student") != null )
            {
                StudentDTO person = (StudentDTO) request.getAttribute("student");

                if( person.getRole().getName().equals("Student") )
                {
                    ps.update((StudentDTO) request.getAttribute("student"));

                    request.setAttribute("alert_type", "info");
                    request.setAttribute("alert_text", "Vous avez mis a jour les infomations de l'étudiant: "+person.getFirstname()+" "+person.getLastname());
                }
                else
                {
                    request.setAttribute("alert_type", "warning");
                    request.setAttribute("alert_text", "La person "+person.getFirstname()+" "+person.getLastname()+" n'est pas un étudiant");
                }


                dispatcher= request.getRequestDispatcher("/accueil.jsp");
            }
            else
            {
                request.setAttribute("idstudent", (idStudent == null ? -1 : idStudent));
                request.setAttribute("students", getAllStudent());

                request.setAttribute("title", "Modifier étudiant");
                request.setAttribute("formaction", "/GestionEtablissement/student/update/"+idStudent);

                dispatcher = request.getRequestDispatcher("/student.jsp");
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

    public void deleteStudent(HttpServletRequest request, HttpServletResponse response, int idStudent) throws ServletException, IOException
    {
        //Traitement des infos
        RequestDispatcher dispatcher;

        PersonService ps = new PersonService();
        try {
            PersonDTO person = ps.get(idStudent);

            if( person == null )
            {
                request.setAttribute("alert_type", "warning");
                request.setAttribute("alert_text", "L'etudiant que vous avez voulue supprimer n'existe pas !");
            }
            else if( person.getRole().getName().equals("Student") )
            {
                ps.delete(idStudent);

                request.setAttribute("alert_type", "info");
                request.setAttribute("alert_text", "Vous avez supprimer l'étudiant: "+person.getFirstname()+" "+person.getLastname());
            }
            else
            {
                request.setAttribute("alert_type", "warning");
                request.setAttribute("alert_text", "La person que vous avez voulue supprimer n'est pas un étudiant !");
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

    public static Date transformStringToDate(String value)
    {
        Date datevalue = null;
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
            datevalue = dateformat.parse(value);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return datevalue;
    }
}
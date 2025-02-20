package eu.ensup.gestionetablissement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.ensup.gestionetablissement.business.*;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.IPersonDao;
import eu.ensup.gestionetablissement.dao.MarkDao;
import eu.ensup.gestionetablissement.dao.PersonDao;
import eu.ensup.gestionetablissement.dto.DirectorDTO;
import eu.ensup.gestionetablissement.dto.ManagerDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.dto.TeacherDTO;
import eu.ensup.gestionetablissement.mapper.DirectorMapper;
import eu.ensup.gestionetablissement.mapper.ManagerMapper;
import eu.ensup.gestionetablissement.mapper.StudentMapper;
import eu.ensup.gestionetablissement.mapper.TeacherMapper;

/**
 * The type Service person.
 */
public class PersonService implements IEntityService<PersonDTO> {

    private IPersonDao dao = null;
    
    // nom de la classe
    String className = getClass().getName();
    
    /**
     * Instantiates a new Service person.
     */
    public PersonService() {
        this.dao = new PersonDao();
    }
    
    public PersonService(IPersonDao idao) {
        this.dao = idao;
    }


    // Create Person
    @Override
    public int create(String surname, String mail, String address, String phone, String firstname, String password, int role, Date dateofbirth, String subjectTaught) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        // Checker le role et faire une instace et l'envoyer dans le DAO
        int check = 0;
        switch(role){
            case 1: // Director
                PersonDTO directorDTO = new DirectorDTO(surname, mail, address, phone, 0, firstname, password);
                Person director = DirectorMapper.dtoToBusiness((DirectorDTO)directorDTO);
                try {
                    check = this.dao.create(director);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                   throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 2: // Manager
                PersonDTO managerDTO = new ManagerDTO(surname, mail, address, phone, 0, firstname, password);
                Person manager = ManagerMapper.dtoToBusiness((ManagerDTO)managerDTO);
                try{
                check = this.dao.create(manager);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 3: // Teacher
                // On instancie Personne pour que dans le DAO il puisse récupérer le matière enseignée
                PersonDTO teacherDTO = new TeacherDTO(surname, mail, address, phone, 0, firstname, password, subjectTaught);
                Person teacher = TeacherMapper.dtoToBusiness((TeacherDTO)teacherDTO);
                try {
                    check = this.dao.create(teacher);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 4: // Student
                // On instancie Personne pour que dans le DAO il puisse récupérer la date de naissance
                PersonDTO studentDTO = new StudentDTO(surname, mail, address, phone, 0, firstname,password, dateofbirth);
                Person student = StudentMapper.dtoToBusiness((StudentDTO)studentDTO);
                try{
                check = this.dao.create(student);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
        }
        return check;
    }

    // Create Person
    @Override
    public int create(PersonDTO person) throws ExceptionService
    {
        int role = person.getRole().getNum();

        Date dateofbirth = null;
        if( person instanceof StudentDTO )
            dateofbirth = ((StudentDTO)person).getDateOfBirth();

        String subjectTaught = null;
        if( person instanceof TeacherDTO )
            subjectTaught = ((TeacherDTO)person).getSubjectTaught();

        return this.create(person.getLastname(), person.getMailAddress(), person.getAddress(), person.getPhoneNumber(), person.getFirstname(), person.getPassword(), role, dateofbirth, subjectTaught);
    }

    // Update Person
    @Override
    public int update(String surname, String mail, String address, String phone, String firstname, String password, int role, Date dateofbirth, String subjectTaught, double average) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        switch(role){
            case 1: // Director
                PersonDTO directorDTO = new DirectorDTO(surname, mail, address, phone, 0, firstname, password);
                Person director = DirectorMapper.dtoToBusiness((DirectorDTO)directorDTO);
                try{
                res = this.dao.update(director);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                   throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 2: // Manager
                PersonDTO managerDTO = new ManagerDTO(surname, mail, address, phone, 0, firstname, password);
                Person manager = ManagerMapper.dtoToBusiness((ManagerDTO)managerDTO);
                try{
                res = this.dao.update(manager);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 3: // Teacher
                PersonDTO teacherDTO = new TeacherDTO(surname, mail, address, phone, 0, firstname, password, subjectTaught);
                Person teacher = TeacherMapper.dtoToBusiness((TeacherDTO)teacherDTO);
                try{
                res = this.dao.update(teacher);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 4: // Student
                PersonDTO studentDTO = new StudentDTO(surname, mail, address, phone, 0, firstname,password,dateofbirth,average);
                Person student = StudentMapper.dtoToBusiness((StudentDTO)studentDTO);
                try{
                res = this.dao.update(student);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
        }
        return res;
    }

    @Override
    public int update(PersonDTO person) throws ExceptionService
    {
        Date dateofbirth = null;
        if( person instanceof StudentDTO )
            dateofbirth = ((StudentDTO)person).getDateOfBirth();

        Double mark = null;
        if( person instanceof StudentDTO )
            mark = ((StudentDTO)person).getAverage();

        String subjectTaught = null;
        if( person instanceof TeacherDTO )
            subjectTaught = ((TeacherDTO)person).getSubjectTaught();

        return this.update(person.getLastname(), person.getMailAddress(), person.getAddress(), person.getPhoneNumber(), person.getFirstname(), person.getPassword(), person.getRole().getNum(), dateofbirth, subjectTaught, mark);
    }

    @Override
    public int delete(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        try{
         res = this.dao.delete(index);
        return res;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
    }

    @Override
    public int linkToCourse(int idEtudiant, int idCourse) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            int res = this.dao.linkToCourse(idEtudiant, idCourse);
            return res;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    @Override
    public PersonDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
        Person person = this.dao.get(index);
        PersonDTO personDTO = new PersonDTO();
        if(person instanceof Student)
        {
            personDTO = StudentMapper.businessToDto((Student)person);
            ((StudentDTO)personDTO).setAverage(this.getAverage(index));
        }else if(person instanceof Manager)
        {
            personDTO = ManagerMapper.businessToDto((Manager)person);
        }else if(person instanceof Teacher)
        {
            personDTO = TeacherMapper.businessToDto((Teacher)person);
        }else if(person instanceof Director)
        {
            personDTO = DirectorMapper.businessToDto((Director)person);
        }

        return personDTO;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
           throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    @Override
    public List<PersonDTO> getAll() throws ExceptionService{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();
        try {
            List<Person> listAllPerson = this.dao.getAll();
            for(Person person : listAllPerson)
            {
                if (person instanceof Student) {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO = StudentMapper.businessToDto((Student) person);
                    studentDTO.setAverage(getAverage(studentDTO.getId()));
                    personDTOList.add(studentDTO);
                } else if (person instanceof Manager) {
                    ManagerDTO managerDTO = new ManagerDTO();
                    managerDTO = ManagerMapper.businessToDto((Manager) person);
                    personDTOList.add(managerDTO);
                } else if (person instanceof Teacher) {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO = TeacherMapper.businessToDto((Teacher) person);
                    personDTOList.add(teacherDTO);
                } else if (person instanceof Director) {
                    DirectorDTO directorDTO = new DirectorDTO();
                    directorDTO = DirectorMapper.businessToDto((Director) person);
                    personDTOList.add(directorDTO);
                }
            }
            return personDTOList;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
    }

    public List<PersonDTO> getAll(String clasz) throws ExceptionService
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();
        try {
        	List<Person> listAllPerson = this.dao.getAll();
        	for(Person person : listAllPerson)
            {
                if ( clasz.equals(Student.class.getName()) && person instanceof Student) {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO = StudentMapper.businessToDto((Student) person);
                    studentDTO.setAverage(getAverage(studentDTO.getId()));
                    personDTOList.add(studentDTO);
                } else if ( clasz.equals(Manager.class.getName()) && person instanceof Manager) {
                    ManagerDTO managerDTO = new ManagerDTO();
                    managerDTO = ManagerMapper.businessToDto((Manager) person);
                    personDTOList.add(managerDTO);
                } else if ( clasz.equals(Teacher.class.getName()) && person instanceof Teacher) {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO = TeacherMapper.businessToDto((Teacher) person);
                    personDTOList.add(teacherDTO);
                } else if ( clasz.equals(Director.class.getName()) && person instanceof Director) {
                    DirectorDTO directorDTO = new DirectorDTO();
                    directorDTO = DirectorMapper.businessToDto((Director) person);
                    personDTOList.add(directorDTO);
                }
            }
            return personDTOList;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
    }
    
    public float getAverage(int index) throws ExceptionService
    {
    	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
    	Float average = 0f;
    	
    	List<Mark> listMark;
		try {
			listMark = (new MarkDao()).getAllMarkByStudentId(index);
			if( listMark != null ) {
                for (Mark mark : listMark) {
                    average += mark.getMark();
                }
                average = average / listMark.size();
            }
		}
		catch (ExceptionDao exceptionDao) {
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
		}
    	
    	return average;
    }
}

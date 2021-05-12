package eu.ensup.gestionetablissement.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Connect.
 */
public class Connect
{
	private static String DRIVER = "";
	private static String URL = "";
	private static String USERNAME = "";
	private static String PASSWORD = "";
	private static Properties properties = new Properties();

	/**
	 * Open an connention with the information in the class
	 *
	 * @return an connection open
	 */
	public static Connection openConnection() throws ExceptionDao
	{
		InputStream inputStream = Connect.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(inputStream);
		}
		catch (IOException e) {
			throw new ExceptionDao("Nous ne parvenons pas à joindre le fichier de configuration !");
		}
		DRIVER = properties.getProperty("db.driver");
		URL = properties.getProperty("db.url");
		USERNAME = properties.getProperty("db.username");
		PASSWORD = properties.getProperty("db.password");

		Connection cn = null;
		try
		{
			//Chargement du Driver
			Class.forName(DRIVER);

			//Récuperation de la connection
			if( URL != null && USERNAME != null && PASSWORD != null )
				cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			if( cn == null && URL != null )
				cn = DriverManager.getConnection(URL);

			// TODO:  Add logger failed and successfull
		}
		catch (ClassNotFoundException | SQLException e){
			// TODO:  Add logger failed and successfull
			throw new ExceptionDao("Nous ne parvenons pas à joindre le serveur distant. Veuillez réessayer ultérieurement");
		}

		return cn;
	}
}

package eu.ensup.gestionetablissement.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.ILoginDao;
import eu.ensup.gestionetablissement.dao.LoginDao;

@ExtendWith(MockitoExtension.class)
class ConnectionServiceTestMock2
{
	ILoginDao mockDao;
	ConnectionService service;

	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		
		// Objet que l'on vas mocker
		// Class dont on souhaite copier le comportement le temps du test
		mockDao = mock(LoginDao.class);
		
		// Objet dans lequel on vas injecter l'objet mocker
		// Class que l'on souhaite tester
		service = new ConnectionService(mockDao);
	}

	@Test
	@DisplayName("Test exception login and password are good")
	void testCheckPasswordLoginAndPasswordAreGood()
	{
		try {
			// GIVEN
			when(mockDao.checkPassword("root", "")).thenReturn(0);
			
			// WHEN
			final int result = service.checkConnection("root", "");
			assertThat("Test result", result, equalTo(0));
			
			// THEN
			verify(mockDao).checkPassword("root", "");
		}
		catch (ExceptionDao | ExceptionService e) {
			fail(e.getMessage());
		}
	}

	@Test
	@DisplayName("Test exception login and password are bad")
	void testCheckPasswordLoginAndPasswordAreBad()
	{
		try {
			// GIVEN
			when(mockDao.checkPassword("admin", "admin")).thenReturn(1);
			
			// WHEN
			final int result = service.checkConnection("admin", "admin");
			assertThat("Test result Bad: ", result, equalTo(1));
			
			// THEN
			verify(mockDao).checkPassword("admin", "admin");
		}
		catch (ExceptionDao | ExceptionService e) {
			fail(e.getMessage());
		}
	}
}

package eu.ensup.gestionetablissement.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.ILoginDao;

@ExtendWith(MockitoExtension.class)
class ConnectionServiceTestMock
{
	@Mock
	ILoginDao mockDao; // Objet que l'on vas mocker
	// Class dont on souhaite copier le comportement le temps du test

	@InjectMocks
	ConnectionService service; // Objet dans lequel on vas injecter l'objet mocker
	// Class que l'on souhaite tester

	@Test
	@DisplayName("Test exception login and password are good")
	void testCheckPasswordLoginAndPasswordAreGood()
	{
		try {
			// GIVEN
			//Stubing = imposer un comportement a un mock
			when(mockDao.checkPassword("root", "")).thenReturn(0);
			
			// WHEN
			// test le comportement du mock
			final int result = service.checkConnection("root", "");
			assertThat("Test result", result, equalTo(0));
			
			// THEN
			// verify que le comportement a été tester
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

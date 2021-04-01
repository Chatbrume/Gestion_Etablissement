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
import eu.ensup.gestionetablissement.dao.ICourseDao;

@ExtendWith(MockitoExtension.class)
class CourseServiceTestMock
{
	@Mock
	ICourseDao mockDao;

	@InjectMocks
	CourseService service;

	@Test
	@DisplayName("Test getIndex pour coursesubject='Informatique' et nbhour=10")
	void testGetIndex()
	{
		try {
			// GIVEN
			when(mockDao.getIndex("Informatique", 10)).thenReturn(1);
			
			// WHEN
			final int result = service.getIndex("Informatique", 10);
			assertThat(result, equalTo(1));
			
			// THEN
			verify(mockDao).getIndex("Informatique", 10);
		}
		catch (ExceptionDao e) {
			fail(e.getMessage());
		}
	}
}

package eu.ensup.gestionetablissement.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.ensup.gestionetablissement.dao.ExceptionDao;

@ExtendWith(MockitoExtension.class)
class CourseServiceTestSpy
{
	@Spy
	CourseService service;

	@Test
	@DisplayName("Test getIndex pour coursesubject='informatique' et nbhour=25")
	void testGetIndex()
	{
		try {
			// WHEN
			final int result = service.getIndex("informatique", 25);
			assertThat(result, equalTo(1));
			
			// THEN
			verify(service).getIndex("informatique", 25);
		}
		catch (ExceptionDao e) {
			fail(e.getMessage());
		}
	}
}

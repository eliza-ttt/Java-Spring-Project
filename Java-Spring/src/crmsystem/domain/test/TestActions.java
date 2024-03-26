package crmsystem.domain.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import crmsystem.domain.Action;

import static org.junit.Assert.*;


public class TestActions
{

	@Test
	public void futureActionsAreNotOverdue()
	{
	
		GregorianCalendar requiredBy = new GregorianCalendar();
		requiredBy.add(Calendar.DAY_OF_WEEK, 1);		
		Action testAction = new Action("A Test Action", requiredBy, null);

		boolean overdue = testAction.isOverdue();
		
		assertFalse(overdue);
	}
	
	@Test
	public void pastActionsAreOverdue()
	{
		
		GregorianCalendar requiredBy = new GregorianCalendar(1980, 1, 1, 1, 1, 1);

		Action testAction = new Action("A Test Action", requiredBy, null);
		
		boolean overdue = testAction.isOverdue();
		
		assertTrue(overdue);
	}
}

package crmsystem.dataaccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import crmsystem.domain.Action;

@Repository
public class ActionDaoJdbcImpl implements ActionDao
{
	private static final String DELETE_SQL = "DELETE FROM ACTION WHERE ACTION_ID=?";
	private static final String UPDATE_SQL = "UPDATE ACTION SET DETAILS=?, COMPLETE=?, OWNING_USER=?, REQUIRED_BY=? WHERE ACTION_ID=?";
	private static final String INSERT_SQL = "INSERT INTO ACTION (DETAILS, COMPLETE, OWNING_USER, REQUIRED_BY) VALUES (?,?,?,?)";
	private static final String GET_INCOMPLETE_SQL = "SELECT ACTION_ID, DETAILS, COMPLETE, OWNING_USER, REQUIRED_BY FROM ACTION WHERE OWNING_USER=? AND COMPLETE=?";
	
	private JdbcTemplate template;
	
	@Autowired
	public ActionDaoJdbcImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	public void create(Action newAction) 
	{
		template.update(INSERT_SQL,newAction.getDetails(), newAction.isComplete(),newAction.getOwningUser(),  newAction.getRequiredBy());					
	}

	@PostConstruct
	private void createTables()
	{
		try
		{
		   this.template.update("CREATE TABLE ACTION (ACTION_ID integer generated by default as identity (start with 1), DETAILS VARCHAR(255), COMPLETE BOOLEAN, OWNING_USER VARCHAR(20), REQUIRED_BY DATE)");
		}
		catch (org.springframework.jdbc.BadSqlGrammarException e)
		{
			System.out.println("Assuming the Action table exists");
		}
	}
	
	public List<Action> getIncompleteActions(String userId) 
	{		
		return this.template.query(GET_INCOMPLETE_SQL, new ActionRowMapper(), userId, false);
	}

	public void update(Action actionToUpdate) throws RecordNotFoundException 
	{
		this.template.update(UPDATE_SQL,actionToUpdate.getDetails(),actionToUpdate.isComplete(), actionToUpdate.getOwningUser(), actionToUpdate.getRequiredBy().getTime(),  actionToUpdate.getActionId() );
	}

	public void delete(Action oldAction) throws RecordNotFoundException 
	{
		this.template.update(DELETE_SQL, oldAction.getActionId());
	}
}


class ActionRowMapper implements RowMapper<Action>
{
	public Action mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		int actionId = rs.getInt(1);
		String details = rs.getString(2);
		boolean complete = rs.getBoolean(3);
		String owningUser = rs.getString(4);
		Date requiredBy = rs.getDate(5);
		
		Calendar requiredByCal = new java.util.GregorianCalendar();
		requiredByCal.setTime(requiredBy);
		
		return new Action("" + actionId, details, requiredByCal, owningUser, complete);
	}	
}

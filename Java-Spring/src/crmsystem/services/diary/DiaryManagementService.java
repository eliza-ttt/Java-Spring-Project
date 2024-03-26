 package crmsystem.services.diary;

import java.util.List;

import crmsystem.domain.Action;

public interface DiaryManagementService 
{

	public void recordAction(Action action);
	

	public List<Action> getAllIncompleteActions(String requiredUser);
}

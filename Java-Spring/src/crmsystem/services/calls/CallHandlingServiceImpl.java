package crmsystem.services.calls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmsystem.domain.Action;
import crmsystem.domain.Call;
import crmsystem.services.customers.CustomerManagementService;
import crmsystem.services.customers.CustomerNotFoundException;
import crmsystem.services.diary.DiaryManagementService;

@Transactional
@Service
public class CallHandlingServiceImpl implements CallHandlingService 
{
	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;

	@Autowired
	public CallHandlingServiceImpl(CustomerManagementService cms, DiaryManagementService dms)
	{
		this.customerService = cms;
		this.diaryService = dms;
	}
	@Override
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException 
	{

		customerService.recordCall(customerId, newCall);

		for (Action nextAction: actions)
		{
			diaryService.recordAction(nextAction);			
		}
	}

}

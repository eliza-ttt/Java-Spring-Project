package crmsystem.services.calls;

import java.util.Collection;

import crmsystem.domain.Action;
import crmsystem.domain.Call;
import crmsystem.services.customers.CustomerNotFoundException;

public interface CallHandlingService 
{

	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}

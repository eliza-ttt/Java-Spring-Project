package crmsystem.restcontrollers;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import crmsystem.domain.Customer;

@XmlRootElement(name="customers")
public class CustomerCollectionRepresentation extends ResourceSupport
{
	private List<Customer> customers;

	public CustomerCollectionRepresentation() {}
	
	public CustomerCollectionRepresentation(List<Customer> customers) {
		super();
		this.customers = customers;
	}

	@XmlElement(name="customer")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}

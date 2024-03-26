package crmsystem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;


@Entity
@XmlRootElement
public class Customer extends ResourceSupport implements Serializable
{
	
	@Id
	private String customerId;
	@NotEmpty
	private String companyName;
	private String email;
	private String telephone;
	@NotNull
	private String notes;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Call> calls;	
	private int version;
	public Customer(String customerId, String companyName, String email,
			        String telephone, String notes)
	{
		this(customerId, companyName, notes);
		this.email = email;
		this.telephone = telephone;
	}
	
	public Customer(String customerId, String companyName, String notes)
	{
		this.customerId = customerId;
		this.companyName = companyName;
		this.notes = notes;
		this.calls = new ArrayList<Call>();
	}
	

	public void addCall(Call callDetails) 
	{
		this.calls.add(callDetails);		
	}
	
	public String toString()
	{
		return this.customerId + ": " + this.companyName ;
	}

	public String getCustomerId() 
	{
		return this.customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getNotes() {
		return notes;
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Customer() {}
}

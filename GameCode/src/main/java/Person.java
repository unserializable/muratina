/**
 * @(#) Person.java
 */

public abstract class Person
{
	protected String name;

	protected String surname;

	protected String phoneNo;

	protected double earnings;

	protected String taxCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", surname='").append(surname).append('\'');
		sb.append(", phoneNo='").append(phoneNo).append('\'');
		sb.append(", earnings=").append(earnings);
		sb.append(", taxCode='").append(taxCode).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

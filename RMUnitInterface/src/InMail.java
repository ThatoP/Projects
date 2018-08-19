/**
 * @author Thato Puoetsile
 * @aboutAuthor B. Eng (Information and Communication Engineering), ITIL
 * @user Records Management Unit
 */

public class InMail 
{
	//Variables 
	private String refNum, dateOnLetter, originDept, subject, dateRec, actionOfficer, dateMarked, days, actDate, daysToAct;
	
	// Constructors
	public InMail(String refNum, String dateOnLetter, String originDept, String subject, String dateRec, String actionOfficer, String dateMarked, String days, String actDate, String daysToAct) {
		this.refNum = refNum;
		this.dateOnLetter = dateOnLetter;
		this.originDept = originDept;
		this.subject = subject;
		this.dateRec = dateRec;
		this.actionOfficer = actionOfficer;
		this.dateMarked = dateMarked;
		this.days = days;
		this.actDate = actDate;
		this.daysToAct = daysToAct;
	}
	
	//Accessors and Mutators
	public String getRefNum() {
		return refNum;
	}
	
	public String getDateOnLetter() {
		return dateOnLetter;
	}
	
	public String getOriginDept () {
		return originDept;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getDateRec() {
		return dateRec;
	}
	
	public String getActionOfficer() {
		return actionOfficer;
	}
	
	public String getDateMarked() {
		return dateMarked;
	}
	
	public String getDays() {
		return days;
	}
	
	public String getActDate() {
		return actDate;
	}
	
	public String getDaysToAct() {
		return daysToAct;
	}
}

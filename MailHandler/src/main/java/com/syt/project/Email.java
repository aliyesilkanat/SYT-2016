package com.syt.project;
public class Email {

	private String Subject;
	private String Text;
	private String From;

	public Email(String Subject, String From, String Text) {
		this.Subject = Subject;
		this.From = From;
		this.Text = Text;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	@Override
	public String toString() {
		return "Email [Subject=" + Subject + ", Text=" + Text + ", From="
				+ From + "]";
	}

}

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAnalyzer {

	public static void main(String[] args) {
		// ArrayList<Email> check = new MailChecker().check("pop.gmail.com",
		// "pop3", "syt.izel.ali@gmail.com", "sytharika");
		// for (Email email : check) {
		//
		// System.out.println(email.getText());
		// }

		System.out.println(getStudents("Merhaba Hocam SYT dersinin 2.proje grup üyeleri aşağıdaki gibidir 5110000000-Adı Soyadı 5110000001-Adı Soyadı 5110000002-Adı Soyadı"));
       
	}

	public static ArrayList<Student> getStudents(String Text) {

		ArrayList<Student> group = new ArrayList<Student>();

		Pattern pattern = Pattern.compile("\\d{10}-(\\p{L}|\\s)*");
		Matcher matcher = pattern.matcher(Text);
		while (matcher.find()) {
			System.out.println(matcher.group());
			String[] parts = matcher.group().split("-");
			
			group.add(new Student(parts[0], parts[1]));
		}

		return group;

	}

}

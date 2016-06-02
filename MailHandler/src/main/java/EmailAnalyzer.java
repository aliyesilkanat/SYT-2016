import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAnalyzer {

	

	public  ArrayList<Student> getStudents(String Text) {

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

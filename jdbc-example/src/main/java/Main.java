import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:postgresql://localhost:5432/tybootcamp";
		String user = "postgres";
		String password = "mysecretpassword";

		PersonRepository pjdbc = new PersonRepository(url, user, password);

		Product product = new Product(32458807l,"name","name","desc",1,3243898);

		pjdbc.addPerson(product);
	}
}

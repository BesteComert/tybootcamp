import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonRepository {
	private Connection connection;

	public PersonRepository(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver"); //load driver communication of postgresql.
		this.connection = DriverManager.getConnection(url, user, password);
	}

	public void addPerson(Product product) throws SQLException {
		String sql = "insert into products (id, description, name, name2, price, seller_id)"
				+ "values (?,?,?,?,?,?)";

		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setLong(1, product.getId());
		ps.setString(2, product.getDescription());
		ps.setString(3, product.getName());
		ps.setString(4, product.getName2());
		ps.setFloat(5, product.getPrice());
		ps.setLong(6, product.getSellerId());

		ps.executeQuery();
	}
}

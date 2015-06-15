package test.java.com.softserve.edu.oms.data;

public class UserRepository {

	/**
	 * Get administrator user.
	 * @return administrator user.
	 */
	public static IUser getAdminUser() {
		return User.get().setLoginName("iva").setFirstName("ivanka")
				.setLastName("horoshko").setPassword("qwerty").setEmail("mail@gmail.com")
				.setRegion("West").setRole("Administrator").build();
	}

	/**
	 * Get customer user.
	 * @return customer user.
	 */
	public static IUser getCustomerUser() {
		return User.get().setLoginName("login1").setFirstName("firstName1")
				.setLastName("lastName1").setPassword("qwerty").setEmail("mail@gmail.com")
				.setRegion("East").setRole("Customer").build();
	}

	/**
	 * Get merchandiser user.
	 * @return merchandiser user.
	 */
	public static IUser getMerchandiserUser() {
		return User.get().setLoginName("login2").setFirstName("firstName2")
				.setLastName("lastName2").setPassword("qwerty").setEmail("mail@gmail.com")
				.setRegion("East").setRole("Merchandiser").build();
	}

}

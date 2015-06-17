package com.softserve.edu.oms.data;

public class UserRepository {

	public static IUser getSearchUser() {
		return User.get()
			.setLoginName("iva")
			.setFirstName("ivanka")
			.setLastName("horoshko")
			.setPassword("qwerty")
			.setEmail("aaaa@gmail.com")
			.setRegion("East")
			.setRole("Administrator")
			.build();
	}

	public static IUser getAdminUser() {
		return User.get()
				.setLoginName("iva")
				.setFirstName("ivanka")
				.setLastName("horoshko")
				.setPassword("qwerty")
				.setEmail("aaaa@gmail.com")
				.setRegion("East")
				.setRole("Administrator")
				.build();
	}

	public static IUser getCustomerUser() {
		return User.get()
				.setLoginName("login1")
				.setFirstName("firstName1")
				.setLastName("lastName1")
				.setPassword("qwerty")
				.setEmail("mail@gmail.com")
				.setRegion("East")
				.setRole("Customer")
				.build();
		//return new User("login1","firstName1","lastName1","qwerty","mail@gmail.com","East","Customer");
	}

	public static IUser getNewUser() {
		return User.get()
				.setLoginName("aabb")
				.setFirstName("firstName1")
				.setLastName("lastName1")
				.setPassword("qwerty")
				.setEmail("mail@gmail.com")
				.setRegion("East")
				.setRole("Customer")
				.build();
		//return new User("aabb","firstName1","lastName1","qwerty","mail@gmail.com","East","Customer");
	}
	
	public static IUser getInvalidUser() {
		return User.get()
				.setLoginName("aabb")
				.setFirstName("firstName1")
				.setLastName("lastName1")
				.setPassword("ytrewq")
				.setEmail("mail@gmail.com")
				.setRegion("East")
				.setRole("Customer")
				.build();
	}

}
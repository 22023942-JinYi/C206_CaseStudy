package UserAccess;

public class User {
		
		private String username;
		private int id;
		private String password;
		private boolean admin;
		
		public User(int id, String username, String password, boolean admin) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.admin = admin;
		}
		/**
		 * @param username2
		 */
		public User(String username,int id) {
			// TODO Auto-generated constructor stub
			super();
			this.username = username;
			this.id = id;
		}
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @return the admin
		 */
		public boolean getAdmin() {
			return admin;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		
	}



This application contains the basic authorization login,logout,security and passwordEncoder and save the data in table in dataBase

you can signup if you don't have username in dataBase

if you have userName in dataBase you can log in , after login Successfully you will redirect to the home page of teh application

you can see your personal data in profile page
Each user has access to a personalized profile page, where they can view their personal data.
to make this Able i use:
Custom UserDetailsService:

 created a custom UserDetailsService to handle user authentication. This service retrieves user details from the database, ensuring a seamless login experience.
Web Security Configuration:

Our WebSecurityConfig class extends WebSecurityConfigurerAdapter, allowing us to configure security settings for our application.defined rules for URL access, set up the login page, and specified how login/logout actions should be handled.
BCrypt Password Encoder:

Passwords are securely encoded using the BCryptPasswordEncoder to safeguard user credentials.

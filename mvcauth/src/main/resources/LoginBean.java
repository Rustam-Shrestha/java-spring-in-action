public class LoginBean {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin() {
        // Example hardcoded validation (replace with database logic for real-world use)
        return "admin@gmail.com".equals(email) && "admin".equals(password);
    }
}

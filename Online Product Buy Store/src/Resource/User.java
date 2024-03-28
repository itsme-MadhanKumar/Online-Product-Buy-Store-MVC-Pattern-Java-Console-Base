package Resource;

public class User
{
    private  String name;
    private final String password;
    private String email;
    private String phone;
    private String role;


    public String getRole()
    {
        return role;
    }
    public User(String name,String email,String password,String role,String phone)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public User(String name, String email,String phone,String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
}

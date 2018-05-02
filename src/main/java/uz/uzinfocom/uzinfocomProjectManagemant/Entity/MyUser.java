package uz.uzinfocom.uzinfocomProjectManagemant.Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Roles> role;
    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects;

    public MyUser(){}

    public MyUser(MyUser users) {
        this.id = (int)users.getId();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.role= users.getRole();
        this.projects = users.getProjects();
    }

    public MyUser(String firstName, String lastName, String username, String password, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = getRole();
    }

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

}

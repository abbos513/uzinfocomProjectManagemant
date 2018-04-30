package uz.uzinfocom.uzinfocomProjectManagemant.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String projectOwner;
    private String agreement;
    private String outsourceCompany;
    private LocalDate dateOfRegistration;
    @ManyToOne
    private MyUser projectManager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getOutsourceCompany() {
        return outsourceCompany;
    }

    public void setOutsourceCompany(String outsourceCompany) {
        this.outsourceCompany = outsourceCompany;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public MyUser getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(MyUser projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectManagerFullName(){
        return projectManager.getFullName();
    }
}

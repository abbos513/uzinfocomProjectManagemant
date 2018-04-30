package uz.uzinfocom.uzinfocomProjectManagemant.Model;

public class AddProjectViewModel {
    private String ownerCompany;
    private String outsourceCompany;
    private int userId;

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public String getOutsourceCompany() {
        return outsourceCompany;
    }

    public void setOutsourceCompany(String outsourceCompany) {
        this.outsourceCompany = outsourceCompany;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

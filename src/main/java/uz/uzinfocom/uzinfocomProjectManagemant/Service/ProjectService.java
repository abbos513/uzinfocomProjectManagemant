package uz.uzinfocom.uzinfocomProjectManagemant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Project;
import uz.uzinfocom.uzinfocomProjectManagemant.Model.AddProjectViewModel;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.ProjectRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class ProjectService {
    @Autowired
    MyUserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    public boolean saveProject(AddProjectViewModel projectViewModel){

        Project project = new Project();

        project.setProjectOwner(projectViewModel.getOwnerCompany());
        project.setOutsourceCompany(projectViewModel.getOutsourceCompany());

        LocalDate localDate = LocalDate.now();
        project.setDateOfRegistration(localDate);

        MyUser user = userRepository.findById(projectViewModel.getUserId());
        project.setProjectManager(user);

        projectRepository.save(project);
        return true;
    }

    public FileOutputStream writeBytesToFileClassic(byte[] bFile, String fileDest) {

        FileOutputStream fileOuputStream = null;
        File downloadFile = new File(fileDest);

        try {
//            downloadFile.getAbsoluteFile();
            fileOuputStream = new FileOutputStream("name");
            fileOuputStream.write(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileOuputStream;
    }
}

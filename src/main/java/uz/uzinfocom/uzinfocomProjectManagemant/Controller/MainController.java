package uz.uzinfocom.uzinfocomProjectManagemant.Controller;

import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Roles;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.UploadFile;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.ProjectRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.RoleRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.UploadFileRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Service.ProjectService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//import static org.springframework.boot.context.ApplicationPidFileWriter.DEFAULT_FILE_NAME;

@Controller
@RequestMapping("/get")
public class MainController {

    @Autowired
    MyUserRepository myUserRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectService projectService;
    @Autowired
    RoleRepository roleRepository;


//    @PreAuthorize("hasAnyRole('PM')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private ModelAndView users (Model model){

        Iterable<MyUser> user = myUserRepository.findAll();
        model.addAttribute("allUsers", user);
        return new ModelAndView("Users");
    }

    @PreAuthorize("hasAnyRole('Registrar')")
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    private ModelAndView projects (Model model){
        LocalDate localDate = LocalDate.now();
        model.addAttribute("currentDate", localDate);
        if(projectRepository.findAll() != null)
            model.addAttribute("projects", projectRepository.findAll());
        if (myUserRepository.findAllByRole("PM") != null)
            model.addAttribute("projectManagers", myUserRepository.findAllByRole("PM"));

        return new ModelAndView("Projects");
    }

    String UPLOAD_FOLDER = "/home/abbos513/Downloads/Spring Boot in Action.pdf";

    @Autowired
    UploadFileRepository fileRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            return new ModelAndView("status", "message", "Please select a file and try again");
        }

        try {
            // read and write the file to the selected location-
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            UploadFile file1 = new UploadFile();
            file1.setName(file.getName());
            file1.setBytes(bytes);
            fileRepository.save(file1);

            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("status", "message", "File Uploaded sucessfully");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    private ModelAndView showFile(Model model){
        UploadFile file = fileRepository.findById(15);

        projectService.writeBytesToFileClassic(file.getBytes(), UPLOAD_FOLDER);


        return new ModelAndView("Projects");
    }

}

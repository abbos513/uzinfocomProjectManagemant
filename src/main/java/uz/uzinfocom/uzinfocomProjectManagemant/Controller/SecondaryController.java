package uz.uzinfocom.uzinfocomProjectManagemant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Project;
import uz.uzinfocom.uzinfocomProjectManagemant.Model.AddProjectViewModel;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.ProjectRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Service.ProjectService;

@RestController
@RequestMapping(value = "/rest")
public class SecondaryController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MyUserRepository userRepository;
    @Autowired
    ProjectService projectService;

//    @RequestMapping(method = RequestMethod.GET)
//    private ModelAndView showProjects (Model model){
//        model.addAttribute("allProjects", projectRepository.findAll());
//        return new ModelAndView("index");
//    }

//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    private ResponseEntity<Object> getUser(){
//        MyUser user = new MyUser();
//        user.setId(1);
//        user.setFirstName("Abbos");
//        user.setLastName("Abdukodirov");
//        user.setUsername("bos");
//        user.setPassword("123");
//        user.setRole("admin");
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PreAuthorize("hasAnyRole('Registrar')")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private ModelAndView addUser(@RequestBody MyUser user){
        userRepository.save(user);
        return new ModelAndView("redirect:/get/users");
    }

    @PreAuthorize("hasAnyRole('Registrar')")
    @RequestMapping(value = "/addPro", method = RequestMethod.POST)
    private ModelAndView addProject(@RequestBody AddProjectViewModel addProjectViewModel){
        projectService.saveProject(addProjectViewModel);
        return new ModelAndView("redirect:/get/projects");
    }
}

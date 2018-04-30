package uz.uzinfocom.uzinfocomProjectManagemant.Repository;

import org.springframework.data.repository.CrudRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Project;

import java.util.List;


public interface ProjectRepository extends CrudRepository<Project, Long> {
}

package uz.uzinfocom.uzinfocomProjectManagemant.Repository;

import org.springframework.data.repository.CrudRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Roles;

public interface RoleRepository extends CrudRepository<Roles, Integer> {
    public Roles findById(int id);
}

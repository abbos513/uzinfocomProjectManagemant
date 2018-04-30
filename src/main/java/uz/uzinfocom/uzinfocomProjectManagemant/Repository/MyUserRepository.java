package uz.uzinfocom.uzinfocomProjectManagemant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends CrudRepository<MyUser, Integer> {
    public List<MyUser> findAllByRole(String role);
    public MyUser findById(int id);

    public Optional<MyUser> findByUsername(String username);
}

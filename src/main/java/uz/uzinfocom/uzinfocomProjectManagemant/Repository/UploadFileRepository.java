package uz.uzinfocom.uzinfocomProjectManagemant.Repository;

import org.springframework.data.repository.CrudRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.UploadFile;

public interface UploadFileRepository extends CrudRepository<UploadFile, Integer> {
    public UploadFile findById(int id);
}

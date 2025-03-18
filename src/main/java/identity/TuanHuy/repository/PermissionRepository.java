package identity.TuanHuy.repository;

import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,String> {

            Optional<Permission> findByName(String name);

}

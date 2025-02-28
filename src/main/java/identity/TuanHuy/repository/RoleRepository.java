    package identity.TuanHuy.repository;

    import identity.TuanHuy.entity.Role;
    import identity.TuanHuy.entity.RoleEnum;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.Optional;

    public interface RoleRepository extends JpaRepository<Role, RoleEnum> {

        Optional<Role> findByName(RoleEnum name);


        @Modifying
        @Transactional
        void deleteByName(RoleEnum name);
    }

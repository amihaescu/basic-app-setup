package ro.amihaescu.polls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amihaescu.polls.model.Role;
import ro.amihaescu.polls.model.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}

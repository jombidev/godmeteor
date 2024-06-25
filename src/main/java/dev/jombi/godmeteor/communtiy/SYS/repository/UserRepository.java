package dev.jombi.godmeteor.communtiy.SYS.repository;

import dev.jombi.godmeteor.communtiy.JJE.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

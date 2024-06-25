package dev.jombi.godmeteor.communtiy.SYS.repository;

import dev.jombi.godmeteor.communtiy.JJE.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Post, Long> {
    public List<Post> findByMember(Member member);
}

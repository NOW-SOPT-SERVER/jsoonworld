package org.sopt.week6to8.repository;


import org.sopt.week6to8.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

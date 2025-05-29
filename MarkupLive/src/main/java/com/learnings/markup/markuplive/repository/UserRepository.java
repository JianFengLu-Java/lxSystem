package com.learnings.markup.markuplive.repository;

import com.learnings.markup.markuplive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByAccount(Long account);//判断账号是否存在
    User findByAccount(Long account);//根据账号查找用户
}

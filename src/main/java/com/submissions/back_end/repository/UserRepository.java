package com.submissions.back_end.repository;

import com.submissions.back_end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT U FROM User U WHERE U.last_name LIKE %?1%")
    List<User> findAllByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT U FROM User U WHERE U.user_name=?1")
    User findByUserName(@Param("user_name") String user_name);

    @Query("SELECT U FROM User U WHERE U.user_name=:user_name and U.password=:password")
    User checkLogin(@Param("user_name") String user_name,@Param("password") String password);
}

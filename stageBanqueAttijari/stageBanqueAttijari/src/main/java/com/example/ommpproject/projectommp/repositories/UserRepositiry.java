package com.example.ommpproject.projectommp.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ommpproject.projectommp.models.User;
@Repository
public interface UserRepositiry extends JpaRepository<User, Long> {
@Query("SELECT u FROM User u WHERE u.email = :email AND ROWNUM <= 1")

Optional<User> findByEmail(String email);

//@Query("SELECT port FROM User u WHERE u.id= ?1")
//List <Port> getPort(Long userid);
@Modifying
@Transactional
@Query("UPDATE User u SET u.firstLogin = :firstLogin WHERE u.idUser = :idUser")
int updateFirstLoginStatus(@Param("idUser") Long idUser, @Param("firstLogin") boolean firstLogin);

}

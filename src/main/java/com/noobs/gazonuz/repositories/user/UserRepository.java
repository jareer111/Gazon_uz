package com.noobs.gazonuz.repositories.user;

import com.noobs.gazonuz.domains.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}

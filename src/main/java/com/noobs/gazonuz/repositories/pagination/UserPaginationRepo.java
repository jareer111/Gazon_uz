package com.noobs.gazonuz.repositories.pagination;

import com.noobs.gazonuz.domains.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface UserPaginationRepo extends PagingAndSortingRepository<User, String> {

    int PER_PAGE = 10;

    //    @Query( "select u from User u where u.username ilike ?1" )
    @Query( "select u from User u where upper(u.username) like upper(concat('%', ?1, '%')) order by u.createdAt" )
    List<User> findByUsernameContainsIgnoreCase(String username , Pageable pageable);

//    List<User> findByUsernameLikeIgnoreCase(String username , Pageable pageable);


    @Query( "select u from User u order by u.createdAt" )
    Page<User> findAll(Pageable pageable);

    @Query( "select count(u) from User u where upper(u.username) like upper(concat('%', ?1, '%'))" )
    Long countUsersThatMatches(String username);

//    Page<User> findAll(@NonNull Pageable pageable);
}

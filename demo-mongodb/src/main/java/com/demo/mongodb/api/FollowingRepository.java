package com.demo.mongodb.api;

import com.demo.mongodb.entity.Following;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowingRepository extends CrudRepository<Following, String> {

    boolean existsByFollowerIdAndFollowedId(String follower, String followed);

    Optional<Following> findByFollowerIdAndFollowedId(String follower, String followed);

    long countByFollowerId(String ghostId);

    List<Following> findByFollowerIdOrderByCreatedDateDesc(String ghostId, Pageable pageable);

    long countByFollowedId(String ghostId);

    List<Following> findByFollowedIdOrderByCreatedDateDesc(String ghostId, Pageable pageable);

    long deleteByFollowerIdAndFollowedId(String follower, String followed);
}

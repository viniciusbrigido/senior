package com.brigido.senior.repository;

import com.brigido.senior.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID>, VoteRepositoryCustom {
}

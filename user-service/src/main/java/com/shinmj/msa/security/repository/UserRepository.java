package com.shinmj.msa.security.repository;

import com.shinmj.msa.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName :  com.shinmj.msa.security.repository
 * fileName : UserRepository
 * author :  home
 * date : 22. 9. 12.
 * description :
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
}

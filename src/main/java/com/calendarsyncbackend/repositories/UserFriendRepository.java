package com.calendarsyncbackend.repositories;

import com.calendarsyncbackend.models.UserFriend;
import com.calendarsyncbackend.models.UserFriendId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendRepository extends JpaRepository<UserFriend, UserFriendId> {
}
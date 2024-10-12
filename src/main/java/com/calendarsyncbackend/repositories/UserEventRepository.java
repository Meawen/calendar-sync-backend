package com.calendarsyncbackend.repositories;

import com.calendarsyncbackend.models.UserEvent;
import com.calendarsyncbackend.models.UserEventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, UserEventId> {
}
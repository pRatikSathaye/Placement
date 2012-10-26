package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.web.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, String> {
}

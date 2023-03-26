package com.cbs.springcourse.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cbs.springcourse.rest.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}

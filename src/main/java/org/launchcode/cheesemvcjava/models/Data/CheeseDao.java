package org.launchcode.cheesemvcjava.models.Data;

import org.launchcode.cheesemvcjava.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}

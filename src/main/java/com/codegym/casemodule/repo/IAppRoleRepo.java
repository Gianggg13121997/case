package com.codegym.casemodule.repo;

import com.codegym.casemodule.model.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends CrudRepository<AppRole, Long> {
    AppRole findByName(String name);
}

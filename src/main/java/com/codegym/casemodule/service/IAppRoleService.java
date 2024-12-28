package com.codegym.casemodule.service;

import com.codegym.casemodule.model.AppRole;

public interface IAppRoleService extends IGenerateService<AppRole>{
    AppRole findByName(String name);
}

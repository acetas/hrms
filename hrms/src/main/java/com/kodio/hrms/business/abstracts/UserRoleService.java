package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.entities.concretes.UserRole;

public interface UserRoleService {

	DataResult<UserRole> add(UserRole userRole);
	DataResult<UserRole> update(int id, UserRole userRole);
	Result delete(int id);
	DataResult<List<UserRole>> getAll();
	
}

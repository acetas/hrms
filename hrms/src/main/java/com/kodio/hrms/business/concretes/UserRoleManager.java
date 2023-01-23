package com.kodio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.UserRoleService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.UserRoleRepository;
import com.kodio.hrms.entities.concretes.UserRole;

@Service
public class UserRoleManager implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public DataResult<UserRole> add(UserRole userRole) {
		userRoleRepository.save(userRole);
		return new SuccessDataResult<UserRole>(userRole, "User role added");
	}

	@Override
	public DataResult<UserRole> update(int id, UserRole userRole) {
		UserRole role = userRoleRepository.findById(id).get();
		
		role.setName(userRole.getName());
		
		return new SuccessDataResult<UserRole>(role, "User role edited");
	}

	@Override
	public Result delete(int id) {
		userRoleRepository.deleteById(id);
		return new SuccessResult("User role deleted");
	}

	@Override
	public DataResult<List<UserRole>> getAll() {
		List<UserRole> roles = userRoleRepository.findAll();
		return new SuccessDataResult<List<UserRole>>(roles, "User Roles listed");
	}

}

package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception{
		
		User local_user=this.userRepository.findByUsername(user.getUsername());
		if(local_user!=null)
		{
			System.out.println("user already exist!");
			throw new Exception("user already exist!");
		}
		else {
			// user create
			for(UserRole ur:userRole)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRole().addAll(userRole);
			local_user=this.userRepository.save(user);
		}
		
		return local_user ;
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User editUser(User user, String username) {
		
		User u=userRepository.findByUsername(username);
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		u.setMobileNo(user.getMobileNo());
		u.setProfile(user.getProfile());
		u.setUserRole(user.getUserRole());
		
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(long uid) {
		
		userRepository.deleteById(uid);
		
	}	
	
}

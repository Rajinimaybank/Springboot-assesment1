package com.intellect.user.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellect.common.CommonConstants;
import com.intellect.common.ValidationError;
import com.intellect.user.domain.User;
import com.intellect.user.dto.UserDTO;
import com.intellect.user.dto.UserEntityResponse;
import com.intellect.user.dto.UserResponse;
import com.intellect.user.repository.UserRepository;
import com.intellect.user.validation.UserValidation;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;
	@Autowired
	UserValidation validation;
	@Autowired
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intellect.user.service.UserService#saveUser(com.intellect.user.dto.
	 * UserRequest)
	 */
	@Override
	public UserResponse saveUser(UserDTO request) {
		List<ValidationError> errorList = validation.userValidation(request);
		if (!errorList.isEmpty()) {
			return new UserResponse(CommonConstants.FAILURE, null, errorList);
		}
		ModelMapper mapper = new ModelMapper();
		User userObj = mapper.map(request, User.class);
		userObj.setIsActive(true);
		User response = repository.save(userObj);
		if (null != response) {
			return new UserResponse(CommonConstants.SUCCESS, response.getId() + "", errorList);
		}
		return new UserResponse(CommonConstants.FAILURE, CommonConstants.DB_ERR, errorList);
	}

	@Override
	@Transactional
	public String deleteUser(Long userId) {
		Query query = em.createQuery("UPDATE User SET isActive = false where id=:id");
		query.setParameter("id", userId);
		int updateCount = query.executeUpdate();
		return updateCount == 0 ? "Failure : Unable to Delete The Record"
				: "Success" + userId + " is Deleted Scuccessfully:";
	}

	@Override
	public UserEntityResponse findUser(Long id) {
		User userObj = repository.findOne(id);
		if(null!=userObj){
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userObj, UserEntityResponse.class);
		} 
		return new UserEntityResponse();
	}

}

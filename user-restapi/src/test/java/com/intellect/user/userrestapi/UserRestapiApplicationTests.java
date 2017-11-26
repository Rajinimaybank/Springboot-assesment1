package com.intellect.user.userrestapi;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intellect.common.ValidationError;
import com.intellect.user.domain.User;
import com.intellect.user.dto.UserDTO;
import com.intellect.user.dto.UserResponse;
import com.intellect.user.repository.UserRepository;
import com.intellect.user.service.UserServiceImpl;
import com.intellect.user.validation.UserValidation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestapiApplicationTests {

	@Mock
	UserRepository repository;
	@Mock
	UserValidation validation;
	@Mock
	EntityManager em;
	@InjectMocks
	UserServiceImpl service;
	
	@Test
	public void saveUser() {
		DateTime date =new DateTime();
		String dateString="22-Mar-2017";
		
		
		// given
		BDDMockito
		.given(repository.save(Matchers.any(User.class)))
		.willReturn(getUser(date));
		
		BDDMockito
		.given(validation.userValidation(Matchers.any(UserDTO.class)))
		.willReturn(new ArrayList<>());
		
		
		//when
		UserResponse stub=getUserResponse();
		UserResponse  expected=service.saveUser(getUserDTO(dateString));
		Assert.assertThat(stub, sameBeanAs(expected));
		
	}
	
	public User getUser(DateTime date){
		
		User user=new User();
		user.setBirthDate(date);
		user.setEmail("rajini.dwa@gmail.com");
		user.setfName("Rajini");
		user.setlName("K");
		user.setId(1l);
		user.setIsActive(true);
		user.setPinCode(123456);
		return user;
	}
	
	public UserResponse getUserResponse(){
		 List<ValidationError> errors= new ArrayList<ValidationError>();
	UserResponse userDto=new UserResponse("success", "1",errors);
		
		return userDto;
		
	}
	
	public UserDTO getUserDTO(String dateString){
		UserDTO userDto=new UserDTO();
		userDto.setBirthDate(dateString);
		userDto.setEmail("rajini.dwa@gmail.com");
		userDto.setfName("Rajini");
		userDto.setlName("K");
		userDto.setPinCode(123456);
		return userDto;
		
		
	}

}

package com.epam.jms_testing.dto;

import com.epam.jms_testing.dao.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String fullName;
	private String userName;
	private String password;

	public UserEntity toEntity(){
		return new UserEntity(fullName, userName, password);
	}
}

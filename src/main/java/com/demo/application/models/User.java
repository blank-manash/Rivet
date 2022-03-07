package com.demo.application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@NonNull
public class User {
	private String firstName;
	private String lastName;
	private Long rivetId;
	private String password;
	private Long phoneNumber;
	private String cityName;
}

package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//261014 06분 녹음듣기
@Builder  
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")

public class TodoEntity {
	@Id
 	@GeneratedValue(strategy=GenerationType.UUID) //uuid 유니크 아이덴티피케이션, 
	private String id; //단 하나의 유니크한 정보 pkey
	private String userId; //user_id in repository  
	//Data에 의해서 getter, setter를 자동생성, 들어가는 인자이름을 
	//userId ->getUserId : user_is ->getUser_Id
	//테이블은 그대로 가지만 내부 변수는 전부 소문자로 간다, 
	private String title;
	private boolean done;
	
	
}

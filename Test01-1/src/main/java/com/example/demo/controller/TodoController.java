package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;


@RestController
@RequestMapping("todo")
public class TodoController {
	@Autowired
	private TodoService service;
	

// TodoController(TodoService service) {
//      this.service = service;
//  }
	
	@GetMapping("/test")
	public ResponseEntity<?> todoTest(){
		String str= service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = 
				ResponseDTO.<String>builder().data(list).error(str).build();
		return ResponseEntity.ok().body(response);
		}
	
	@PostMapping() //내부 에 추가path 안넣었어 그래서 이걸 쓸떄 통신방식에 따라 따로 따로 ~~
	//http://localhost:8080/todo로 하는데 post get 에 따라 리트라이브 인서트 따로따로 실행,,  서버 꺼지면 데이터 사라졍~
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId="temporary-user";
			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setId(null);
			entity.setUserId(temporaryUserId);
			
			List<TodoEntity> entities = service.create(entity);
			List<TodoDTO> dtos =entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response =ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList(){
	    String temporaryUserId = "temporary-user";
	    List<TodoEntity> entities = service.retrieve(temporaryUserId); //261019 중간고사,, .stream(),, retrieve..  
	    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
	    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	    return ResponseEntity.ok().body(response);
	}
	//261021 12분 녹음 듣기
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		 String temporaryUserId = "temporary-user";
		 
		 TodoEntity entity = TodoDTO.toEntity(dto);
		 
		 entity.setUserId(temporaryUserId);
		 List<TodoEntity> entities = service.update(entity);
		 List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		 ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		 return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
	    try {
	        String temporaryUserId = "temporary-user";
	        TodoEntity entity = TodoDTO.toEntity(dto);
	        entity.setUserId(temporaryUserId);
	        List<TodoEntity> entities = service.delete(entity);
	        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

	        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

	        return ResponseEntity.ok().body(response);
	    } catch (Exception e) {
	        String error = e.getMessage();
	        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
	        return ResponseEntity.badRequest().body(response);
	    }
	}


}



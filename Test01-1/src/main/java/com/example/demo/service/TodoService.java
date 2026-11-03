package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

@Service
public class TodoService {
	
	private final TodoRepository repository;
	
	TodoService(TodoRepository repository){
		this.repository=repository;
	}
	public String testService(){
		TodoEntity entity=TodoEntity.builder().title("My First todo item").build();
		//System.out.println(entity.get_user_id())
		repository.save(entity);
		TodoEntity savedEntity=repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity){
		validate(entity);
		repository.save(entity);
		System.out.println("Added entity id:" + entity.getId());
		
		
		return repository.findByUserId(entity.getUserId());
	}
	
	
	public List<TodoEntity> retrieve(final String userId){
		return repository.findByUserId(userId);
		
	}

	public List<TodoEntity> update(final TodoEntity entity){
		validate(entity);
		
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		if(original.isPresent()) {
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			repository.save(todo);
		}
		
		return retrieve(entity.getUserId());
		
		
	}

	
	public List<TodoEntity> delete(final TodoEntity entity){
	    validate(entity);

	    try {
	        repository.delete(entity);
	    } catch (Exception e) {
	        System.out.println("error deleting entity"+entity.getId()+"\n"+e);
	        throw new RuntimeException("error deleting entity"+entity.getId()+"\n"+e);
	    }

	    return retrieve(entity.getUserId());
	}
	
	private void validate(final TodoEntity entity) {
		if(entity==null) {
			System.out.println("Entity can not be null");
			throw new RuntimeException("Entity can not be null");
			
		}
		if(entity.getUserId()==null) {
			System.out.println("Unknown user");
			throw new RuntimeException("Entity can not be null");
			
		}
	}
}

package lab.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lab.logic.Pet;
import lab.logic.PetModel;

@RestController
public class Controller {
	private static final PetModel petModel= PetModel.getInstance();
	private static final AtomicInteger newid=new AtomicInteger(1);
	
	@PostMapping(value="/createPet",consumes = "application/json",produces = "application/json")
	public Map<String,String> createPet(@RequestBody Pet pet) {
	    Map<String,String> res=new HashMap<>();
		if(newid.get()==1)
			res.put("message","Вы добавили первого питомца");
		else
			res.put("message","Вы добавили питомца");
		petModel.add(pet, newid.getAndIncrement());	    
		return res;
	}
	@GetMapping(value="/getAll",produces = "application/json")
	public Map<Integer,Pet> getAll(){
		return petModel.getAll();
	}
	@GetMapping(value="/getPet",consumes = "application/json",produces = "application/json")
	public Pet getPett(@RequestBody Map<String,Integer> id) {
		return petModel.getFromList(id.get("id"));	
	}
	
	@DeleteMapping(value="/deletePet",consumes = "application/json",produces = "application/json")
	public Map<String,String> deletePet(@RequestBody Map<String,Integer> id) {
	    Map<String,String> res=new HashMap<>();
	    res.put("message","Вы удалили питомца");
		petModel.del(id.get("id"));	    
		return res;
	}
	@PutMapping(value="/putPet",consumes = "application/json",produces = "application/json")
	public Map<String, String> putPet(@RequestBody Map<String, Object> requestBody) {
	    Map<String,String> map=new HashMap<>();
		map.put("message","Вы изменили питомца");
		Pet pet= new Pet( (String)requestBody.get("name"), (String)requestBody.get("type"), (Integer)requestBody.get("age"));
		petModel.add(pet, (Integer)requestBody.get("id"));	    
		return map;
	}
}

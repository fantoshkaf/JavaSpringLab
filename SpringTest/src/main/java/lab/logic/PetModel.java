package lab.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable{
	private static final  PetModel instance = new PetModel();
	private final Map<Integer,Pet> model;
	public static PetModel getInstance() {
		return instance;
	}
	private PetModel() {
		
		model = new HashMap<>();


	}
	public void add(Pet pet, int id) {
		model.put(id,pet);
	}
	public void del(int id) {
		model.remove(id);
	}
	public Pet getFromList(int id){
		return model.get(id);
	}
	public Map<Integer,Pet> getAll(){
		return model;
	}
}

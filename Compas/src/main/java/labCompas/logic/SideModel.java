package labCompas.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class SideModel implements Serializable{
	private static final  SideModel instance = new SideModel();
	private final Map<String,Side> model;
	public static SideModel getInstance() {
		return instance;
	}
	private SideModel() {
		
		model = new HashMap<>();


	}
	public void set(String name,Side side) {
		model.put(name,side);
	}
	public Map<String,Side> getAll(){
		return model;
	}
	public Side getFromList(String name){
		return model.get(name);
	}
}

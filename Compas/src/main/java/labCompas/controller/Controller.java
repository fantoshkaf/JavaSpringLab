package labCompas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import labCompas.logic.Side;
import labCompas.logic.SideModel;

@RestController
public class Controller {
	private static final SideModel sideModel = SideModel.getInstance();

	@PostMapping(value = "/setSides", consumes = "application/json", produces = "application/json")

	public Map<String, String> createSide(@RequestBody String jsonString) {
		Gson gson = new GsonBuilder().create();
		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonArray sidesArray = jsonObject.getAsJsonArray("sides");

		for (int i = 0; i < sidesArray.size(); i++) {
			JsonObject sideObject = sidesArray.get(i).getAsJsonObject();
			String sideName = sideObject.get("side").getAsString();
			JsonObject coordinates = sideObject.getAsJsonObject("coordinates");

			double mincord = coordinates.get("mincord").getAsDouble();
			double maxcord = coordinates.get("maxcord").getAsDouble();

			sideModel.set(sideName, new Side(mincord, maxcord));
			


		}
		Map<String, String> res = new HashMap<>();

		res.put("message", "Вы задали координаты");

		return res;
	}

	@PostMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
	public Map<String, String> getSide(@RequestBody Map<String, Double> degree) {
		double deg = degree.get("Degree");
		Map<String, String> res = new HashMap<>();
		if (deg < 0 || deg > 359) {
			res.put("message", "Градусы должны быть в диапазоне от 0 до 359");
		} else {

			for (Map.Entry<String, Side> entry : sideModel.getAll().entrySet()) {

				Side side = entry.getValue();
				double minDegree = side.getMinDegree();
				double maxDegree = side.getMaxDegree();
				if (minDegree > maxDegree) // например 300-90
				{
					if (deg <= maxDegree || deg >= minDegree) {
						res.put("Side", entry.getKey());
						return res;
					}
				} else {
					// обычная ситуация
					if (deg >= minDegree && deg <= maxDegree) {
						res.put("Side", entry.getKey());
						return res;
					}
				}
			}
		}
	    if (res.isEmpty()) {
	        res.put("message", "Сторона света не найдена для указанного градуса.");
	    }
		return res;

	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public Map<String, Side> getAll() {
		return sideModel.getAll();
	}

}

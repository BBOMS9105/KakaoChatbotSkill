package com.study.springboot;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatbotController {

	@RequestMapping("/chat")
	@ResponseBody
	public JSONObject test(
				@RequestBody	// post 방식으로 저장
				Map map
			) {
		System.out.println(map);
		
		// Map에서 변수 가져오기
		Map map_action = (Map) map.get("action");
		Map map_detailParams = (Map) map_action.get("detailParams");
		Map map_meat = (Map) map_detailParams.get("meat");
		String meat = (String) map_meat.get("origin");
		
		Map map_sys_number = (Map) map_detailParams.get("sys_number");
		String portion = (String) map_sys_number.get("origin");
		
		System.out.println("meat : " + meat);
		System.out.println("portion : " + portion);
		
		
//		String result = "{"
//				+ "    \"version\": \"2.0\","
//				+ "    \"template\": {"
//				+ "        \"outputs\": ["
//				+ "            {"
//				+ "                \"simpleText\": {"
//				+ "                    \"text\": \"meat : " + meat + "\\nportion : " + portion + "\""
//				+ "                }"
//				+ "            }"
//				+ "        ]"
//				+ "    }"
//				+ "}";

		// 가장 안쪽 json부터 작성
		JSONObject send = new JSONObject();
		send.put("version", "2.0");
		
		JSONObject simpleText = new JSONObject();
		String message = "meat : " + meat + ", portion : " + portion;
		simpleText.put("text", message);
		
		JSONObject outputs1 = new JSONObject();
		outputs1.put("simpleText", simpleText);
		
		// JSONArray
		JSONArray outputsArr = new JSONArray();
		outputsArr.add(outputs1);
		
		JSONObject template = new JSONObject();
		template.put("outputs", outputsArr);
		
		send.put("template", template);
		
		
		return send;
	}
}



















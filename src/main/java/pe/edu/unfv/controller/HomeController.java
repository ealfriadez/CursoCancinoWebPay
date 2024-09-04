package pe.edu.unfv.controller;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import pe.edu.unfv.model.CrearTokenModel;
import pe.edu.unfv.model.RequestModel;
import pe.edu.unfv.model.ResponseVerifiedModel;
import pe.edu.unfv.util.Constantes;


@Controller
@RequestMapping("")
public class HomeController {

	@GetMapping("")
	public String home() {
		return "home/home";
	}
	
	@GetMapping("/pagar")
	public String pagar(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Tbk-Api-Key-Secret", Constantes.WEBPAY_CODIGO_SECRETO);
		headers.set("Tbk-Api-Key-Id", Constantes.WEBPAY_CODIGO_COMERCIO);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		RequestModel post = new RequestModel("ordenCompra12345678", "session1234557545", 1000, "http://localhost:7050/curso_cancino_webpay/respuesta");
		
		//construimos el json request		
		HttpEntity<RequestModel> request = new HttpEntity<>(post, headers);
		
		//enviamos la peticion post
		ResponseEntity<CrearTokenModel> response = restTemplate.postForEntity(Constantes.WEBPAY_URL, request, CrearTokenModel.class);
		
		model.addAttribute("response", response.getBody());
		
		return "home/pagar";
	}
	
	@GetMapping("/respuesta")
	public String respuesta(Model model, @RequestParam("token_ws") String token_ws) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Tbk-Api-Key-Secret", Constantes.WEBPAY_CODIGO_SECRETO);
		headers.set("Tbk-Api-Key-Id", Constantes.WEBPAY_CODIGO_COMERCIO);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ResponseVerifiedModel> response = restTemplate.exchange(Constantes.WEBPAY_URL + "/" + token_ws, HttpMethod.PUT, entity, ResponseVerifiedModel.class);
		
		ResponseVerifiedModel responseVerifiedModel = response.getBody();
		model.addAttribute("response", responseVerifiedModel);
		model.addAttribute("token_ws", token_ws);		
		
		return "home/respuesta";
	}	
}

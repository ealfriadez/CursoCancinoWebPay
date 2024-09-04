package pe.edu.unfv.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buy_order;
	private String session_id;
	private Integer amount;
	private String return_url;
}

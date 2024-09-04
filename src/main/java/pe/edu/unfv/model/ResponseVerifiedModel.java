package pe.edu.unfv.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVerifiedModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vci;
	private Integer amount;
	private String status;
	private String buy_order;
	private String session_id;
	private Object card_detail;
	private String accounting_date;
	private String transaction_date;
	private String authorization_code;
	private String payment_type_code;
	private Integer response_code;
	private Integer installments_number;
}

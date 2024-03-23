package mws.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class RequestResult<T> implements Serializable{
	int code;
	String message;
	boolean success;
	List<T> data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "RequestResult [code=" + code + ", message=" + message + ", success=" + success + ", data=" + data + "]";
	}
	public RequestResult(int code, String message, boolean success, List<T> data) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
		this.data = data;
	}
	public RequestResult() {
		super();
		// TODO Auto-generated constructor stub
	}
}

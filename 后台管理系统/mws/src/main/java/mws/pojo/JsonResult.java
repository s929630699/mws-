package mws.pojo;

public class JsonResult {
	private Boolean result;
	private String errorMsg;
	private String successMsg;
	private Object resultData;
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public Object getResultData() {
		return resultData;
	}
	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	@Override
	public String toString() {
		return "JsonResult [result=" + result + ", errorMsg=" + errorMsg + ", successMsg=" + successMsg
				+ ", resultData=" + resultData + "]";
	}
	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JsonResult(Boolean result, String errorMsg, String successMsg, Object resultData) {
		super();
		this.result = result;
		this.errorMsg = errorMsg;
		this.successMsg = successMsg;
		this.resultData = resultData;
	}
	

}

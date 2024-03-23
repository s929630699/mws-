package mws.pojo;

public class Banner {
	private Integer id;
	private String imgsrc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public String toString() {
		return "Banner [id=" + id + ", imgsrc=" + imgsrc + "]";
	}
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Banner(Integer id, String imgsrc) {
		super();
		this.id = id;
		this.imgsrc = imgsrc;
	}
	

}

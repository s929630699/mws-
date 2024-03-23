package mws.pojo;

public class Catesitem {
	private Integer id;
	private String name;
	private String imgsrc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public String toString() {
		return "Catesitem [id=" + id + ", name=" + name + ", imgsrc=" + imgsrc + "]";
	}
	public Catesitem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Catesitem(Integer id, String name, String imgsrc) {
		super();
		this.id = id;
		this.name = name;
		this.imgsrc = imgsrc;
	}
	
	

}

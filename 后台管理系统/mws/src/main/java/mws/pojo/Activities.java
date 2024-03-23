package mws.pojo;

import java.util.Date;

public class Activities {
	private Integer id;
	private String name;
	private String imgurl;
	private Date beginDate;
	private Date deadLine;
	private String introduce;
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "Activities [id=" + id + ", name=" + name + ", imgurl=" + imgurl + ", beginDate=" + beginDate
				+ ", deadLine=" + deadLine + ", introduce=" + introduce + "]";
	}
	public Activities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Activities(Integer id, String name, String imgurl, Date beginDate, Date deadLine, String introduce) {
		super();
		this.id = id;
		this.name = name;
		this.imgurl = imgurl;
		this.beginDate = beginDate;
		this.deadLine = deadLine;
		this.introduce = introduce;
	}
	

}

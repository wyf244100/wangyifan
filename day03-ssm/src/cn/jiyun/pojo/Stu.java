package cn.jiyun.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Stu {
	private Integer id;
	private String name;
	private String sex;
	private String pic;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date	sr;
	private Integer cid;
	private Clazz	clazz;
	
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getSr() {
		return sr;
	}
	public void setSr(Date sr) {
		this.sr = sr;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + ", sex=" + sex + ", pic=" + pic + ", sr=" + sr + ", cid=" + cid
				+ ", clazz=" + clazz + "]";
	}
	
	
	
}

package cn.jiyun.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Emp {

	private Integer eid;
	private String ename;
	private String sex;
	private String hobby;
	private String img;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	private Integer did;
	
	private String addr;
	
	private Dept dept;

	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", hobby=" + hobby + ", img=" + img
				+ ", birthday=" + birthday + ", did=" + did + ", addr=" + addr + ", dept=" + dept + "]";
	}

	public Emp(Integer eid, String ename, String sex, String hobby, String img, Date birthday, Integer did, Dept dept) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
		this.hobby = hobby;
		this.img = img;
		this.birthday = birthday;
		this.did = did;
		this.dept = dept;
	}

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}

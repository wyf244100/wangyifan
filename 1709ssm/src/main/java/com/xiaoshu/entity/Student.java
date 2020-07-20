package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

public class Student implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String sex;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    
    @Transient
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ks;
    
    @Transient
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date js;

    private String hobby;

    private Integer gid;
    @Transient
    private Grade grade;

    
    
    public Date getKs() {
		return ks;
	}

	public void setKs(Date ks) {
		this.ks = ks;
	}

	public Date getJs() {
		return js;
	}

	public void setJs(Date js) {
		this.js = js;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * @param hobby
     */
    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    /**
     * @return gid
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * @param gid
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", hobby=").append(hobby);
        sb.append(", gid=").append(gid);
        sb.append("]");
        return sb.toString();
    }
}
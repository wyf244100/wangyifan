package cn.jiyun.pojo;

public class City {
	
	private Integer cid;
	private String cname;
	private Integer fid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	@Override
	public String toString() {
		return "City [cid=" + cid + ", cname=" + cname + ", fid=" + fid + "]";
	}
	
	

}

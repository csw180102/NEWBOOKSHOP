package spring.mvc.bms.member.vo;

import java.sql.Timestamp;

public class MemberVO {
	/*id          VARCHAR2(20),
    pwd         VARCHAR2(10) NOT NULL,
    name        VARCHAR2(20),
    jumin       VARCHAR2(14) NOT NULL,
    hp          VARCHAR2(13),
    email       VARCHAR2(30) NOT NULL,
    reg_date    TIMESTAMP DEFAULT sysdate,
    CONSTRAINT mvc_member_id_pk PRIMARY KEY(id),
    CONSTRAINT mvc_member_email_uk UNIQUE(email)*/
	
	private String id;
	private String pwd;
	private String name;
	private String jumin;
	private String hp;
	private String email;
	private Timestamp reg_date;
	private String birth;
	private String address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

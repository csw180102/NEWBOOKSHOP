package spring.mvc.bms.board.vo;

import java.sql.Timestamp;

public class OrderListVO {
	/*buy_seq     NUMBER(5),
    id          VARCHAR(10) NOT NULL,
    book_seq    NUMBER(5),
    buy_date    TIMESTAMP default sysdate,
    quantity    NUMBER(5)*/
	
	private int buy_seq;
	private String id;
	private int book_seq;
	private Timestamp buy_date;
	private int quantity;
	private Timestamp approval_date;
	
	
	public Timestamp getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Timestamp approval_date) {
		this.approval_date = approval_date;
	}
	public int getBuy_seq() {
		return buy_seq;
	}
	public void setBuy_seq(int buy_seq) {
		this.buy_seq = buy_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBook_seq() {
		return book_seq;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}
	public Timestamp getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

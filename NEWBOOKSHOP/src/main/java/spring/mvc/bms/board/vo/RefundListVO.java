package spring.mvc.bms.board.vo;

import java.sql.Timestamp;

public class RefundListVO {
	
	private int buy_seq;
	private int refund_seq;
	private String id;
	private int book_seq;
	private int quantity;
	private Timestamp refund_date;
	private Timestamp finalrefund_date;
	
	public int getRefund_seq() {
		return refund_seq;
	}
	public void setRefund_seq(int refund_seq) {
		this.refund_seq = refund_seq;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public Timestamp getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(Timestamp refund_date) {
		this.refund_date = refund_date;
	}
	public Timestamp getFinalrefund_date() {
		return finalrefund_date;
	}
	public void setFinalrefund_date(Timestamp finalrefund_date) {
		this.finalrefund_date = finalrefund_date;
	}
	
	
	
	
}

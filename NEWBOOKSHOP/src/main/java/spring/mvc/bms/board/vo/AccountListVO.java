package spring.mvc.bms.board.vo;

import java.sql.Timestamp;

public class AccountListVO {
	
	private int close_seq;
	private int buy_seq;
	private int refund_seq;
	private String id;
	private int book_seq;
	private int quantity;
	private String ar_check;
	private Timestamp ar_date;
	private int month_sales_inflow;
	private int month_sales_outflow;
	
	
	
	public int getMonth_sales_inflow() {
		return month_sales_inflow;
	}
	public void setMonth_sales_inflow(int month_sales_inflow) {
		this.month_sales_inflow = month_sales_inflow;
	}
	public int getMonth_sales_outflow() {
		return month_sales_outflow;
	}
	public void setMonth_sales_outflow(int month_sales_outflow) {
		this.month_sales_outflow = month_sales_outflow;
	}
	public int getRefund_seq() {
		return refund_seq;
	}
	public void setRefund_seq(int refund_seq) {
		this.refund_seq = refund_seq;
	}
	public int getClose_seq() {
		return close_seq;
	}
	public void setClose_seq(int close_seq) {
		this.close_seq = close_seq;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAr_check() {
		return ar_check;
	}
	public void setAr_check(String ar_check) {
		this.ar_check = ar_check;
	}
	public Timestamp getAr_date() {
		return ar_date;
	}
	public void setAr_date(Timestamp ar_date) {
		this.ar_date = ar_date;
	}


	
}

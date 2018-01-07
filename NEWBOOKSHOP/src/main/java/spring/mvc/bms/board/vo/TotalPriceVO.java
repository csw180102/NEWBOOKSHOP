package spring.mvc.bms.board.vo;

public class TotalPriceVO {
	
	private int wish_amount;
	private int order_amount;
	private int admin_order_amount;
	
	
	public int getAdmin_order_amount() {
		return admin_order_amount;
	}

	public void setAdmin_order_amount(int admin_order_amount) {
		this.admin_order_amount = admin_order_amount;
	}

	public int getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}

	public int getWish_amount() {
		return wish_amount;
	}

	public void setWish_amount(int wish_amount) {
		this.wish_amount = wish_amount;
	}
	
	
}

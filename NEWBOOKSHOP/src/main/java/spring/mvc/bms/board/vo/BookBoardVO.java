package spring.mvc.bms.board.vo;

import java.sql.Timestamp;

public class BookBoardVO {
	/*book_seq        NUMBER(10),
    isbn            VARCHAR2(10) NOT NULL,
    book_name       VARCHAR2(30) NOT NULL,
    author          VARCHAR2(20) NOT NULL,
    publisher       VARCHAR2(20) NOT NULL,
    domeforei       VARCHAR2(10) NOT NULL,
    newold          VARCHAR2(10) NOT NULL,
    price           NUMBER(6) NOT NULL,
    stock           NUMBER(4) NOT NULL,
    receiving_day   TIMESTAMP DEFAULT sysdate,*/
	//quantity      NUMBER(5) 추가하자
	
	private String image;
	private int book_seq;
	private String isbn;
	private String book_name;
	private String author;
	private String publisher;
	private String domeforei;
	private String newold;
	private int price;
	private int stock;
	private Timestamp receiving_day;
	private String content;
	private int quantity;
	
	
	/* 매핑용 클래스 refundListVO */
	private RefundListVO refundlistvo;
	/* 매핑용 클래스 OrderListVO */
	private OrderListVO orderlistvo;
	
	
	
	public RefundListVO getRefundlistvo() {
		return refundlistvo;
	}
	public void setRefundlistvo(RefundListVO refundlistvo) {
		this.refundlistvo = refundlistvo;
	}
	public OrderListVO getOrderlistvo() {
		return orderlistvo;
	}
	public void setOrderlistvo(OrderListVO orderlistvo) {
		this.orderlistvo = orderlistvo;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBook_seq() {
		return book_seq;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDomeforei() {
		return domeforei;
	}
	public void setDomeforei(String domeforei) {
		this.domeforei = domeforei;
	}
	public String getNewold() {
		return newold;
	}
	public void setNewold(String newold) {
		this.newold = newold;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Timestamp getReceiving_day() {
		return receiving_day;
	}
	public void setReceiving_day(Timestamp receiving_day) {
		this.receiving_day = receiving_day;
	}
	
	
}
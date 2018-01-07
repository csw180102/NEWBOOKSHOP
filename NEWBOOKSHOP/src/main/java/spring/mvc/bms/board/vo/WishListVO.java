package spring.mvc.bms.board.vo;

public class WishListVO {
	
	private int wishlist_seq;
	private int book_seq;
	private String id;
	private String book_name;
	private String author;
	private String publisher;
	private int price;
	private int quantity;
	
	
	public int getWishlist_seq() {
		return wishlist_seq;
	}
	public void setWishlist_seq(int wishlist_seq) {
		this.wishlist_seq = wishlist_seq;
	}
	public int getBook_seq() {
		return book_seq;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}

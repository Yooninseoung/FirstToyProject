package manage;

import java.sql.Date;

public class OrderVO {
	private int level;
	private int orderNO;
	private int parentNO;
	private String content;
	private int ok;
	private String id;
	private Date orderDate;
	
	

	public OrderVO(int parentNO, String content, int ok, String id) {
		this.parentNO = parentNO;
		this.content = content;
		this.ok = ok;
		this.id = id;
	}

	public OrderVO(int orderNO, int parentNO, String content, int ok, String id) {
		this.orderNO = orderNO;
		this.parentNO = parentNO;
		this.content = content;
		this.ok = ok;
		this.id = id;
	}
	
	public OrderVO(int level, int orderNO, int parentNO, String content, int ok, String id, Date orderDate) {
		this.level = level;
		this.orderNO = orderNO;
		this.parentNO = parentNO;
		this.content = content;
		this.ok = ok;
		this.id = id;
		this.orderDate = orderDate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}
	public int getParentNO() {
		return parentNO;
	}
	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOk() {
		return ok;
	}
	public void setOk(int ok) {
		this.ok = ok;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}

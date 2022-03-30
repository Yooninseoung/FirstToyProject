package manage;

public class MenuVO {
	private int level;
	private int menuNO;
	private int parentNO;
	private String food;
	private int price;
	private String imageFileName;
	
public MenuVO() {
	
}


public MenuVO(int menuNO, int parentNO, String food, int price, String imageFileName) {
	this.menuNO = menuNO;
	this.parentNO = parentNO;
	this.food = food;
	this.price = price;
	this.imageFileName = imageFileName;
}

public MenuVO(int level, int menuNO, int parentNO, String food, int price, String imageFileName) {
	this.level=level;
	this.menuNO = menuNO;
	this.parentNO = parentNO;
	this.food = food;
	this.price = price;
	this.imageFileName = imageFileName;
}

public int getLevel() {
	return level;
}

public void setLevel(int level) {
	this.menuNO = level;
}

public int getMenuNO() {
	return menuNO;
}

public void setMenuNO(int menuNO) {
	this.menuNO = menuNO;
}

public int getParentNO() {
	return parentNO;
}

public void setParentNO(int parentNO) {
	this.parentNO = parentNO;
}

public String getFood() {
	return food;
}

public void setFood(String food) {
	this.food = food;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public String getImageFileName() {
	return imageFileName;
}

public void setImageFileName(String imageFileName) {
	this.imageFileName = imageFileName;
}


}

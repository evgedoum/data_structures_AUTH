//This class represents the supplies of the game


public class Supply {
	private int supplyId; // Id of supply
	private int x, y; // coordinates of supply
	private int supplyTileId; //id of supply's tile
	

// Constructors of the class
	public Supply() {
		supplyId = 0;
		x = 0;
		y = 0;
		supplyTileId = 0;
	}
	
	public Supply(int supplyId, int x, int y, int supplyTileId) {
		this.supplyId = supplyId;
		this.x = x;
		this.y = y;
		this.supplyTileId = supplyTileId;
	}
	
	public Supply(Supply supplyOb) {
		supplyId = supplyOb.supplyId;
		x = supplyOb.x;
		y = supplyOb.y;
		supplyTileId = supplyOb.supplyTileId;
	}

// Setters & Getters for all the variables
	public void setSupplyId(int si) {
		supplyId = si;
	}
	public int getSupplyId() {
		return supplyId;
	}
	public void setX(int inx) {
		x = inx;
	}
	public int getX() {
		return x;
	}
	public void setY(int iny) {
		y = iny;
	}
	public int getY() {
		return y;
	}
	public void setSupplyTileId(int sti) {
		supplyTileId = sti;
	}
	public int getSupplyTileId() {
		return supplyTileId;
	}
}

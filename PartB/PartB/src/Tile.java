//This class represents the tiles of the board

public class Tile {
	private int tileId; // Id of tile
	private int x, y; //coordinates of supply
	boolean up, down, left, right; // existence of a wall up, down, left or right in a tile
	
//Constructors of the class
	public Tile() {
		tileId = 0;
		x = 0;
		y = 0;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	public Tile(int tileId, int x, int y, boolean up, boolean down, boolean left, boolean right) {
		this.tileId = tileId;
		this.x = x;
		this.y = y;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	public Tile(Tile tileOb) {
		tileId = tileOb.tileId;
		x = tileOb.x;
		y = tileOb.y;
		up = tileOb.up;
		down = tileOb.down;
		left = tileOb.left;
		right = tileOb.right;
	}
	
//Setters & Getters of the variables 
	
	public void setTileyId(int ti) {
		tileId = ti;
	}
	public int getTileId() {
		return tileId;
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
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean getUp() {
		return up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean getDown() {
		return down;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean getLeft() {
		return left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean getRight() {
		return right;
	}
}

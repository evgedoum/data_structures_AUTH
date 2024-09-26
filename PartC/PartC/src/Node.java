import java.util.ArrayList;

public class Node {
	Node parent;                 //the parent node of the node we create
	ArrayList<Node> children;    //the dynamic array which contains the children of the code we create
	int nodeDepth;               //the depth of the node in the tree MinMax
	int[] nodeMove;              //the motion represented by the node(the coordinates of the current position tile and the dice number) 
	Board nodeBoard;             //the board of the game for this node-motion
	double nodeEvaluation;       //the value of the evaluation function for the motion represented by the node

//Constructors of the class
  public Node(){
	   children=new  ArrayList<Node>(0);
	   nodeDepth=0;
	   nodeMove=new int [3];
	   nodeBoard= new Board();
	   nodeEvaluation=0;
   }
  public Node(Node parent, ArrayList<Node>children, int nodeDepth, int [] nodeMove, Board nodeBoard, double nodeEvaluation) {
	  this.parent= parent;
	  this.children=children;
	  this.nodeDepth=nodeDepth;
	  
	  this.nodeMove=nodeMove;
	  this.nodeBoard=nodeBoard;
	  this.nodeEvaluation=nodeEvaluation;
	  
	  
}
  
//Setters & Getters of the variables 
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	public int getNodeDepth() {
		return nodeDepth;
	}
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	public int[] getNodeMove() {
		return nodeMove;
	}
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	public Board getNodeBoard() {
		return nodeBoard;
	}
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
}
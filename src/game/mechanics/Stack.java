package game.mechanics;

public class Stack {
	private Item item;
	private int qty;
	public Stack(Item item, int qty){
		this.item = item;
		this.qty = qty;
	}
	public Stack(Stack s){
		this(s.getItem(),s.getQty());
	}
	public boolean merge(Stack stack){
		if(item == stack.item && item.isStackable()){
			if(!isValid()){
				item = stack.item;
				qty = stack.qty;
			}else{
				this.qty += stack.qty;	
			}
			stack.qty = 0;
			stack.item = null;
			return true;
		}
		return false;
	}
	public void setQty(int qty){
		this.qty = qty;
	}
	public boolean addItem(Item item){
		if(!isValid()){
			this.item = item;
			this.qty = 1;
			return true;
		}else if(this.item == item){
			this.qty++;
			return true;
		}
		return false;
		
	}
	public int getQty(){
		return qty;
	}
	public Item getItem(){
		return item;
	}
	public boolean isValid(){
		return item != null && qty > 0;
	}
	
}

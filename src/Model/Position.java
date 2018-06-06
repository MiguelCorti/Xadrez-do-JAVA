package Model;

public class Position {
	private int row;
	private int column;
	
	public Position() {
		
	}
	
	public Position(int r, int c){
		row = r;
		column = c;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public boolean isEqual(Position p)
	{
		if(p.getColumn() == column && p.getRow() == row)
			return true;
		return false;
	}
	
	public boolean setRow(int r)
	{
		if(r > 8 || r <0)
			return false;
		else
		{
			this.row = r;
			return true;
		}
	}
	
	public boolean setColumn(int c)
	{
		if(c > 8 || c <0)
			return false;
		else
		{
			this.column = c;
			return true;
		}
	}
	
	public boolean set(int r, int c) {
		if(c>8||c<0||r>8||r<0) {
			return false;
		}
		
		row = r;
		column = c;	
		
		return true;
	}
}

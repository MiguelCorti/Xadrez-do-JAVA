
public class Position {
	int row;
	int column;
	
	int getRow()
	{
		return row;
	}
	
	int getColumn()
	{
		return column;
	}
	
	boolean setRow(int r)
	{
		if(r > 8 || r <0)
			return false;
		else
		{
			this.row = r;
			return true;
		}
	}
	
	boolean setColumn(int c)
	{
		if(c > 8 || c <0)
			return false;
		else
		{
			this.column = c;
			return true;
		}
	}
}

package util;

public class CustomMover
{
	public static final int MOVES_NUMBER = 7;
	public static final int FORWARD = 0,
							BACKWARD = 1,
							LEFT = 2,
							RIGHT = 3,
							JUMPING = 4,
							SNEAKING = 5,
							SPRINTING = 6;
	private boolean moves[] = new boolean[MOVES_NUMBER];
	private boolean hasChange = false;

	public CustomMover()
	{
		this.moves[FORWARD] = false;
		this.moves[BACKWARD] = false;
		this.moves[LEFT] = false;
		this.moves[RIGHT] = false;
		this.moves[JUMPING] = false;
		this.moves[SNEAKING] = false;
	}
	public boolean getMove(int id)
	{
		return this.moves[id];
	}

	public void setMove(boolean state, int id)
	{
		if (this.moves[id] != state)
		{
			this.hasChange = true;
			this.moves[id] = state;
		}
	}
	public void setMoveFrom(CustomMover m, int id)
	{
		if (this.moves[id] != m.getMove(id))
		{
			this.setMove(m.getMove(id), id);
			this.hasChange = true;
		}
	}

	public boolean hasChanged()
	{
		boolean b = this.hasChange;
		this.hasChange = false;
		return b;
	}
	public CustomMover copy()
	{
		CustomMover m = new CustomMover();
		m.moves = this.moves.clone();
		m.hasChange = this.hasChange;
		return m;
	}
	public void setChanged(boolean changed)
	{
		this.hasChange = changed;
	}
	public String toString()
	{
		return "Forward : "+this.moves[FORWARD] +
			 "\nBackward : "+this.moves[BACKWARD] +
			 "\nLeft : "+this.moves[LEFT] +
			 "\nRight : "+this.moves[RIGHT] +
			 "\nJumping : "+this.moves[JUMPING] +
			 "\nSneaking : "+this.moves[SNEAKING] +
			 "\nSprinting : "+this.moves[SPRINTING];
	}
	public boolean isMoving()
	{
		return this.getMove(FORWARD) || this.getMove(BACKWARD) ||this.getMove(LEFT) ||this.getMove(RIGHT);
	}
}

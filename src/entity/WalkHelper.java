package entity;

public class WalkHelper
{
	public float state = 0f, add = 0f, max = 0;
	public boolean forward = true;
	public WalkHelper(float a, float m)
	{
		this.add = a;
		this.max = m;
	}
	public void walk()
	{
		this.state += this.forward ? this.add : -this.add;
		if (this.state > this.max || this.state < -this.max)
			this.forward = !this.forward;
	}
	public void repose()
	{
		if (this.state > 0)
			if (this.state < this.add)
				this.state = 0;
			else
				this.state -=this.add;
		if (this.state < 0)
			if (this.state > -this.add)
				this.state = 0;
			else
				this.state +=this.add;
	}
}

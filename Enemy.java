package Hackathon;

import apcs.Window;

public class RedBlockEnemy implements Thing {
	
	int x, y, speed, size, direction;
	String color;
	
	public RedBlockEnemy(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 9;
		size = 30;
		color = "red";
		direction = 1;
	}

	@Override
	public void draw() {
		Window.out.color(color);
		Window.out.square(x, y, size);
	}

	@Override
	public void move() {
		x += speed * direction;
		
		if (x > Window.width() - size / 2) {
			direction = -1;
			y += speed * 8;
		}
		else if (x < size / 2) {
			direction = 1;
			y += speed * 8;
		}
	}

	@Override
	public boolean isTouching(Thing t) {
		return false;
	}

	@Override
	public boolean isEnemy() {
		return true;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean shoot() {
		if (Window.rollDice(10000)==1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean outside() {
		if (y > Window.height() + size) {
			MainGame.gameover = true;
		}
		return false;
	}

	@Override
	public boolean isEnemySpaceship() {
		// TODO Auto-generated method stub
		return true;
	}

}

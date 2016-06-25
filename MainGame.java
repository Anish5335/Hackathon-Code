package Hackathon;

	import java.util.ArrayList;

	import apcs.Window;

	public class MainGame {
		
		static boolean gameover = false;

		public static void main(String[] args) {

			Window.size(700, 800);
			
			int lives = 2;


			Thing p = new Player();
			

			ArrayList<Thing> things = new ArrayList<Thing>();
			

			for (int y = 50; y <= 150; y += 50) {
				for (int i = 20; i < Window.width(); i += 40) {
					things.add(new RedBlockEnemy(i, y));
				}
			}

			int enemies = things.size();
			
			while (true) {
				Window.out.background("black");
				
				
				Window.out.color("lime green");
				Window.out.fontSize(30);
				Window.out.print("Lives: ", 20, Window.height() - 30);
				for (int i = 0; i < lives; i++) {
					Window.out.color("blue");
					Window.out.square(i * 20 + 110, Window.height() - 38, 15);
				}

				p.draw();
				p.move();

				if (p.shoot()) {
					things.add(new Bullet(p.getX(), p.getY(), false));
				}

				for (int i = 0; i < things.size(); i++) {
					things.get(i).draw();
					things.get(i).move();

					if (things.get(i).shoot()) {
						int x = things.get(i).getX();
						int y = things.get(i).getY();
						things.add(new Bullet(x, y, true));
					}

					if (things.get(i).outside()) {
						things.remove(i);
					}
				}

				for (int i = 0; i < things.size(); i++) {

					for (int j = 0; j < things.size(); j ++) {
						if (things.get(i).isTouching(things.get(j))) {
							if (things.get(j).isEnemySpaceship()) {
								enemies--;
								//System.out.println(enemies);
							}
							
							things.remove(i);
							if (i < j && j != 0) {
								j -= 1;
							}
							things.remove(j);
							i -= 2;
							break;
						}
					}
				}

				for (int i = 0; i < things.size(); i++) {

					if (things.get(i).isTouching(p)) {
						lives--;
						things.remove(i);
					}

				}
				
				if (lives < 0) {
					gameover = true;
				}
				
				if (gameover) {
					Window.out.color("white");
					Window.out.font("Blockletter", 70);
					Window.out.print("GAME OVER", 140, Window.height() / 2);
					Window.frame(2000);
					System.exit(0);
				}
				
				if (enemies <= 0) {
					Window.out.color("white");
					Window.out.font("Blockletter", 70);
					Window.out.print("YOU WIN", 140, Window.height() / 2);
					Window.frame(2000);
					System.exit(0);
				}

				Window.frame();
			}
		}

	}


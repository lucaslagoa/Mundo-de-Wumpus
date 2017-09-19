import java.util.ArrayList;
import java.util.Random;

public class Map {

	public void mapa(ArrayList<Room> listasala) {

		Room room1 = new Room();
		Room room2 = new Room();
		Room room3 = new Room();
		Room room4 = new Room();
		Room room5 = new Room();
		Room room6 = new Room();
		Room room7 = new Room();
		Room room8 = new Room();
		Room room9 = new Room();
		Room room10 = new Room();
		Room room11 = new Room();
		Room room12 = new Room();
		Room room13 = new Room();
		Room room14 = new Room();
		Room room15 = new Room();
		Room room16 = new Room();
		Wall wall = new Wall();
		Wall wall2 = new Wall();
		Wall wall3 = new Wall();
		Wall wall4 = new Wall();
		Wall wall5 = new Wall();
		Wall wall6 = new Wall();
		Wall wall7 = new Wall();
		Wall wall8 = new Wall();
		Wall wall9 = new Wall();
		Wall wall10 = new Wall();
		Wall wall11 = new Wall();
		Wall wall12 = new Wall();
		Wall wall13 = new Wall();
		Wall wall14 = new Wall();
		Wall wall15 = new Wall();
		Wall wall16 = new Wall();
		Wall wall17 = new Wall();
		Wall wall18 = new Wall();
		Wall wall19 = new Wall();
		Wall wall20 = new Wall();
		Wall wall21 = new Wall();
		Wall wall22 = new Wall();
		Wall wall23 = new Wall();
		Wall wall24 = new Wall();
		Wall wall25 = new Wall();
		Wall wall26 = new Wall();
		Wall wall27 = new Wall();
		Wall wall28 = new Wall();
		Wall wall29 = new Wall();
		Wall wall30 = new Wall();
		Wall wall31 = new Wall();
		Wall wall32 = new Wall();
		Wall wall33 = new Wall();
		Wall wall34 = new Wall();
		Wall wall35 = new Wall();
		Wall wall36 = new Wall();
		Wall wall37 = new Wall();
		Wall wall38 = new Wall();
		Wall wall39 = new Wall();
		Wall wall40 = new Wall();
		Wall wall41 = new Wall();
		Wall wall42 = new Wall();
		Wall wall43 = new Wall();
		Wall wall44 = new Wall();
		Wall wall45 = new Wall();
		Wall wall46 = new Wall();
		Wall wall47 = new Wall();
		Wall wall48 = new Wall();
		Wall wall49 = new Wall();
		Wall wall50 = new Wall();
		Wall wall51 = new Wall();
		Wall wall52 = new Wall();
		Wall wall53 = new Wall();
		Wall wall54 = new Wall();
		Wall wall55 = new Wall();
		Wall wall56 = new Wall();
		Wall wall57 = new Wall();
		Wall wall58 = new Wall();
		Wall wall59 = new Wall();
		Wall wall60 = new Wall();
		Wall wall61 = new Wall();
		Wall wall62 = new Wall();
		Wall wall63 = new Wall();
		Wall wall64 = new Wall();

		Random rand = new Random();
		Random randBonus = new Random();
		Random randBonus2 = new Random();
		Wumpus wumpus = new Wumpus();

		int randomWumpus = rand.nextInt((15 - 1) + 1);
		while (randomWumpus == 0 || randomWumpus == 1 || randomWumpus == 4 || randomWumpus == 6) {
			randomWumpus = randBonus.nextInt((15 - 1) + 1);
			Wumpus.setSalaWumpus(randomWumpus);
		}
		Wumpus.setSalaWumpus(randomWumpus);
		Gold gold = new Gold();
		Random rand1 = new Random();
		int randomOuro = rand1.nextInt((15 - 1) + 1);
		while (randomOuro == 2 || randomOuro == 10 || randomOuro == 15) {
			randomOuro = randBonus2.nextInt((15 - 1) + 1);
			gold.setSalaGold(randomOuro);
		}
		gold.setSalaGold(randomOuro);
		Pit pit3 = new Pit();
		pit3.setPossuiPit(1);

		Pit pit11 = new Pit();

		pit11.setPossuiPit(1);

		Pit pit16 = new Pit();
		pit16.setPossuiPit(1);

		int fedor = 0;
		int briza = 0;
		int radiante = 0;

		wall.setParede(1);
		wall2.setParede(0);
		wall3.setParede(0);
		wall4.setParede(1);
		room1.setIdRoom(0);
		room1.setBreeze(briza);
		room1.setStink(fedor);
		room1.setRadiance(radiante);
		room1.getWall().add(wall);
		room1.getWall().add(wall2);
		room1.getWall().add(wall3);
		room1.getWall().add(wall4);
		listasala.add(room1);

		wall5.setParede(0);
		wall6.setParede(0);
		wall7.setParede(0);
		wall8.setParede(1);
		room2.setIdRoom(1);
		room2.setStink(fedor);
		room2.setBreeze(briza);
		room2.setRadiance(radiante);
		room2.getWall().add(wall5);
		room2.getWall().add(wall6);
		room2.getWall().add(wall7);
		room2.getWall().add(wall8);
		listasala.add(room2);

		wall9.setParede(0);
		wall10.setParede(0);
		wall11.setParede(0);
		wall12.setParede(1);
		room3.setIdRoom(2);
		room3.setStink(fedor);
		room3.setBreeze(briza);
		room3.setRadiance(radiante);
		room3.getWall().add(wall9);
		room3.getWall().add(wall10);
		room3.getWall().add(wall11);
		room3.getWall().add(wall12);
		room3.getPit().add(pit3);
		listasala.add(room3);

		wall13.setParede(0);
		wall14.setParede(0);
		wall15.setParede(1);
		wall16.setParede(1);
		room4.setIdRoom(3);
		room4.setRadiance(radiante);
		room4.setStink(fedor);
		room4.setBreeze(briza);
		room4.getWall().add(wall13);
		room4.getWall().add(wall14);
		room4.getWall().add(wall15);
		room4.getWall().add(wall16);
		listasala.add(room4);

		wall17.setParede(1);
		wall18.setParede(0);
		wall19.setParede(0);
		wall20.setParede(0);
		room5.setIdRoom(4);
		room5.setStink(fedor);
		room5.setBreeze(briza);
		room5.setRadiance(radiante);
		room5.getWall().add(wall17);
		room5.getWall().add(wall18);
		room5.getWall().add(wall19);
		room5.getWall().add(wall20);
		listasala.add(room5);

		wall21.setParede(0);
		wall22.setParede(0);
		wall23.setParede(0);
		wall24.setParede(0);
		room6.setIdRoom(5);
		room6.setStink(fedor);
		room6.setBreeze(briza);
		room6.setRadiance(radiante);
		room6.getWall().add(wall21);
		room6.getWall().add(wall22);
		room6.getWall().add(wall23);
		room6.getWall().add(wall24);
		listasala.add(room6);

		wall25.setParede(0);
		wall26.setParede(0);
		wall27.setParede(0);
		wall28.setParede(0);
		room7.setIdRoom(6);
		room7.setStink(fedor);
		room7.setBreeze(briza);
		room7.setRadiance(radiante);
		room7.getWall().add(wall25);
		room7.getWall().add(wall26);
		room7.getWall().add(wall27);
		room7.getWall().add(wall28);
		listasala.add(room7);

		wall29.setParede(0);
		wall30.setParede(0);
		wall31.setParede(1);
		wall32.setParede(0);
		room8.setIdRoom(7);
		room8.setStink(fedor);
		room8.setBreeze(briza);
		room8.setRadiance(radiante);
		room8.getWall().add(wall29);
		room8.getWall().add(wall30);
		room8.getWall().add(wall31);
		room8.getWall().add(wall32);
		listasala.add(room8);

		wall33.setParede(1);
		wall34.setParede(0);
		wall35.setParede(0);
		wall36.setParede(0);
		room9.setIdRoom(8);
		room9.setStink(fedor);
		room9.setBreeze(briza);
		room9.setRadiance(radiante);
		room9.getWall().add(wall33);
		room9.getWall().add(wall34);
		room9.getWall().add(wall35);
		room9.getWall().add(wall36);
		listasala.add(room9);

		wall37.setParede(0);
		wall38.setParede(0);
		wall39.setParede(0);
		wall40.setParede(0);
		room10.setIdRoom(9);
		room10.setStink(fedor);
		room10.setBreeze(briza);
		room10.setRadiance(radiante);
		room10.getWall().add(wall37);
		room10.getWall().add(wall38);
		room10.getWall().add(wall39);
		room10.getWall().add(wall40);
		listasala.add(room10);

		wall41.setParede(0);
		wall42.setParede(0);
		wall43.setParede(0);
		wall44.setParede(0);
		room11.setIdRoom(10);
		room11.setStink(fedor);
		room11.setBreeze(briza);
		room11.setRadiance(radiante);
		room11.getWall().add(wall41);
		room11.getWall().add(wall42);
		room11.getWall().add(wall43);
		room11.getWall().add(wall44);
		room11.getPit().add(pit11);
		listasala.add(room11);

		wall45.setParede(0);
		wall46.setParede(0);
		wall47.setParede(1);
		wall48.setParede(0);
		room12.setIdRoom(11);
		room12.setStink(fedor);
		room12.setBreeze(briza);
		room12.setRadiance(radiante);
		room12.getWall().add(wall45);
		room12.getWall().add(wall46);
		room12.getWall().add(wall47);
		room12.getWall().add(wall48);
		listasala.add(room12);

		wall49.setParede(1);
		wall50.setParede(1);
		wall51.setParede(0);
		wall52.setParede(0);
		room13.setIdRoom(12);
		room13.setStink(fedor);
		room13.setBreeze(briza);
		room13.setRadiance(radiante);
		room13.getWall().add(wall49);
		room13.getWall().add(wall50);
		room13.getWall().add(wall51);
		room13.getWall().add(wall52);
		listasala.add(room13);

		wall53.setParede(0);
		wall54.setParede(1);
		wall55.setParede(0);
		wall56.setParede(0);
		room14.setIdRoom(13);
		room14.setStink(fedor);
		room14.setBreeze(briza);
		room14.setRadiance(radiante);
		room14.getWall().add(wall53);
		room14.getWall().add(wall54);
		room14.getWall().add(wall55);
		room14.getWall().add(wall56);
		listasala.add(room14);

		wall57.setParede(0);
		wall58.setParede(1);
		wall59.setParede(0);
		wall60.setParede(0);
		room15.setIdRoom(14);
		room15.setStink(fedor);
		room15.setBreeze(briza);
		room15.setRadiance(radiante);
		room15.getWall().add(wall57);
		room15.getWall().add(wall58);
		room15.getWall().add(wall59);
		room15.getWall().add(wall60);
		listasala.add(room15);

		wall61.setParede(0);
		wall62.setParede(1);
		wall63.setParede(1);
		wall64.setParede(0);
		room16.setIdRoom(15);
		room16.setStink(fedor);
		room16.setBreeze(briza);
		room16.setRadiance(radiante);
		room16.getWall().add(wall61);
		room16.getWall().add(wall62);
		room16.getWall().add(wall63);
		room16.getWall().add(wall64);
		room16.getPit().add(pit16);
		listasala.add(room16);
		radiante = gold.getSalaGold();
		listasala.get(radiante).setRadiance(1);
		int salaWumpus = wumpus.getSalaWumpus();
		if (listasala.get(salaWumpus).getWall().get(0).getParede() != 1) {
			listasala.get(salaWumpus - 1).setStink(1);
		}
		if (listasala.get(salaWumpus).getWall().get(1).getParede() != 1) {
			listasala.get(salaWumpus + 4).setStink(1);
		}
		if (listasala.get(salaWumpus).getWall().get(2).getParede() != 1) {
			listasala.get(salaWumpus + 1).setStink(1);
		}
		if (listasala.get(salaWumpus).getWall().get(3).getParede() != 1) {
			listasala.get(salaWumpus - 4).setStink(1);
		}

		listasala.get(1).setBreeze(1);
		listasala.get(3).setBreeze(1);
		listasala.get(6).setBreeze(1);
		listasala.get(9).setBreeze(1);
		listasala.get(11).setBreeze(1);
		listasala.get(14).setBreeze(1);
	}
}

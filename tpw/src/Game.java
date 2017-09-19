import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Wumpus wumpus = new Wumpus();
	Gold gold = new Gold();
	Wall wall = new Wall();
	Pit pit = new Pit();
	static Map mapa = new Map();
	

	public static ArrayList<Room> listasala = new ArrayList<Room>();	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		mapa.mapa(listasala);
		Agente agente = new Agente();
		while(agente.getIdRoom()!= -1){
			if(Wumpus.getSalaWumpus()==agente.getIdRoom() && Wumpus.isCry() != true){
				agente.setCost(agente.getCost()-1000);
				break;
			}if(2==agente.getIdRoom()||10==agente.getIdRoom()||15==agente.getIdRoom()){
				agente.setCost(agente.getCost()-1000);
				break;
			}
			agente.lerPercepcao(listasala.get(agente.getIdRoom()));
			if(agente.getTable().getPerception().get(agente.getIdRoom()).isRadiance()){
				break;
			};
			agente.selecionarSalas();
			agente.getTable().getPerception().get(agente.getIdRoom()).viewPerception();
			System.out.println("MIN: "+agente.getTable().getMin());
			System.out.println("SAFE: "+agente.getTable().getSafe());
			//agente.setIdRoom(agente.destinoMoviment2(agente.getCost()));
			agente.destinoMoviment();
			Thread.currentThread().sleep(3000);
			//agente.setCost(agente.getCost()+agente.heuristica(agente.getIdRoom(), idRoom, agente.getSentido()));
			//agente.setIdRoom(idRoom);	
			
		}
			
		System.out.println("DESEMPENHO = "+agente.getCost());
	}

}

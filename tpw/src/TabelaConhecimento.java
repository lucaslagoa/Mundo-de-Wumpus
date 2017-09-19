/*Lista de salas, com suas percepções, além de algumas listas auxiliares:
 * safe: lista com id's de salas que já foram percorridas
 * min: lista com id's de salas que não foram percorridas
 * med: lista com id's das salas onde tem os possiveis poços
 * wumpus: lista com id's das salas onde possivelmente está o wumpus
 * block: lista com id's das salas onde poços que com certeza são poços   */
import java.util.ArrayList;

public class TabelaConhecimento {
	private ArrayList<Percepcao> perception;
	private ArrayList<Integer> safe;
	private ArrayList<Integer> min;
	private ArrayList<Integer> max;
	private ArrayList<Integer> med;
	private ArrayList<Integer> wumpus;
	private ArrayList<Integer> block;
	public TabelaConhecimento(){
		setPerception(new ArrayList<Percepcao>());
		setMin(new ArrayList<Integer>());
		setMed(new ArrayList<Integer>());
		setMax(new ArrayList<Integer>());
		setWumpus(new ArrayList<Integer>());
		setSafe(new ArrayList<Integer>());
		setBlock(new ArrayList<Integer>());
		int i;
		for (i=0;i<16;i++){
			perception.add(new Percepcao());
		}
	}
	
	public void viewTable(){
		int i;
		for(i=0;i<16;i++){
			perception.get(i).viewPerception();
		}
	}
	public ArrayList<Percepcao> getPerception() {
		return perception;
	}
	public void setPerception(ArrayList<Percepcao> perception) {
		this.perception = perception;
	}
	public ArrayList<Integer> getMin() {
		return min;
	}
	public void setMin(ArrayList<Integer> min) {
		this.min = min;
	}
	public ArrayList<Integer> getMax() {
		return max;
	}
	public void setMax(ArrayList<Integer> max) {
		this.max = max;
	}
	public ArrayList<Integer> getMed() {
		return med;
	}
	public void setMed(ArrayList<Integer> med) {
		this.med = med;
	}
	public ArrayList<Integer> getWumpus() {
		return wumpus;
	}
	public void setWumpus(ArrayList<Integer> wumpus) {
		this.wumpus = wumpus;
	}
	public ArrayList<Integer> getSafe() {
		return safe;
	}
	public void setSafe(ArrayList<Integer> safe) {
		this.safe = safe;
	}
	public ArrayList<Integer> getBlock() {
		return block;
	}
	public void setBlock(ArrayList<Integer> block) {
		this.block = block;
	}
}

import java.util.ArrayList;

public class Room {
	
	public int idRoom;
	public int stink;
	public int breeze;
	public int radiance;
	
	public boolean isRadiance() {
		if(radiance == 0){
			return false;
		}
		else{
			return true;
		}
	}

	public void setRadiance(int radiance) {
		this.radiance = radiance;
	}

	public boolean isBreeze() {
		if(breeze == 0){
			return false;
		}
		else{
			return true;
		}
	}

	public void setBreeze(int breeze) {
		this.breeze = breeze;
	}

	public ArrayList<Wall> wall = new ArrayList<Wall>();
	public ArrayList<Pit> pit = new ArrayList<Pit>();

	public boolean isStink() {
		if(stink == 0){
			return false;
		}
		else{
			return true;
		}
	}

	public void setStink(int stink) {
		this.stink = stink;
	}

	public ArrayList<Wall> getWall() {
		return wall;
	}

	public void setWall(ArrayList<Wall> wall) {
		this.wall = wall;
	}

	public ArrayList<Pit> getPit() {
		return pit;
	}

	public void setPit(ArrayList<Pit> pit) {
		this.pit = pit;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	void viewSala(){
		System.out.println("Room id:"+idRoom);
		System.out.println("stink:"+stink);
		System.out.println("breeze:"+breeze);
		if(radiance==idRoom)
			System.out.println("radiance:"+radiance);
	}
	

}

import java.util.ArrayList;
import java.util.Random;


public class Agente {
	private TabelaConhecimento table;
	private int cost=0;
	private int idRoom=0;
	private int sentidoatual;
	//Sentidos: 0 - Baixo, 1 - Direita, 2 - Cima, 3 - Esquerda
	private int sentido = 1;
	public Agente(){
		table = new TabelaConhecimento();
	}//Lê as percepções da sala.
	void lerPercepcao(Room room){
		//Remove a sala de Max,Min,Wumpus ou Med e armazena em safe
		if (getTable().getPerception().get(idRoom).getState().equals("MAX"))removerItemMax(idRoom);
		if (getTable().getPerception().get(idRoom).getState().equals("MIN")) removerItemMin(idRoom);
		if (getTable().getPerception().get(idRoom).getState().equals("WUMPUS"))removerItemWumpus(idRoom);
		if (getTable().getPerception().get(idRoom).getState().equals("MED"))removerItemMed(idRoom);
		getTable().getSafe().add(idRoom);
		getTable().getPerception().get(idRoom).setBreeze(room.isBreeze());
		getTable().getPerception().get(idRoom).setStink(room.isStink());
		//Verifica se o Wumpus esta chorando, e se não foi setado
		if (Wumpus.isCry()==true&&getTable().getPerception().get(idRoom).isCry()!=true){
			setGrito();
		}
		if (room.isRadiance()==true){
			pegarOuro();
		}
		getTable().getPerception().get(idRoom).setState("SAFE");
	}
	//Seta grito em todas as salas
	public void setGrito(){
		int i;
		for(i=0;i<16;i++){
			getTable().getPerception().get(i).setCry(true);
		}
		for(i=0;i<getTable().getWumpus().size();i++){
			getTable().getMin().add(getTable().getWumpus().get(0));
			getTable().getWumpus().remove(0);
		}
		for(i=0;i<getTable().getMax().size();i++){
			getTable().getMed().add(getTable().getMax().get(0));
			getTable().getMax().remove(0);
		}
	}
	public void pegarOuro(){
		setCost(getCost()+1000);
		getTable().getPerception().get(idRoom).setRadiance(true);
		System.out.println("ENDGAME");
	}
	//Verifica quais salas estão adjacentes, e chama marcaSala 
	public void selecionarSalas(){
		boolean breeze = getTable().getPerception().get(idRoom).isBreeze();
		boolean stink = getTable().getPerception().get(idRoom).isStink();
		boolean cry = getTable().getPerception().get(idRoom).isCry();
		int id = idRoom;
		int aux;
		aux = id%4;
		aux = aux-1;
		id = id-1;
		if (id>0&&aux!=-1)marcarSala(id,breeze,stink,cry);
		aux = aux+2;
		id = id+2;
		if (id<16&&aux!=4)marcarSala(id,breeze,stink,cry);
		id = id-5;
		if (id>0)marcarSala(id,breeze,stink,cry);
		id = id+8;
		if (id<16)marcarSala(id,breeze,stink,cry);
	}
	//Marca as salas com os estados e as insere nas listas respectivas
	public void marcarSala(int id,boolean breeze,boolean stink,boolean cry){
		//Inserção da lista MAX
		if(breeze==true&&stink==true&&cry==false){
			if (!getTable().getPerception().get(id).getState().equals("BLOCK")){
				if (!getTable().getPerception().get(id).getState().equals("SAFE")){
					if (!getTable().getPerception().get(id).getState().equals("MIN")){
						if (!getTable().getPerception().get(id).getState().equals("MED")){
							getTable().getPerception().get(id).setState("MAX");
							removerItemMax(id);
							getTable().getMax().add(id);
						}
					}
				}
			}
		//Inserção da lista WUMPUS
		}else if(breeze==false&&stink==true&&cry==false){
			if (!getTable().getPerception().get(id).getState().equals("BLOCK")){
				if (!getTable().getPerception().get(id).getState().equals("SAFE")){
					if (!getTable().getPerception().get(id).getState().equals("MIN")){
						if (getTable().getPerception().get(id).getState().equals("MED")){
							//Se na sala a percepção for já estiver MED, Seta min, pois duas salas leram percepção sobre essa diferentemente 
							getTable().getPerception().get(id).setState("MIN");
							getTable().getMin().add(id);
							removerItemMed(id);	
						}else if (getTable().getPerception().get(id).getState().equals("MAX")){
							//Se na sala a percepção for já estiver MAX, Seta Wumpus, pois duas salas leram percepção sobre essa diferentemente,
							//Mas tem em comum o fedor
							getTable().getPerception().get(id).setState("WUMPUS");
							getTable().getWumpus().add(id);
							removerItemMax(id);	
						}else{
							getTable().getPerception().get(id).setState("WUMPUS");
							removerItemWumpus(id);
							getTable().getWumpus().add(id);
						}
					}
				}
			}
			//Inserção da lista MED
		}else if(breeze==true){
			if (!getTable().getPerception().get(id).getState().equals("BLOCK")){
				if (!getTable().getPerception().get(id).getState().equals("SAFE")){
					if (!getTable().getPerception().get(id).getState().equals("MIN")){
						if (getTable().getPerception().get(id).getState().equals("MAX")){
							//Se na sala a percepção for já estiver MAX, Seta MED, pois duas salas leram percepção sobre essa diferentemente
							//Mas possuem brisa em comum
							removerItemBlock(id);
							getTable().getPerception().get(id).setState("MED");
							getTable().getMed().add(id);
							removerItemMax(id);	
						}else if (getTable().getPerception().get(id).getState().equals("WUMPUS")){
							//Se na sala a percepção for já estiver WUMPUS, Seta min, pois duas salas leram percepção sobre essa diferentemente
							removerItemBlock(id);
							getTable().getPerception().get(id).setState("MIN");
							getTable().getMin().add(id);
							removerItemWumpus(id);	
						}else if(getTable().getPerception().get(id).getState().equals("MED")){
							//Se na sala a percepção for já estiver MED, Seta BLOCK, pois é confirmado que é poço
							removerItemBlock(id);
							getTable().getBlock().add(id);
						}else{
							getTable().getPerception().get(id).setState("MED");
							removerItemMed(id);
							getTable().getMed().add(id);
						}
					}
				}
			}
			//Adiciona na lista MIN
		}else if(breeze==false){
			if (!getTable().getPerception().get(id).getState().equals("BLOCK")){
				if (!getTable().getPerception().get(id).getState().equals("SAFE")){
					
					if (getTable().getPerception().get(id).getState().equals("MAX")){
						removerItemMax(id);
					}
					if (getTable().getPerception().get(id).getState().equals("MED")){
						removerItemMed(id);
					}
					if (getTable().getPerception().get(id).getState().equals("WUMPUS")){
						removerItemWumpus(id);
					}
								
					
					getTable().getPerception().get(id).setState("MIN");
					removerItemMin(id);
					getTable().getMin().add(id);
				}
			}
		}
	}
	//Seleciona o destino para para movimentação em destinoMoviment2()
	public int destinoMoviment(){
		int i;
		int tam;
		if (getTable().getMin().size()>0){
			//Tratamento utilizando lista de MIN
			setIdRoom(destinoMoviment2(getCost(),getTable().getMin().get(0)));
			return this.idRoom;
		}else if(getTable().getWumpus().size()>0){
			//Tratamento utilizando lista de Wumpus
			tam = getTable().getWumpus().size();
			//Lista de adjacentes ao Wumpus
			ArrayList<Integer> adjWumpus = adjacentes(getTable().getWumpus().get(tam-1));
			for(i =1;i<adjWumpus.size();i++){
				//Procura adjacente em SAFE
				if(HaveItemSafe(adjWumpus.get(i))){
					setIdRoom(destinoMoviment2(getCost(),adjWumpus.get(i)));
					//Atira flecha
					throwArrow(adjWumpus.get(0),idRoom);
					if(Wumpus.isCry()){
						break;
					}
				}
			}
			if(!Wumpus.isCry()){
				removerItemWumpus(adjWumpus.get(0));
				getTable().getPerception().get(adjWumpus.get(0)).setState("MIN");
				getTable().getMin().add(adjWumpus.get(0));
			}
			return this.idRoom;
		}else if(getTable().getMax().size()>0){
			//Tratamento para MAX. Semelhante ao para WUMPUS
			tam = getTable().getMax().size();
			ArrayList<Integer> adjWumpus = adjacentes(getTable().getMax().get(tam-1));
			for(i =1;i<adjWumpus.size();i++){
				if(HaveItemSafe(adjWumpus.get(i))){	
					setIdRoom(destinoMoviment2(getCost(),adjWumpus.get(i)));
					throwArrow(adjWumpus.get(0),idRoom);
					if(Wumpus.isCry()){
						break;
					}
				}
			}if(!Wumpus.isCry()){
				getTable().getMax().remove(0);
				getTable().getPerception().get(adjWumpus.get(0)).setState("MED");
				getTable().getMed().add(adjWumpus.get(0));
			}
			return this.idRoom;
		}else{
			//Loop identificado
			System.out.println("NÃO FOI POSSIVEL MOVIMENTAR");
			idRoom = -1;
			return idRoom;
		}
		
	}
	//Função para atirar flecha
	public void throwArrow(int direcao,int local){
		setCost(getCost()-10);
		//Se o WUMPUS está na sala onde foi a flecha, seta cry
		System.out.println(Wumpus.getSalaWumpus()==direcao);
		if(Wumpus.getSalaWumpus()==direcao){
			Wumpus.setCry();
			setCost(getCost()-10);
		}else{
			//Seta Impact caso contrario
			getTable().getPerception().get(local).setImpact(true);
			removerItemWumpus(direcao);
			setCost(getCost()-10);
		}
	}
	
	
	
	public TabelaConhecimento getTable() {
		return table;
	}
	public void setTable(TabelaConhecimento table) {
		this.table = table;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getSentido() {
		return sentido;
	}
	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	public void removerItemMax(int item){
		for(int i =0;i<getTable().getMax().size();i++){
			if(item == getTable().getMax().get(i)){
				getTable().getMax().remove(i);
			}
		}
	}
	public void removerItemMed(int item){
		for(int i =0;i<getTable().getMed().size();i++){
			if(item == getTable().getMed().get(i)){
				getTable().getMed().remove(i);
			}
		}
	}public void removerItemWumpus(int item){
		for(int i =0;i<getTable().getWumpus().size();i++){
			if(item == getTable().getWumpus().get(i)){
				getTable().getWumpus().remove(i);
			}
		}
	}public void removerItemMin(int item){
		for(int i =0;i<getTable().getMin().size();i++){
			if(item == getTable().getMin().get(i)){
				getTable().getMin().remove(i);
			}
		}
	}
	public void removerItemSafe(int item){
		for(int i =0;i<getTable().getSafe().size();i++){
			if(item == getTable().getSafe().get(i)){
				getTable().getSafe().remove(i);
			}
		}
	}
	public void removerItemBlock(int item){
		for(int i =0;i<getTable().getBlock().size();i++){
			if(item == getTable().getBlock().get(i)){
				getTable().getBlock().remove(i);
			}
		}
	}
	public boolean HaveItemSafe(int item){
		for(int i =0;i<getTable().getSafe().size();i++){
			if(item == getTable().getSafe().get(i)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Integer> adjacentes(int id){
		ArrayList<Integer> adj = new ArrayList<Integer>();
		adj.add(id);
		int aux;
		aux = id%4;
		aux = aux-1;
		id = id-1;
		if (id>0&&aux!=-1) adj.add(id);
		aux = aux+2;
		id = id+2;
		if (id<16&&aux!=4)adj.add(id);
		id = id-5;
		if (id>0)adj.add(id);
		id = id+8;
		if (id<16)adj.add(id);
		return adj;
	}
	
	
	public void girar(int sentido,ArrayList<Integer> adj){
		if (sentido == 0){//baixo
			//baixo
			adj.set(0,1);
			//direita
			adj.set(1, 2);
			//cima
			adj.set(2,3);
			//esquerda
			adj.set(3, 2);
		}else if(sentido == 1){//direita
			//baixo
			adj.set(0,2);
			//direita
			adj.set(1, 3);
			//cima
			adj.set(2,2);
			//esquerda
			adj.set(3,1);
		} else if(sentido == 2){//cima
			//baixo
			adj.set(0,3);
			//direita
			adj.set(1,2);
			//cima
			adj.set(2,1);
			//esquerda
			adj.set(3,2);
		} else{//esquerda
			//baixo
			adj.set(0,2);
			//esquerda
			adj.set(1,1);
			//cima
			adj.set(2,2);
			//direita
			adj.set(3,3);
		}
	}
	public int destinoMoviment2(int cost, int destino){
		int sent;
		int gira = 0;
		if(getTable().getMin().size()>0){
			if(idRoom == 0){
				if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 4 || idRoom == 8){
				if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					System.out.println("" + idRoom);
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return this.idRoom;
				}else if(getTable().getPerception().get(idRoom+4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom -1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 12){
				if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).getState() == "MIN"){
					getTable().getPerception().get(idRoom-4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-4){
							getTable().getMin().remove(i);
						}
					}
					sent = 0;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if (idRoom == 13 || idRoom == 14){
				if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).getState() == "MIN"){
					getTable().getPerception().get(idRoom-4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-4){
							getTable().getMin().remove(i);
						}
					}
					sent = 0;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					System.out.println("xx" + idRoom);
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 15){
				if(getTable().getPerception().get(idRoom-4).getState() == "MIN"){
					getTable().getPerception().get(idRoom-4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-4){
							getTable().getMin().remove(i);
						}
					}
					sent = 0;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 11 || idRoom == 7){
				 if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
						getTable().getPerception().get(idRoom-1).setState("SAFE");
						for(int i = 0;  i< getTable().getMin().size(); i++){
							if (getTable().getMin().get(i) == idRoom-1){
								getTable().getMin().remove(i);
							}
						}
						sent = 3;
						if(sent > sentidoatual){
							gira = sent - sentidoatual;
						}else{
							gira = sentidoatual - sent;
						}
						idRoom = idRoom -1;
						if (gira == 2){
							setCost(getCost() -2);
						}else{
							setCost(getCost() - 1 -gira);
						}
						return this.idRoom;
				}else if(getTable().getPerception().get(idRoom-4).getState() == "MIN"){
					getTable().getPerception().get(idRoom-4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-41){
							getTable().getMin().remove(i);
						}
					}
					sent = 0;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom +4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 3){
				if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return this.idRoom;
				}else if(getTable().getPerception().get(idRoom +4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if (idRoom == 1 || idRoom == 2){
				if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom +4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
				
			}else{
				if(getTable().getPerception().get(idRoom+1).getState() == "MIN"){
					getTable().getPerception().get(idRoom+1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+1){
							getTable().getMin().remove(i);
						}
					}
					sent = 1;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).getState() == "MIN"){
					getTable().getPerception().get(idRoom-1).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-1){
							getTable().getMin().remove(i);
						}
					}
					sent = 3;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -1;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom +4).getState() == "MIN"){
					getTable().getPerception().get(idRoom+4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom+4){
							getTable().getMin().remove(i);
						}
					}
					sent = 2;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom +4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).getState() == "MIN"){
					getTable().getPerception().get(idRoom-4).setState("SAFE");
					for(int i = 0;  i< getTable().getMin().size(); i++){
						if (getTable().getMin().get(i) == idRoom-4){
							getTable().getMin().remove(i);
						}
					}
					sent = 0;
					if(sent > sentidoatual){
						gira = sent - sentidoatual;
					}else{
						gira = sentidoatual - sent;
					}
					idRoom = idRoom -4;
					if (gira == 2){
						setCost(getCost() -2);
					}else{
						setCost(getCost() - 1 -gira);
					}
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}	
		}else{ ///////////////COMEÇA WUMPUSSSSSSSSSSSSSS/////////////
			if(idRoom == 0){
				if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 4 || idRoom == 8){
				if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return this.idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 12){
				if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-4);
					return idRoom;
				}
			}else if (idRoom == 13 || idRoom == 14){
				if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-4);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 15){
				if(getTable().getPerception().get(idRoom-4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-4);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 11 || idRoom == 7){
				if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return this.idRoom;
				}else if(getTable().getPerception().get(idRoom-4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-4);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if(idRoom == 3){
				if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return this.idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}else if (idRoom == 1 || idRoom == 2){
				if(getTable().getPerception().get(idRoom-1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
				
			}else{
				if(getTable().getPerception().get(idRoom+1).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-1).isStink() == true){
						throwArrow(getTable().getWumpus().get(0),idRoom-1);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom+4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom+4);
					return idRoom;
				}else if(getTable().getPerception().get(idRoom-4).isStink() == true){
					throwArrow(getTable().getWumpus().get(0),idRoom-4);
					return idRoom;
				}else{
					idRoom = semmovemove(idRoom,sentidoatual, gira);
					setCost(getCost() -1);
					return idRoom;
				}
			}	
		}
		return idRoom;
	}
	
	public int semmovemove(int idRoom, int sentidoatual, int gira){
		int i = 1;
		int sent;
		i = i + 1;
		int tamanho = getTable().getMin().size();
		int tamanho2 = getTable().getSafe().size();
		System.out.println(tamanho);
		if(getTable().getSafe().get(tamanho2-i) == idRoom + 1){
			sent = 1;
			if(sent > sentidoatual){
				gira = sent - sentidoatual;
			}else{
				gira = sentidoatual - sent;
			}
			if (gira == 2){
				setCost(getCost() -2);
			}else{
				setCost(getCost() - 1 -gira);
			}
		}else if(getTable().getSafe().get(tamanho2 - i) == idRoom + 4){
			sent = 2;
			if(sent > sentidoatual){
				gira = sent - sentidoatual;
			}else{
				gira = sentidoatual - sent;
			}
			idRoom = idRoom +4;
			if (gira == 2){
				setCost(getCost() -2);
			}else{
				setCost(getCost() - 1 -gira);
			}
		}else if(getTable().getSafe().get(tamanho2 - i) == idRoom - 1){
			sent = 3;
			if(sent > sentidoatual){
				gira = sent - sentidoatual;
			}else{
				gira = sentidoatual - sent;
			}
			idRoom = idRoom -1;
			if (gira == 2){
				setCost(getCost() -2);
			}else{
				setCost(getCost() - 1 -gira);
			}
		}else{
			sent = 0;
			if(sent > sentidoatual){
				gira = sent - sentidoatual;
			}else{
				gira = sentidoatual - sent;
			}
			idRoom = idRoom -4;
			if (gira == 2){
				setCost(getCost() -2);
			}else{
				setCost(getCost() - 1 -gira);
			}
		}
		idRoom = getTable().getSafe().get(tamanho2-i);
		return idRoom;
	}
	public void removePocoSemBlock(){
		int k,j,i;
		int tamanhoBlock = getTable().getBlock().size();
		int tamanhoMed = getTable().getMed().size();
		for(i =0;i<tamanhoMed;i++){
			k=0;
			for(j=0;j<tamanhoBlock;j++){
				if(getTable().getBlock().get(j)==getTable().getMed().get(i)){
					k++;
				}
			}
			if(k==0){
				getTable().getMin().add(getTable().getMed().get(i));
				getTable().getMed().remove(i);
				return;
			}
		}
		
	}
}


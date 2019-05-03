package eu.su.mas.dedaleEtu.mas.agents;

import java.util.ArrayList;
import java.util.List;

import dataStructures.tuple.Couple;
import eu.su.mas.dedale.env.Observation;
import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import eu.su.mas.dedale.mas.agent.behaviours.startMyBehaviours;
import eu.su.mas.dedaleEtu.mas.behaviours.EndProcess;
import eu.su.mas.dedaleEtu.mas.behaviours.ExploDuoBehaviour;
import eu.su.mas.dedaleEtu.mas.behaviours.FindTanker;
import eu.su.mas.dedaleEtu.mas.behaviours.GoToGoalCollect;
import eu.su.mas.dedaleEtu.mas.behaviours.ReceiveBlock;
import eu.su.mas.dedaleEtu.mas.behaviours.ReceiveDone;
import eu.su.mas.dedaleEtu.mas.behaviours.SendMap;
import eu.su.mas.dedaleEtu.mas.behaviours.SendReady;
import eu.su.mas.dedaleEtu.mas.behaviours.ReceiveMap;
import eu.su.mas.dedaleEtu.mas.behaviours.SayImBlock_1;
import eu.su.mas.dedaleEtu.mas.behaviours.SayImBlock_2;
import eu.su.mas.dedaleEtu.mas.knowledge.MapRepresentation;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;



public class exploreAgent1 extends AbstractDedaleAgent implements explot_collect_agent {

	private static final long serialVersionUID = -6431752665590433727L;
	private MapRepresentation myMap;
	private List<Couple<String,List<Couple<Observation,Integer>>>> ListeTresor;
	private String TankerPosition;
	private String objPosition;
	
	private  static  final  String exploration = "exploration";
	private  static  final  String sendMap = "send_map";
	private  static  final  String receiveMap = "receive_map";
	private static final String sendBlock1 = "send_block1";
	private static final String sendBlock2 = "send_block2";
	private static final String receiveBlock = "receive_block";
	private static final String findTanker = "find_tanker";
	private static final String gotocible = "Go_To_cible";
	private static final String sendready = "send_Ready";
	private static final String receiveddone = "received_done";
	

	private  static  final  String fin = "fin";

	
	protected void setup(){

		super.setup();
		
		this.ListeTresor = new ArrayList<Couple<String,List<Couple<Observation,Integer>>>>();
		this.myMap=new MapRepresentation();
		this.TankerPosition = new String("") ;
		List<Behaviour> lb=new ArrayList<Behaviour>();
		
		FSMBehaviour fsm = new FSMBehaviour(this);	
		
		fsm.registerFirstState (new ExploDuoBehaviour(this), exploration);
		fsm.registerState (new SendMap(this), sendMap);
		fsm.registerState (new ReceiveMap(this), receiveMap);
		fsm.registerState(new SayImBlock_1(this),sendBlock1 );
		fsm.registerState(new SayImBlock_2(this),sendBlock2 );
		fsm.registerState(new ReceiveBlock(this), receiveBlock);
		fsm.registerState(new FindTanker(this), findTanker);
		fsm.registerState(new GoToGoalCollect(this, myMap, objPosition),gotocible);
		fsm.registerState(new SendReady(this), sendready);
		fsm.registerState(new ReceiveDone(this), receiveddone);
		fsm.registerLastState(new EndProcess(), fin);
		
		fsm.registerDefaultTransition (exploration,sendMap);
		fsm.registerDefaultTransition (sendMap,receiveMap);
		fsm.registerDefaultTransition (receiveMap,exploration);
		fsm.registerTransition(exploration,sendBlock1,2);
		fsm.registerDefaultTransition(sendBlock1, receiveBlock);
		fsm.registerDefaultTransition(receiveBlock,exploration);
		fsm.registerTransition (exploration,findTanker, 3);
		fsm.registerTransition(findTanker,sendBlock2,2);
		fsm.registerDefaultTransition(sendBlock2, receiveBlock);
		fsm.registerTransition(receiveBlock,findTanker, 2);
		fsm.registerDefaultTransition(findTanker, findTanker);
		fsm.registerTransition(findTanker, fin, 3);

		fsm.registerDefaultTransition(gotocible,gotocible);
		fsm.registerTransition(gotocible, sendready, 3);
		fsm.registerTransition(sendready,receiveddone, 3);
		fsm.registerDefaultTransition(receiveddone, receiveddone);
		fsm.registerTransition(receiveddone, findTanker,3);
		
		lb.add(fsm);
		addBehaviour(new startMyBehaviours(this,lb));
		
		try {
			this.doWait(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("the  agent "+this.getLocalName()+ " is started");
	}
	

	public void beforeMove()
	{
		
	super.beforeMove();
	System.out.println("Save everything (and kill GUI) before move");
	this.myMap.prepareMigration();
	
	}

	public void afterMove()
	{
		
	super.afterMove();
	this.myMap.loadSavedData();
	System.out.println("Restore data (and GUI) after moving");
	
	}


	@Override
	public void setMap(MapRepresentation myMap) {
			this.myMap = myMap;
	}


	@Override
	public MapRepresentation getMap() {
		return this.myMap ;
	}


	@Override
	public void setListTresor(List<Couple<String, List<Couple<Observation, Integer>>>> ListeTresor) {
		this.ListeTresor = ListeTresor;
	}


	@Override
	public List<Couple<String, List<Couple<Observation, Integer>>>> getListTresor() {
		return this.ListeTresor;
	}


	@Override
	public void setTankerPosition(String posTanker) {
			this.TankerPosition = posTanker;
	}


	@Override
	public String getTankerPosition() {
		return this.TankerPosition;
	}


	@Override
	public void setCpt(int cpt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getCpt() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setListAgentCap(List<Couple<String, List<Integer>>> listAgent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Couple<String, List<Integer>>> getListAgentCap() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setListNodes(List<String> listNodes) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getListNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
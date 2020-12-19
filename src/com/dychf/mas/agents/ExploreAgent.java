package com.dychf.mas.agents;

import com.dychf.mas.behaviours.MapExploration;
import com.dychf.mas.knowledge.MapRepresentation;
import dataStructures.tuple.Couple;
import eu.su.mas.dedale.env.Observation;
import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import eu.su.mas.dedale.mas.agent.behaviours.startMyBehaviours;
import jade.core.behaviours.Behaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ilyas Aroui
 */

public class ExploreAgent extends AbstractDedaleAgent implements AgentInterface {

    private static final long serialVersionUID = -6431752665590433727L;
    private MapRepresentation myMap;
    private static final String exploration = "exploration";

    protected void setup() {
        super.setup();
        List<Behaviour> lb = new ArrayList<>();
        lb.add(new MapExploration(this));
        addBehaviour(new startMyBehaviours(this, lb));
    }


    public void beforeMove() {
        super.beforeMove();
        System.out.println("Save everything (and kill GUI) before move");
    }

    public void afterMove() {
        super.afterMove();
        System.out.println("Restore data (and GUI) after moving");
    }


    @Override
    public void setMap(MapRepresentation myMap) {
        this.myMap = myMap;
    }

    @Override
    public MapRepresentation getMap() {
        return this.myMap;
    }

    @Override
    public void setListTresor(List<Couple<String, List<Couple<Observation, Integer>>>> ListeTresor) {

    }

    @Override
    public List<Couple<String, List<Couple<Observation, Integer>>>> getListTresor() {
        return null;
    }

    @Override
    public void setTankerPosition(String posTanker) {

    }

    @Override
    public String getTankerPosition() {
        return null;
    }

    @Override
    public void setMissionPosition(String missionPos) {

    }

    @Override
    public String getMissionPosition() {
        return null;
    }

    @Override
    public void setCpt(int cpt) {

    }

    @Override
    public int getCpt() {
        return 0;
    }

    @Override
    public void setListAgentCap(List<Couple<String, List<Couple<String, Integer>>>> listAgent) {

    }

    @Override
    public List<Couple<String, List<Couple<String, Integer>>>> getListAgentCap() {
        return null;
    }

    @Override
    public void setListNodes(List<String> listNodes) {

    }

    @Override
    public List<String> getListNodes() {
        return null;
    }

    @Override
    public void setCapacities(List<Couple<String, Integer>> capacities) {

    }

    @Override
    public List<Couple<String, Integer>> getCapacities() {
        return null;
    }

    @Override
    public void setTresorNodeInfo(Couple<String, List<Couple<Observation, Integer>>> nodeInfo) {

    }

    @Override
    public Couple<String, List<Couple<Observation, Integer>>> getTresorNodeInfo() {
        return null;
    }
}

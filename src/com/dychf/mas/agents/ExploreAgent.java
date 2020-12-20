package com.dychf.mas.agents;

import com.dychf.mas.behaviours.MapExploration;
import com.dychf.mas.behaviours.ReceiveMap;
import com.dychf.mas.behaviours.SendMap;
import com.dychf.mas.knowledge.MapRepresentation;
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

    protected void setup() {
        super.setup();

        this.myMap = new MapRepresentation();
        List<Behaviour> lb = new ArrayList<>();
        lb.add(new MapExploration(this));
        lb.add(new SendMap(this));
        lb.add(new ReceiveMap(this));
        addBehaviour(new startMyBehaviours(this, lb));
        System.out.println("the  agent " + this.getLocalName() + " is started");
    }

    @Override
    public void setMap(MapRepresentation myMap) {
        this.myMap = myMap;
    }

    @Override
    public MapRepresentation getMap() {
        return myMap;
    }
}

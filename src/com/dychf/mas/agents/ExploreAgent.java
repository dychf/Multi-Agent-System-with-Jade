package com.dychf.mas.agents;

import com.dychf.mas.behaviours.MapExploration;
import dataStructures.tuple.Couple;
import eu.su.mas.dedale.env.Observation;
import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import eu.su.mas.dedale.mas.agent.behaviours.startMyBehaviours;
import eu.su.mas.dedaleEtu.mas.agents.AgentInterface;
import eu.su.mas.dedaleEtu.mas.behaviours.*;
import eu.su.mas.dedaleEtu.mas.knowledge.MapRepresentation;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ilyas Aroui
 */

public class ExploreAgent extends AbstractDedaleAgent {

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


}

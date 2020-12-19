package com.dychf.mas.behaviours;

import dataStructures.tuple.Couple;
import eu.su.mas.dedale.env.Observation;
import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import eu.su.mas.dedaleEtu.mas.agents.AgentInterface;
import eu.su.mas.dedaleEtu.mas.knowledge.MapRepresentation;
import eu.su.mas.dedaleEtu.mas.knowledge.MapRepresentation.MapAttribute;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;

import java.util.*;


/**
 * @author ilyas Aroui
 */
public class MapExploration extends TickerBehaviour {

    private static final long serialVersionUID = 8567689731496787661L;

    public MapExploration(final AbstractDedaleAgent myagent) {
        super(myagent, 1000);
    }

    @Override
    protected void onTick() {
        String myPosition = ((AbstractDedaleAgent) this.myAgent).getCurrentPosition();
        System.out.println(this.myAgent.getLocalName() + " -- myCurrentPosition is: " + myPosition);
        if (myPosition != null) {
            //寻找可达节点
            List<Couple<String, List<Couple<Observation, Integer>>>> lobs = ((AbstractDedaleAgent) this.myAgent).observe();
            System.out.println(this.myAgent.getLocalName() + "--- list of observables:" + lobs);

            //getRight 记录宝藏信息
            List<Couple<Observation, Integer>> lObservations = lobs.get(0).getRight();
            System.out.println("发现宝藏:" + lObservations);

            if (lobs.size() > 0) {
                Random random = new Random();
                int moveId = 1 + random.nextInt(lobs.size() - 1);

                System.out.println("moveID---" + moveId);
                //getLeft用于获取节点值
                ((AbstractDedaleAgent) this.myAgent).moveTo(lobs.get(moveId).getLeft());
            }
        }
    }
}

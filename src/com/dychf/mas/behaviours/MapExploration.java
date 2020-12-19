package com.dychf.mas.behaviours;

import com.dychf.mas.agents.AgentInterface;
import com.dychf.mas.knowledge.MapRepresentation;
import dataStructures.tuple.Couple;
import eu.su.mas.dedale.env.Observation;
import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;

import java.util.*;


/**
 * @author ilyas Aroui
 */
public class MapExploration extends SimpleBehaviour {

    private static final long serialVersionUID = 8567689731496787661L;

    private MapRepresentation myMap;
    private List<String> openNodes;
    private Set<String> closedNodes;
    private boolean finished = false;
    private int exitValue;

    public MapExploration(final AbstractDedaleAgent myagent) {
        super(myagent);
        this.openNodes = new ArrayList<String>();
        this.closedNodes = new HashSet<String>();
    }

    @Override
    public void action() {
        this.myMap = ((AgentInterface) this.myAgent).getMap();
        if (this.myMap == null)
            this.myMap = new MapRepresentation();

        try {
            this.myAgent.doWait(1000);
            String myPosition = ((AbstractDedaleAgent) this.myAgent).getCurrentPosition();
            if (myPosition != null) {

                //得到后续节点列表
                List<Couple<String, List<Couple<Observation, Integer>>>> lobs = ((AbstractDedaleAgent) this.myAgent).observe();

                this.closedNodes.add(myPosition);
                this.openNodes.remove(myPosition);
                this.myMap.addNode(myPosition);

                String nextNode = null;
                Iterator<Couple<String, List<Couple<Observation, Integer>>>> iter = lobs.iterator();

                //对可达节点进行遍历
                while (iter.hasNext()) {
                    Couple<String, List<Couple<Observation, Integer>>> node = iter.next();
                    String nodeId = node.getLeft();

                    if (!this.closedNodes.contains(nodeId)) {

                        if (!this.openNodes.contains(nodeId)) {
                            this.openNodes.add(nodeId);
                            this.myMap.addNode(nodeId, MapRepresentation.MapAttribute.open);
                            this.myMap.addEdge(myPosition, nodeId);
                        } else {
                            this.myMap.addEdge(myPosition, nodeId);
                        }
                        if (nextNode == null) nextNode = nodeId;
                    }
                }

                if (this.openNodes.isEmpty()) {
                    //遍历完成
                    System.out.println(((AbstractDedaleAgent) this.myAgent).getLocalName() + " : Exploration successufully done.");
                    finished = true;
                    exitValue = 3;
                } else {
                    if (nextNode == null) {
                        int best_size = 9999;//距离节点的路径长度
                        int best_i = 0;//可达节点的下标位置, 用于记录最近的节点
                        //从可达节点中计算出最近的节点
                        for (int i = 0; i < this.openNodes.size(); i++) {
                            int current_size = (this.myMap.getShortestPath(myPosition, this.openNodes.get(i))).size();
                            if (current_size < best_size) {
                                best_i = i;
                                best_size = current_size;
                            }
                        }
                        //到最近的节点路径上, 要走的第一个节点
                        nextNode = this.myMap.getShortestPath(myPosition, this.openNodes.get(best_i)).get(0);
                    }

                    //向计算出的节点移动
                    if (!((AbstractDedaleAgent) this.myAgent).moveTo(nextNode)) {
                        exitValue = 2;
                        finished = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int onEnd() {
        ((AgentInterface) this.myAgent).setMap(this.myMap);
        return exitValue;
    }

    @Override
    public boolean done() {
        return finished;
    }
}

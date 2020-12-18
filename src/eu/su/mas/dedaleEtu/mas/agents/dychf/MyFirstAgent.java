package eu.su.mas.dedaleEtu.mas.agents.dychf;

import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import eu.su.mas.dedale.mas.agent.behaviours.startMyBehaviours;
import eu.su.mas.dedaleEtu.mas.behaviours.dychf.RandomWalkBehaviour;
import eu.su.mas.dedaleEtu.mas.behaviours.dychf.SayHello;
import jade.core.behaviours.Behaviour;

import java.util.ArrayList;
import java.util.List;

public class MyFirstAgent extends AbstractDedaleAgent {

    /**
     * This method is automatically called when "agent".start() is executed.
     * Consider that Agent is launched for the first time.
     * 1) set the agent attributes
     * 2) add the behaviours
     */
    protected void setup() {
        super.setup();

        List<Behaviour> lb = new ArrayList<Behaviour>();

        /************************************************
         *
         * ADD the behaviours of the Dummy Moving Agent
         *
         ************************************************/
        lb.add(new RandomWalkBehaviour(this));
        lb.add(new SayHello(this));


        /***
         * MANDATORY TO ALLOW YOUR AGENT TO BE DEPLOYED CORRECTLY
         */

        addBehaviour(new startMyBehaviours(this, lb));

    }


    /**
     * This method is automatically called after doDelete()
     */
    protected void takeDown() {
        super.takeDown();
    }

    /**
     * This method is automatically called before migration.
     * You can add here all the saving you need
     */
    protected void beforeMove() {
        super.beforeMove();
    }

    /**
     * This method is automatically called after migration to reload.
     * You can add here all the info regarding the state you want your agent to restart from
     */
    protected void afterMove() {
        super.afterMove();
    }
}

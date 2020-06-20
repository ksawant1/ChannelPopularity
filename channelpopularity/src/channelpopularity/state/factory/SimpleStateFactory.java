package channelpopularity.state.factory;

public class SimpleStateFactory implements SimpleStateFactoryI {
    StateI create(Enum event){
        StateI state=null;
        if(event.equals(UNPOPULAR.event)){
            state=new UNPOPULAR();
        }
    }
}

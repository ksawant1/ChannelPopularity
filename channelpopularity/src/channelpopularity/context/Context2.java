package channelpopularity.context;
import channelpopularity.state.StateI;
import channelpopularity.state.Unpopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;

public class Context2{

        StateI currentstate;
        StateI unpopular;
        StateI mildlypopular;
        StateI highlypopular;
        StateI ultrapopular;
        int popularity_score=0;
        

        public  Context2(){
            unpopular=new Unpopular(this);
            mildlypopular=new MildlyPopular(this);
            highlypopular= new HighlyPopular(this);
            ultrapopular=new UltraPopular(this);

            currentstate= unpopular;
        }
            

            void setStateI(StateI newstate){
                currentstate=newstate;
            }
            public void addvideo(){
                currentstate.addvideo();
            }
            public void removevideo(){
                currentstate.removevideo();
            }
            void calmetrics(int likes,int dislikes,int views){
                currentstate.calmetrics(likes,dislikes,views);
            }
            void adrequest(int length){
                currentstate.adrequest(length);
            }
            
            public StateI isUnpopular(){ return unpopular;}
            public StateI isMildlypopular(){ return mildlypopular;}
            public StateI isHighlypopular(){ return highlypopular;}
            public StateI isUltrapopular(){ return ultrapopular;}
        
}
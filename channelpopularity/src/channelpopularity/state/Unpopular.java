package channelpopularity.state;
import channelpopularity.state.StateI;
import channelpopularity.context.Context2;

public class Unpopular implements StateI{
    Context2 context2;

    public Unpopular(Context2 newcontext){
        context2=newcontext;

    }
public void addvideo(){
         System.out.println("New video added: Initial score 0");
}

public void removevideo(){
          System.out.println("Video is removed");

}

public void calmetrics(int views,int likes,int dislikes){
    

}

public void adrequest(int length){
    
}

}
package channelpopularity.helper;
import java.lang.Integer;
import channelpopularity.util.FileProcessor;
import java.util.HashMap;



public class Helper{
    String[] arrsplit,arrsplit2,arrsplit3;
    HashMap<String,String> storeinput= new HashMap<String,String>();
    int views,likes,dislikes,results=0;
    String results1;

    public Helper(){
    }

    public void printinput(String inputfile){
        //System.out.println(inputfile);
        arrsplit=inputfile.split("::");
        
            if(arrsplit[0].contains("ADD_VIDEO"))
            {
            storeinput.put(arrsplit[1],null);
            System.out.println(storeinput.size());
            }

            if(arrsplit[0].contains("METRICS"))
            {
                 
               arrsplit2=arrsplit[0].split("__");
               
               arrsplit=arrsplit[1].split(",");
                 //System.out.println(arrsplit[0]);
                   arrsplit3=arrsplit[0].split("=");
                   views=Integer.parseInt(arrsplit3[1]);
                   arrsplit3=arrsplit[1].split("=");
                   likes=Integer.parseInt(arrsplit3[1]);
                   arrsplit3=arrsplit[2].split("=");
                   arrsplit3=arrsplit3[1].split("]");
                   dislikes=Integer.parseInt(arrsplit3[0]);
                  results= (views+2*(likes-dislikes));
                  results1=String.valueOf(results);
                   System.out.println(results1); 
                   if(storeinput.containsKey(arrsplit2[1])){
                       
                   storeinput.replace(arrsplit2[1],null,results1);
                   //System.out.println(storeinput);

               }      

            
            }

            if(arrsplit[0].contains("REMOVE_VIDEO"))
            {
            if(storeinput.containsKey(arrsplit2[1])){
                   storeinput.remove(arrsplit2[1]);
                   //System.out.println(storeinput);
                  // System.out.println("yes removed");

               }

            }
            

        
    }

}
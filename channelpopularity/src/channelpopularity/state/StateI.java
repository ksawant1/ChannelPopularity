package channelpopularity.state;

public interface StateI {
    void addvideo();
    void removevideo();
    void calmetrics(int likes,int dislikes,int views);
    void adrequest(int length);

}

package channelpopularity.context;

/**
 * video context - utility for maintaining a context for video wrt it's own likes, views and dislikes
 * @author Krupa Sawant
 */
public class VideoContext implements ContextI {

    private String name;
    private int likes;
    private int dislikes;
    private int views;
    private double popularity;

    public double getPopularity() {
        return popularity;
    }

    public String getName() {
        return name;
    }

    public VideoContext(String name) {
        this.name = name;
    }

    public void updateMetrics(int views, int likes, int dislikes) {
        updateViews(views);
        updateLikes(likes);
        updateDislikes(dislikes);
    }

    private void updateViews(int views) {
        if(this.views + views < 0)
            throw new IllegalArgumentException("Cannot update the factor of views less than the current value");
        this.views += views;
    }

    private void updateLikes(int likes) {
        if(this.likes + likes < 0)
            throw new IllegalArgumentException("Cannot update the factor of likes less than the current value");
        this.likes += likes;
    }
    private void updateDislikes(int dislikes) {
        if(this.dislikes + dislikes < 0)
            throw new IllegalArgumentException("Cannot update the factor of dislikes less than the current value");
        this.dislikes += dislikes;
    }

    @Override
    public void calcPopularity() {
        popularity =  Math.abs(views + (2 * (likes - dislikes)));
    }

    @Override
    public String toString() {
        return "VideoContext{" +
                "name='" + name + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", views=" + views +
                ", popularity=" + popularity +
                '}';
    }
}

package game.states;
/**
 * Score element 
 * @author esa
 *
 */
public class Score implements Comparable{
	
	String owner;
	Integer points;
	
	public Score(String s,Integer i) {
		owner=s;
		points=i;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public Integer getPoints() {
		return points;
	}
    
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public int compareTo(Object obj) {
		return points.compareTo(((Score)obj).getPoints());
	}

}

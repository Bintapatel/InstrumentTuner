package tuner.model;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractModel implements Model {
	private ArrayList listeners = new ArrayList(5);
	
	public void notifyChanged(ModelEvent event){
		ArrayList list = (ArrayList)listeners.clone();
		Iterator it = list.iterator();
		while(it.hasNext()){
			ModelListener ml = (ModelListener)it.next();
			ml.modelChanged(event);
		}
	}
}

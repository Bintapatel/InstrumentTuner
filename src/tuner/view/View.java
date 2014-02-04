package tuner.view;
import tuner.model.Model;
import tuner.controller.Controller;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}

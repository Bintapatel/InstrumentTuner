package tuner.controller;
import tuner.model.Model;
import tuner.view.View;

public interface Controller {
	void setModel(Model model);
	Model getModel();
	void setView(View view);
	View getView();
}

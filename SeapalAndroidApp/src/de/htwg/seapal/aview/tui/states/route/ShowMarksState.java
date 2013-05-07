package de.htwg.seapal.aview.tui.states.route;

import java.util.List;
import java.util.UUID;

import android.widget.Toast;
import de.htwg.seapal.aview.tui.StateContext;
import de.htwg.seapal.aview.tui.TuiState;
import de.htwg.seapal.aview.tui.activity.MarkActivity;
import de.htwg.seapal.aview.tui.activity.RouteActivity;
import de.htwg.seapal.controller.IMarkController;
import de.htwg.seapal.controller.IRouteController;

public class ShowMarksState implements TuiState {

	private UUID route;
	private List<UUID> marks;

	public ShowMarksState(UUID route) {
		this.route = route;
	}

	@Override
	public String buildString(StateContext context) {
		IRouteController controller = ((RouteActivity) context).getController();
		IMarkController markController = ((MarkActivity) context).getController();
		marks = controller.getMarks(route);
		StringBuilder sb = new StringBuilder();
		sb.append("q - quit\n");
		sb.append("a - add a Mark to Route\n");
		sb.append("d - delete a Mark from Route\n");
		sb.append("-------------------------------------\n");
		int i = 1;
		for (UUID uuid : marks) {
			sb.append(i++).append(")\t").append(markController.getName(uuid))
					.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean process(StateContext context, String input) {
		RouteActivity activity = (RouteActivity) context;
		switch (input.charAt(0)) {
		case 'q':
			context.setState(new ShowState(route));
			break;
		case 'd':
			context.setState(new DeleteMarkState(marks));
			break;
		case 'a':
			context.setState(new EditState(route));
			break;
		default:
			Toast.makeText(activity, "Unkown Option", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}

}
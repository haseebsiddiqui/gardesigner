package objects;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import mvc.Controller;

/**
 * Anchor class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Anchor extends Circle implements Serializable {

	private final DoubleProperty x, y;
    ArrayList<Anchor> anchors = new ArrayList<>();

    /**
     * Sets common Anchors
     * 
     * @param anchors the ArrayList of anchors
     */
    public void setCommon(ArrayList<Anchor> anchors) {
        this.anchors = anchors;
    }

    /**
     * Adds an Anchor
     * 
     * @param anchor the anchor to be added
     */
    public void addAnchor(Anchor anchor) {
        anchors.add(anchor);
        Controller.anchorDragBehavior(this);
    }

	/**
	 * Constructor for Anchor. Formats the characteristics of the Anchor.
	 * 
	 * @param color the color of the anchor
	 * @param x     the x-coordinate of the anchor
	 * @param y     the y-coordinate of the anchor
	 */
    public Anchor(Color color, DoubleProperty x, DoubleProperty y) {
        super(x.get(), y.get(), 10);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        this.x = x;

        this.y = y;

        x.bind(centerXProperty());
        y.bind(centerYProperty());
        Controller.anchorDragBehavior(this);
    }
    
	/**
	 * Adds anchors to a polygon
	 * 
	 * @param shape  the shape which will have anchors added to it
	 * @param points the points of the polygon
	 * @return an ObservableList with all the anchors stored inside of them
	 */
	public static ObservableList<Anchor> createAnchors(Polygon shape, final ObservableList<Double> points) {
		ObservableList<Anchor> anchors = FXCollections.observableArrayList();

		for (int i = 0; i < points.size(); i += 2) {
			final int idx = i;

			DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
			DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));

			xProperty.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
					points.set(idx, (double) x);
				}
			});

			yProperty.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
					points.set(idx + 1, (double) y);
				}
			});
			Anchor anchor = new Anchor(Color.GOLD, xProperty, yProperty);

			anchor.layoutXProperty().bind(shape.layoutXProperty());
			anchor.layoutYProperty().bind(shape.layoutYProperty());

			anchors.add(anchor);
		}
		return anchors;
	}

	/**
	 * Enables Anchors to be dragged
	 */
	private void enableDrag() {
		final Delta dragDelta = new Delta();
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				dragDelta.x = getCenterX() - mouseEvent.getX();
				dragDelta.y = getCenterY() - mouseEvent.getY();
				getScene().setCursor(Cursor.MOVE);
			}
		});
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getScene().setCursor(Cursor.HAND);
			}
		});
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				double newX = mouseEvent.getX() + dragDelta.x;
				if (newX > 0 && newX < getScene().getWidth()) {
					setCenterX(newX);
					if (anchors != null) {
						for (Anchor anchor : anchors) {
							anchor.setCenterX(newX);
							System.out.println("CALLED");
						}
					}
				}
				double newY = mouseEvent.getY() + dragDelta.y;
				if (newY > 0 && newY < getScene().getHeight()) {
					setCenterY(newY);
					if (anchors != null) {
						for (Anchor anchor : anchors) {
							anchor.setCenterY(newY);
						}
					}
				}
			}
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.HAND);
				}
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.DEFAULT);
				}
			}
		});
	}

	/**
	 * Delta class that keeps track of movement of anchors in terms of x and y
	 * coordinate deltas
	 * 
	 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
	 *
	 */
	private class Delta {
		double x, y;
	}

	/**
	 * Gets the anchors assigned to an object
	 * 
	 * @return the anchors
	 */
	public ArrayList<Anchor> getAnchors() {
		return anchors;
	}
}

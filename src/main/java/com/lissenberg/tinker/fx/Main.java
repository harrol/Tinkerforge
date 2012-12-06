package com.lissenberg.tinker.fx;

import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.IPConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    IPConnection ip = null;
    BrickletLinearPoti poti = null;

    @Override
    public void start(Stage stage) throws Exception {

        final GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
//        grid.setGridLinesVisible(true);

        grid.add(new Label("Speed"), 0, 1);

        final TextField speed = new TextField("0");
        grid.add(speed, 1, 1);

        final Slider slider = new Slider(0, 100, 0);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setManaged(true);
        slider.setMinHeight(300);
        slider.setMinWidth(100);
        slider.setStyle("-fx-base: #ed1c24;");
        grid.add(slider, 0, 2);


        final Button start = new Button("Start");
        final Button stop = new Button("Stop");
        stop.setDisable(true);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ip = new IPConnection("localhost", 4223);
                    poti = new BrickletLinearPoti("bxs");
                    poti.addListener(new BrickletLinearPoti.PositionListener() {
                        @Override
                        public void position(int position) {
                            slider.setValue(position);
                            speed.setText("" + position);
                        }
                    });
                    ip.addDevice(poti);
                    poti.setPositionCallbackPeriod(100);
                    start.setDisable(true);
                    stop.setDisable(false);
                    slider.setValue(poti.getPosition());
                    speed.setText("" + poti.getPosition());
                } catch (IOException | IPConnection.TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ip.destroy();
                    stop.setDisable(true);
                    start.setDisable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        grid.add(start, 0, 0);
        grid.add(stop, 1, 0);

        Scene scene = SceneBuilder.create()
                .width(600)
                .height(400)
                .root(grid)
                .build();
        stage.setTitle("TinkerFX");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

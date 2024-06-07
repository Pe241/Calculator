package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calc extends Application{
	Button[] numbtn = new Button[10];
	Button plusbtn = new Button("＋");
	Button minusbtn = new Button("－");
	Button timesbtn = new Button("×");
	Button divbtn = new Button("÷");
	Button equalbtn = new Button("＝");
	Button dotbtn = new Button(".");
	Button clearbtn = new Button("C");
	TextField panel = new TextField("0");
	BorderPane bp = new BorderPane();
	boolean isFirstNum = true;
	boolean dotflag = false;
	int btnsize = 50;
	double panelvalue = 0;
	double memory = 0;
	String lastop = "";
	String pushedop = "";
	public void start(Stage primaryStage) {		
		makeLayout();
		Scene scene = new Scene(bp, 250, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Caluclator");
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	void makeLayout() {
		for(int num=0;num < numbtn.length;num++) {
			numbtn[num]=new Button(String.valueOf(num));
			numbtn[num].setOnAction(e->numact(e));
			numbtn[num].setPrefSize(btnsize, btnsize);
		}
		plusbtn.setOnAction(e -> opact(e));
		plusbtn.setPrefSize(btnsize, btnsize);
		minusbtn.setOnAction(e -> opact(e));
		minusbtn.setPrefSize(btnsize, btnsize);
		timesbtn.setOnAction(e -> opact(e));
		timesbtn.setPrefSize(btnsize, btnsize);
		divbtn.setOnAction(e -> opact(e));
		divbtn.setPrefSize(btnsize, btnsize);
		dotbtn.setOnAction(e -> dotact());
		dotbtn.setPrefSize(btnsize, btnsize);
		equalbtn.setOnAction(e -> opact(e));
		equalbtn.setPrefSize(btnsize, btnsize);
		clearbtn.setOnAction(e -> {
			if(isFirstNum) {
				memory = 0;
				lastop = "";
				pushedop = "";
			}
			panelvalue = 0;
			panel.setText("0");
			isFirstNum = true;
			dotflag = false;
		});
		clearbtn.setPrefSize(btnsize, btnsize);
		panel.setAlignment(Pos.CENTER_RIGHT);
    	panel.setEditable(false);
		HBox Top = new HBox(10,panel,clearbtn);
		Top.setAlignment(Pos.CENTER);
		VBox Right = new VBox(0,plusbtn,minusbtn,timesbtn,divbtn);
		Right.setAlignment(Pos.CENTER);
		HBox Center1 = new HBox(0,numbtn[7],numbtn[8],numbtn[9]);
		Center1.setAlignment(Pos.CENTER);
		HBox Center2 = new HBox(0,numbtn[4],numbtn[5],numbtn[6]);
		Center2.setAlignment(Pos.CENTER);
		HBox Center3 = new HBox(0,numbtn[1],numbtn[2],numbtn[3]);
		Center3.setAlignment(Pos.CENTER);
		HBox Center4 = new HBox(0,dotbtn,numbtn[0],equalbtn);
		Center4.setAlignment(Pos.CENTER);
		VBox Center = new VBox(0,Center1,Center2,Center3,Center4);
		Center.setAlignment(Pos.CENTER);
		bp.setTop(Top);
		bp.setRight(Right);
		bp.setCenter(Center);
	}
	void numact(ActionEvent e) {
		String Numstring = ((Button)e.getSource()).getText();
		if(isFirstNum) {
			panel.setText(Numstring);
			isFirstNum = false;
			dotflag = false;
		}
		else {
			panel.appendText(Numstring);
		}
	}
	void roadpanel() {
		panelvalue = Double.parseDouble(panel.getText());
	}
	void writepanel() {
		if(memory==(long)memory)
		panel.setText(String.valueOf((long)memory));
		else
		panel.setText(String.valueOf(memory));		
	}
	void opact(ActionEvent e) {
		roadpanel();
		pushedop = ((Button)e.getSource()).getText();
		if(lastop=="") {
			memory = panelvalue;
		}
		else {
			switch(lastop) {
			case "＋":{
				memory = memory + panelvalue;
				writepanel();
				break;
			}
			case "－":{
				memory = memory - panelvalue;
				writepanel();
				break;
			}
			case "×":{
				memory = memory * panelvalue;
				writepanel();
				break;
			}
			case "÷":{
				if(panelvalue!=0) {
					memory = memory / panelvalue;
					writepanel();
				}
				else {
					panel.setText("0で割る事は出来ません");
					isFirstNum = true;
					dotflag = false;
				}
				break;
			}
			}
		}
		isFirstNum = true;
		lastop = pushedop;
		dotflag=true;
	}
	void dotact() {
		if(!dotflag) {
			panel.appendText(".");
			isFirstNum = false;
			dotflag = true;
		}
	}
}

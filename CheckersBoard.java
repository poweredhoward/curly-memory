package Checkers;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import javax.xml.bind.JAXBElement.GlobalScope;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class CheckersBoard extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	int col1 = 0;
	int row1 = 0;


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Welcome");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(0);
		grid.setVgap(0);
		grid.setPadding(new Insets(5, 5, 5, 5));
		GridPane.setColumnIndex(grid, 8);
		GridPane.setRowIndex(grid, 8);
		grid.getChildren().addAll();
		grid.setGridLinesVisible(true);

		Rectangle[][] space = new Rectangle[8][8];

		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8 ; y++){
				if((x+y)%2 == 0){
					space [x][y] = new Rectangle(65,65, Color.RED);
					grid.add(space[x][y], x, y);
				}
				else{
					space [x][y] = new Rectangle(65,65,Color.BLACK);
					grid.add(space[x][y], x, y);
				}
			}
		}
		Circle piece[] = new Circle[24];
		for (int i = 0; i < 24 ; i++){  //make pieces
			if (i < 12 ){
				piece[i] = new Circle(32, Color.AQUAMARINE);
			}
			else{
				piece[i] = new Circle(32, Color.PINK);
			}
		}	


		//System.out.print(circ[4].getFill());

		//		piece[2].setOnMousePressed((MouseEvent event) - > {
		//	        System.out.println("Mouse exited");
		//		});


		//}


		/*for(int i = 0; i< 24 ; i++){
			int k = i;
			piece[i].setOnMousePressed(e -> {
				//System.out.print("Clicked " + k);
				col1 = (int) (getColumnPiece(piece[k].getLayoutX()));
				row1 = (int) (getRowPiece(piece[k].getLayoutY()));
				System.out.println("Column: " + col1);
				System.out.println("Row: " + row1);

			});
		}*/


		//		System.out.println("Column: " + col1);
		//		System.out.println("Row: " + row1);


		Scene scene = new Scene(grid, 650, 650);
		primaryStage.setScene(scene);
		primaryStage.show();


		int h = 0;

		while(h < 24){
			for(int q = 0; q < 8; q++){ // set pieces
				for(int p = 0; p < 8 ; p++){

					//Make player a variable, and increment after each turn. Use a mod statement (even/odd) to determine which player's
					//move it is. Use a break statement to leave the while loop once someone wins
					int j = h;	
					int x = q;             
					int y = p;
					if(((x+y)%2 == 1) && x <= 2){


						grid.add(piece[j], y,x);
						//grid.getChildren().remove(piece[j]);
						h++;
					}
					else if(((x+y)%2 == 1) && x >= 5){
						grid.add(piece[j], y,x);
						h++;
					}

				}
			}
		}

		piece[11].setFill(Color.YELLOW);
		

		for(int i = 0 ; i < piece.length ; i++){
			int j = i;
			piece[j].setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent t)
				{
					int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
					int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
					grid.getChildren().remove(piece[j]);

					
						space[xpos][ypos].setOnMouseClicked(new EventHandler<MouseEvent>()
						{  //If user change mind and and don't want to move piece
						   //Sets piece back to original position
							
							@Override
							public void handle(MouseEvent t)
							{
								int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
								int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
								//grid.getChildren().remove(piece[j]);
								grid.add(piece[j], xpos, ypos);
							
							}
						});
						
						try{
						space[xpos+1][ypos+1].setOnMouseClicked(new EventHandler<MouseEvent>()
						{ //sets piece one valid space away if user clicks it
							@Override
							public void handle(MouseEvent t)
							{
								int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
								int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
								//grid.getChildren().remove(piece[j]);
								grid.add(piece[j], xpos+1, ypos+1);


							}
						});
						}catch(ArrayIndexOutOfBoundsException e){
						
						
						space[xpos-1][ypos+1].setOnMouseClicked(new EventHandler<MouseEvent>()
						{ //sets piece one valid space away if user clicks it
							@Override
							public void handle(MouseEvent t)
							{
								int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
								int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
								//grid.getChildren().remove(piece[j]);
								grid.add(piece[j], xpos-1, ypos+1);
								


							}
						});
						}
						
						try{

							space[xpos-1][ypos+1].setOnMouseClicked(new EventHandler<MouseEvent>()
							{ //sets piece one valid space away if user clicks it
								@Override
								public void handle(MouseEvent t)
								{
									int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
									int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
									//grid.getChildren().remove(piece[j]);
									grid.add(piece[j], xpos-1, ypos+1);
									
								}
							});
							}catch(ArrayIndexOutOfBoundsException e){
							
							
							space[xpos+1][ypos+1].setOnMouseClicked(new EventHandler<MouseEvent>()
							{ //sets piece one valid space away if user clicks it
								@Override
								public void handle(MouseEvent t)
								{
									int xpos = (int) (getColumnPiece(piece[j].getLayoutX()));
									int ypos = (int) (getRowPiece(piece[j].getLayoutY()));
									//grid.getChildren().remove(piece[j]);
									grid.add(piece[j], xpos+1, ypos+1);


								}
							});
							}

						
						
					//grid.add(piece[j], xpos-1, ypos+1);
				}
			});

		}
		
		
		
		/*for(int x = 0; x < 8; x++){
				int p = x;
				for(int y = 0; y < 8 ; y++){
					int q = y;
					space[x][y].setOnMousePressed(e -> {
						System.out.println("Tile Column: " + p);
						System.out.println("Tile Row: " + q);
					});
				}
			}*/



		//System.out.print(col1);
		//piece[4].addEventFilter(MouseEvent.MOUSE_PRESSED,filter );


		//PRINTING WHERE A PIECE IS
		/*int col1 = (int) (getColumnPiece(piece[6].getLayoutX()));
        int row1 = (int) (getRowPice(piece[6].getLayoutY()));
        System.out.println("Column: " + col1);
        System.out.println("Row: " + row1);   
		 */



		//piece[19].setFill(Color.FUCHSIA);

	}




	public double getColumnPiece(double d){
		double r = (d-97)/65;
		return r;
	}

	public double getRowPiece(double d){
		double r = (d-97)/65;
		return r;
	}
}



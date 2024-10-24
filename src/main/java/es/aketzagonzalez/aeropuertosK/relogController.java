package es.aketzagonzalez.aeropuertosK;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.stage.WindowEvent; 

/**
 * Clase relogController.
 */
public class relogController {

    /** El id de la imagen de la hora de la derecha. */
    @FXML
    private ImageView idHDer;

    /** El id de la imagen de la hora de la izquierda. */
    @FXML
    private ImageView idHIz;

    /** El id de la imagen del minuto de la derecha */
    @FXML
    private ImageView idMinDer;

    /** El id de la imagen del minuto de la izquierda. */
    @FXML
    private ImageView idMinIz;

    /** El id de la imagen del segundo de la derecha. */
    @FXML
    private ImageView idSegDer;

    /** TEl id de la imagen del segundo de la izquierda */
    @FXML
    private ImageView idSegIz;
    
    /** El timer. */
    private Timer timer;
    
    /**
     * Inicializa el relog y finaliza la ejecucion del programa al cerrar la ventana.
     */
    @FXML
    public void initialize() {
    	MainApp.getStage().setOnCloseRequest((WindowEvent event) -> {
    		parar();
            Platform.exit();
        });
    	iniciarRelog();
    }

	/**
	 * Ejecuta el metodo actualizar relog y dice que debe cambiar cada 1000 ms (1s).
	 */
	private void iniciarRelog() {
		timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	actualizarRelog();
            }
        }, 0, 1000);
	}
	
	/**
	 * Update clock.
	 */
	private void actualizarRelog() {
		Platform.runLater(() -> {
			LocalDateTime currentTime = LocalDateTime.now();
			int hours = currentTime.getHour();
	        int minutes = currentTime.getMinute();
	        int seconds = currentTime.getSecond();
	        actualizarImagen(idHIz, hours / 10);
	        actualizarImagen(idHDer, hours % 10);
	        actualizarImagen(idMinIz, minutes / 10);
	        actualizarImagen(idMinDer, minutes % 10);
	        actualizarImagen(idSegIz, seconds / 10);
	        actualizarImagen(idSegDer, seconds % 10);
		});
	}

	/**
	 * Actualiza las imagenes para que se muestre la hora actual.
	 *
	 * @param imagen La imagen
	 * @param i the i
	 */
	private void actualizarImagen(ImageView imagen, int i) {
		String num="";
		switch (i) {
		case 0:
			num="ZERO";
			break;
		case 1:
			num="ONE";
			break;
		case 2:
			num="TWO";
			break;
		case 3:
			num="THREE";
			break;
		case 4:
			num="FOUR";
			break;
		case 5:
			num="FIVE";
			break;
		case 6:
			num="SIX";
			
			break;
		case 7:
			num="SEVEN";
			break;
		case 8:
			num="EIGHT";
			break;
		case 9:
			num="NINE";
			break;
		}
		imagen.setImage(new Image(getClass().getResource("/imagenes/"+num+".png").toString()));
	}
	
	/**
	 * para el relog, haciendo que la ejecucion del programa finalice.
	 */
	private void parar() {
        if (timer != null) {
            timer.cancel();
        }
    }

}

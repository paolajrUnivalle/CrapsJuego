package co.edu.univalle.poe.mvc.controller;

import co.edu.univalle.poe.mvc.model.EstadoPartida;
import co.edu.univalle.poe.mvc.model.JuegoCraps;
import co.edu.univalle.poe.mvc.model.Lanzamiento;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class CrapsController {

    private JuegoCraps juegoCraps;

    @FXML
    private Label lblSuma;

    @FXML
    private ImageView imgDado1;

    @FXML
    private ImageView imgDado2;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnLanzar;

    @FXML
    private Button btnNuevaPartida;


    @FXML
    private void onLanzarDados(){
            //usar JuegoCraps
            Lanzamiento lanzamiento = juegoCraps.lanzarDados();
            int valorDado1 = lanzamiento.getValorDado1();
            int valorDado2 = lanzamiento.getValorDado2();
            int suma = lanzamiento.calcularSuma();

            //actualizar los resultados del lanzamiento en la vista
            lblSuma.setText("Suma: " + String.valueOf(suma));

            String rutaImagen = "/co/edu/univalle/poe/mvc/images/dado-" + valorDado1 + ".png";
            Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            imgDado1.setImage(imagen);

            rutaImagen = "/co/edu/univalle/poe/mvc/images/dado-" + valorDado2 + ".png";
            imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            imgDado2.setImage(imagen);

            actualizarEstadoPartida();
    }

    @FXML
    private void onNuevaPartida(MouseEvent mouseEvent){
       //actualizo las reglas del juego (reset punto y estado del juego)
        juegoCraps.iniciarPartida();
        //actualizar la GUI
        btnLanzar.setDisable(false);
        btnNuevaPartida.setDisable(true);
        lblSuma.setText("Suma: 0");
        lblMensaje.setText("Iniciando nueva partida ...");
        String rutaImagen = "/co/edu/univalle/poe/mvc/images/dado-inicial.png";
        Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
        imgDado1.setImage(imagen);
        imgDado2.setImage(imagen);

    }

    public CrapsController(){
        juegoCraps = new JuegoCraps();
    }

    private void actualizarEstadoPartida(){
        EstadoPartida estadoPartida = juegoCraps.getEstadoPartida();

        switch (estadoPartida){
            case GANADA: lblMensaje.setText("¡Ganaste la partida! Inicia una nueva para volver a jugar.");
                    btnLanzar.setDisable(true);
                    btnNuevaPartida.setDisable(false);
                break;
            case PERDIDA: lblMensaje.setText("Perdiste la partida. Inicia una nueva para intentarlo otra vez.");
                btnLanzar.setDisable(true);
                btnNuevaPartida.setDisable(false);
                break;
            case EN_CURSO: lblMensaje.setText("Punto establecido: " + juegoCraps.getPunto()
                                               + " Sigue lanzando. Ganas si repites el punto antes de obtener 7.");
                btnLanzar.setDisable(false);
                btnNuevaPartida.setDisable(true);
                break;
        }
    }

    @FXML
    private void aumentarDado(MouseEvent mouseEvent){
        if(mouseEvent.getSource()==imgDado1){
            imgDado1.setScaleX(1.5);
            imgDado1.setScaleY(1.5);
        }else{
            imgDado2.setScaleX(1.5);
            imgDado2.setScaleY(1.5);
        }
    }

    @FXML
    private void restaurarDado(MouseEvent mouseEvent){
        if(mouseEvent.getSource()==imgDado1){
            imgDado1.setScaleX(1.0);
            imgDado1.setScaleY(1.0);
        }else{
            imgDado2.setScaleX(1.0);
            imgDado2.setScaleY(1.0);
        }
    }

    @FXML
    private void lanzarDadosConTeclado(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.L && keyEvent.isControlDown()){
            //usar JuegoCraps
            Lanzamiento lanzamiento = juegoCraps.lanzarDados();
            int valorDado1 = lanzamiento.getValorDado1();
            int valorDado2 = lanzamiento.getValorDado2();
            int suma = lanzamiento.calcularSuma();

            //actualizar los resultados del lanzamiento en la vista
            lblSuma.setText("Suma: " + String.valueOf(suma));

            String rutaImagen = "/co/edu/univalle/poe/mvc/images/dado-" + valorDado1 + ".png";
            Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            imgDado1.setImage(imagen);

            rutaImagen = "/co/edu/univalle/poe/mvc/images/dado-" + valorDado2 + ".png";
            imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            imgDado2.setImage(imagen);

            actualizarEstadoPartida();
        }
    }

    public void crearVentanaEmergente(Event event){
        //Instanciar la ventana dialogo
        Alert alert = new Alert(Alert.AlertType.NONE);
        //Titulo
        alert.setTitle("Confirmación de Cierre");
        //encabezado
        alert.setHeaderText("¿Está seguro que quiere cerrar el juego?");
        alert.setContentText("Si confirmas, se cerrará la aplicación");
        //icono
        Image image = new Image(getClass().getResourceAsStream("/co/edu/univalle/poe/mvc/images/parar.png"));
        ImageView icono = new ImageView(image);
        icono.setFitHeight(48);
        icono.setFitWidth(48);
        icono.setPreserveRatio(true);
        alert.setGraphic(icono);
        //agregar los botones
        ButtonType btnNo = new ButtonType("No, continuar Jugando", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType btnSi = new ButtonType("Si, salir de Craps");
        alert.getButtonTypes().addAll(btnNo,btnSi);

        //identificar el botón seleccionado
        Optional<ButtonType> result =  alert.showAndWait();
        if(result.isPresent()){
            if(result.get()==btnNo){
                System.out.println("El usuario quiere continuar jugando");
                event.consume();
            }else{
                System.out.println("El usuario quiere cerrar Craps");
            }
        }
    }
}

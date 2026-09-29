package co.edu.univalle.poe.mvc.model;

public class JuegoCraps {
    private Dado dado1;
    private Dado dado2;
    private EstadoPartida estadoPartida;
    private int punto;

    public JuegoCraps(){
        dado1 = new Dado();
        dado2 = new Dado();

        iniciarPartida();
    }

    public void iniciarPartida(){
        punto = 0;
        estadoPartida = EstadoPartida.EN_CURSO;
    }

    public Lanzamiento lanzarDados(){
        int valorDado1 = dado1.lanzar();
        int valorDado2 = dado2.lanzar();

        Lanzamiento lanzamiento = new Lanzamiento(valorDado1, valorDado2);
        aplicarReglas(lanzamiento.calcularSuma());
        return lanzamiento;
    }

    private void aplicarReglas(int suma){
        if(!hayPuntoEstablecido()){
           evaluarPrimerLanzamiento(suma);
        }else{
           evaluarLanzamientoPosterior(suma);
        }
    }

    private void evaluarPrimerLanzamiento(int suma){
        if(suma == 7 || suma == 11){
            estadoPartida = EstadoPartida.GANADA;
        }else{
            if(suma == 2 || suma == 3 || suma == 12){
                estadoPartida = EstadoPartida.PERDIDA;
            }else{
                punto = suma;
            }
        }
    }

    private void evaluarLanzamientoPosterior(int suma){
        if(suma == punto){
            estadoPartida = EstadoPartida.GANADA;
        }else{
            if(suma == 7){
                estadoPartida = EstadoPartida.PERDIDA;
            }
        }
    }

    public boolean hayPuntoEstablecido(){
        return punto != 0;
    }

    public boolean puedeLanzar(){
        return estadoPartida == EstadoPartida.EN_CURSO;
    }

    public boolean partidaTerminada(){
        return estadoPartida == EstadoPartida.GANADA || estadoPartida == EstadoPartida.PERDIDA;
    }

    public EstadoPartida getEstadoPartida() {
        return estadoPartida;
    }

    public int getPunto() {
        return punto;
    }
}

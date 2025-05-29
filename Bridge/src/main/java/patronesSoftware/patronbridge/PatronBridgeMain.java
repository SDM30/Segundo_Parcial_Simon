/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Bridge
 * Tipo de Clase: Main()
 */

package patronesSoftware.patronbridge;
import patronesSoftware.encriptacion.*;
import patronesSoftware.fabricapuente.FabricaEncriptacion;
import patronesSoftware.implementacion.InterfaceMensajeEncriptacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class PatronBridgeMain {

    public static void main(String[] args) {
        FabricaEncriptacion fabrica = new FabricaEncriptacion();
        InterfaceMensajeEncriptacion encriptadorMensaje = fabrica.crearEncriptadorMensaje();

        try {
            final String message = "<Curso><Nombre>Patrones de Diseño de Software</Nombre></Curso>";
            String messageEncriptado = encriptadorMensaje.EncryptarMensaje(message, "HG58YZ3CR9123456");
            System.out.println("Mensaje Original: " + message);
            System.out.println("Mensaje Encriptado: " + messageEncriptado);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
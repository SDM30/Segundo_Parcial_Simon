package patronesSoftware.fabricapuente;

import patronesSoftware.encriptacion.ProcesoEncriptarAES;
import patronesSoftware.encriptacion.ProcesoEncriptarBlowfish;
import patronesSoftware.encriptacion.ProcesoEncriptarDES;
import patronesSoftware.encriptacion.ProcesoSinEncriptar;
import patronesSoftware.implementacion.InterfaceMensajeEncriptacion;
import patronesSoftware.implementacion.PuenteMensajeEncriptacion;

import java.io.IOException;
import java.util.Properties;

public class FabricaEncriptacion {

    static String tipoEncriptacion;

    public FabricaEncriptacion() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream("bridgeConfig.properties"));
            this.tipoEncriptacion = prop.getProperty("puente", "SIN_ENCRIPTAR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InterfaceMensajeEncriptacion crearEncriptadorMensaje() {
        switch (tipoEncriptacion.toUpperCase()) {
            case "AES":
                return new PuenteMensajeEncriptacion(new ProcesoEncriptarAES());
            case "DES":
                return new PuenteMensajeEncriptacion(new ProcesoEncriptarDES());
            case "BLOWFISH":
                return new PuenteMensajeEncriptacion(new ProcesoEncriptarBlowfish());
            default:
                return new PuenteMensajeEncriptacion(new ProcesoSinEncriptar());
        }
    }

}
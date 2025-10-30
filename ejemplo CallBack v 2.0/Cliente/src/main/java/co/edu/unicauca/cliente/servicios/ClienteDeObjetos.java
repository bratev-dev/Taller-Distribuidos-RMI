package co.edu.unicauca.cliente.servicios;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckImpl;
import co.edu.unicauca.cliente.utilidades.UtilidadesConsola;
import co.edu.unicauca.cliente.utilidades.UtilidadesRegistroC;
import co.edu.unicauca.servidor.controladores.ControladorServidorChatInt;
import java.util.List;

public class ClienteDeObjetos {
    public static void main(String[] args) {

        try {
            ControladorServidorChatInt servidor;
            int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";
            direccionIpRMIRegistry = UtilidadesConsola.leerCadena("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

            servidor = (ControladorServidorChatInt) UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry,
                    direccionIpRMIRegistry, "ServidorChat");

            UsuarioCllbckImpl objNuevoUsuario = new UsuarioCllbckImpl();

            Boolean respRegistro = false;
            String nickname = "";
            while (!respRegistro) {
                nickname = UtilidadesConsola.leerCadena("Ingrese su nickname: ");
                respRegistro = servidor.registrarReferenciaUsuario(objNuevoUsuario, nickname);
                if (!respRegistro) {
                    System.out.println("Intente de nuevo con otro nickname: ");
                }
            }
            int opcion = 0;
            do {
                System.out.println("Menu de opciones");
                System.out.println("1. Enviar mensaje");
                System.out.println("2. Listar usuarios conectados");
                System.out.println("3. Salir");
                System.out.println("Digite la opcion: ");
                opcion = UtilidadesConsola.leerEntero();
                switch (opcion) {
                    case 1:
                        String mensaje = UtilidadesConsola.leerCadena("Digite el mensaje a enviar al servidor: ");
                        servidor.enviarMensaje(mensaje);
                        
                        break;
                    case 2:
                        System.out.println("Usuarios conectados: ");
                        List<String> usuarios = servidor.listarUsuarios();
                        for (String user : usuarios) {
                            System.out.println("- " + user);
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo del chat...");
                        servidor.desconectarUsuario(nickname);
                        break;

                    default:
                        break;
                }
            } while (opcion != 3);

        } catch (Exception e) {
            System.out.println("No se pudo realizar la conexion...");
            System.out.println(e.getMessage());
        }

    }

}

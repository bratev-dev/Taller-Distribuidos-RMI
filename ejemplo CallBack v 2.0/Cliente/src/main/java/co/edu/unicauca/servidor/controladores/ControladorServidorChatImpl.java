package co.edu.unicauca.servidor.controladores;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;

public class ControladorServidorChatImpl {
    private final HashMap<String, UsuarioCllbckInt> usuarios; //lista que almacena la referencia remota de los clientes y su nickname

    public ControladorServidorChatImpl() throws RemoteException
    {
        super();//asignamos el puerto 
        usuarios= new HashMap();
    }

    public boolean registrarReferenciaUsuario(UsuarioCllbckInt usuario, String nickname) throws RemoteException {
        // TO DO
        return false;
    }

    public void enviarMensaje(String mensaje) throws RemoteException {
        // TO DO
    }

    public List<String> listarUsuarios() throws RemoteException {
        // TO DO
        return null;
    }

    public boolean eliminarReferenciaUsuario(String nickname) throws RemoteException {
        // TO DO
        return false;
    }

    public void enviarMensajePrivado(String nicknameDestino, String mensaje) throws RemoteException {
        // TO DO
    }

    private void notificarUsuarios(String mensaje) throws RemoteException 
    {
        System.out.println("Invocando al método notificar usuarios desde el servidor");
        for(UsuarioCllbckInt objUsuario: usuarios.values())
        {
            objUsuario.notificar(mensaje, usuarios.size());//el servidor hace el callback
            
        }
    }

    public boolean desconectarUsuario(String nickname) throws RemoteException {
        System.out.println("Desconectando usuario: " + nickname);
        boolean removed = usuarios.remove(nickname) != null;
        if (removed) {
            notificarUsuarios("El usuario " + nickname + " se ha desconectado del chat");
        }
        return removed;
    }
}

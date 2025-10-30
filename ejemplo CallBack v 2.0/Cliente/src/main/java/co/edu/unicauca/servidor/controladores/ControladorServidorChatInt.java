package co.edu.unicauca.servidor.controladores;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ControladorServidorChatInt extends Remote
{
    public boolean registrarReferenciaUsuario(UsuarioCllbckInt  usuario, String nickname) throws RemoteException;
    public void enviarMensaje(String mensaje)throws RemoteException;
    public List<String> listarUsuarios() throws RemoteException;
    public boolean eliminarReferenciaUsuario(String nickname) throws RemoteException;
    public void enviarMensajePrivado(String nicknameDestino, String mensaje) throws RemoteException;
    public boolean desconectarUsuario(String nickname) throws RemoteException; // Nuevo método
}



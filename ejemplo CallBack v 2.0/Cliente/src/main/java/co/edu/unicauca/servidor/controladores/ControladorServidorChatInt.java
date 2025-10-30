package co.edu.unicauca.servidor.controladores;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorServidorChatInt extends Remote
{
    public boolean registrarReferenciaUsuario(UsuarioCllbckInt  usuario, String nickname) throws RemoteException;
    public void enviarMensaje(String mensaje)throws RemoteException;
    public List<String> listarUsuarios() throws RemoteException;
    public boolean eliminarReferenciaUsuario(string nickname) throws RemoteException;
    public void enviarMensajePrivado(String nicknameDestino, String mensaje) throws RemoteException;
}



package co.edu.unicauca.servidor.controladores;


import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ControladorServidorChatImpl extends UnicastRemoteObject implements ControladorServidorChatInt {

    private final HashMap<String, UsuarioCllbckInt> usuarios; //lista que almacena la referencia remota de los clientes y su nickname

    public ControladorServidorChatImpl() throws RemoteException
    {
        super();//asignamos el puerto 
        usuarios= new HashMap();
    }
    
    @Override
    public synchronized boolean  registrarReferenciaUsuario(UsuarioCllbckInt usuario, String nickname) throws RemoteException 
    {
       //método que unicamente puede ser accedido por un hilo
	System.out.println("Invocando al método registrar usuario desde el servidor");
        boolean bandera=false;
        if (!usuarios.containsKey(nickname))
        {
            usuarios.put(nickname, usuario);
            bandera= true;  
        }else
        {
            System.out.println("El nickname ya existe en el sistema");
            throw new RemoteException("El nickname ya existe en el sistema");
        }        
        return bandera;       
    }
   
    @Override
    public void enviarMensaje(String mensaje)throws RemoteException 
    {        
        notificarUsuarios("un cliente envio el siguiente mensaje: " + mensaje);
    }
    
    private void notificarUsuarios(String mensaje) throws RemoteException 
    {
        System.out.println("Invocando al método notificar usuarios desde el servidor");
        for(UsuarioCllbckInt objUsuario: usuarios.values())
        {
            objUsuario.notificar(mensaje, usuarios.size());//el servidor hace el callback
            
        }
    }

    @Override
    public synchronized boolean desconectarUsuario(String nickname) throws RemoteException 
    {
        System.out.println("Desconectando usuario: " + nickname);
        boolean removed = usuarios.remove(nickname) != null;
        if (removed) {
            notificarUsuarios("El usuario " + nickname + " se ha desconectado del chat");
        }
        return removed;
    }
}

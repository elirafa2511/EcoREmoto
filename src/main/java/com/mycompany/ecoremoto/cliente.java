/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ecoremoto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

/**
 *
 * @author ryukai
 */
public class cliente {
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("CLIENTE EN LINEA...\n");
        
        try {  
            DatagramSocket ClienteUDP = new DatagramSocket();
            //declaramos una variable entera y le asignamos el puerto que usaremos
            int puerto = 5001;
            //arreglo de tipo byte para asignarle una direccion 
            String add = JOptionPane.showInputDialog("Ingrese el valor de la Ip"); 
            //obtenemos la direccion ip por medio del getByAddress 
            InetAddress host = InetAddress.getByName(add);
            //declaramos el string para pedir el mensaje
            String message;
            //iniciamos el ciclo for
            for(;;){
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                message = JOptionPane.showInputDialog("INGRESE UNA CADENA (solamente 'enter' para salir):");
                //pedimos la cadena
                
                //asignamos un array de bytes con los byte10s que se obtienen de la cadena que se ingreso
                byte [] mensaje= message.getBytes();
                //de la clase de datagramas creamos la instancia de la clase en peticion 
                DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, host, puerto);
                //hacemos el envio del datagrama hacia el server 
                ClienteUDP.send(peticion);
                //hacemos la instancia con nuevo datagrama en el cual recibiremos la respuesta
                DatagramPacket respuesta = new DatagramPacket(mensaje, mensaje.length);
                //recibimos la respuesta por parte del server
                ClienteUDP.receive(respuesta);
                //si la longitud de la respuesta es 0 terminamos el programa del cliente
                if(respuesta.getLength() == 0){
                    JOptionPane.showMessageDialog(null,"SALIENDO DEL CLIENTE...");
                    //salir del programa
                    System.exit(0);
                }else{
                //si el metodo getLength verifica que no es una cadena vacia
                //mostramos en pantalla la respuesta que nos ha mandado el server
                JOptionPane.showMessageDialog(null,"LA RESPUESTA DEL SERVER ES: " + new String(respuesta.getData(), 0, respuesta.getLength()));
            }//ciclo infinito de for...
            }
        //capturamos las excepciones..    
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
   }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * Esta clase se encarga de cifrar y decifrar datos usando SHA-256(No decifrable) y Base64.
 * @author Andres Marlex
 */
public class Cifrador {
    
    /**
     * Se encarga de cifrar un String usando el metodo de hash (SHA-256).
     *
     * @param data Datos a cifrar.
     * @return El parametro data cifrado en SHA-256.
     * @throws java.lang.Exception Si un error ocurre.
     */
    
    public String hash(String data) throws Exception { 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
        md.reset();
        byte[] b = md.digest(data.getBytes()); 
        int size = b.length;
        StringBuffer h = new StringBuffer(size); 
        for (int i = 0; i < size; i++) { 
            int u = b[i]&255; 
            if (u<16) { 
                h.append("0"+Integer.toHexString(u)); 
            } else { 
                h.append(Integer.toHexString(u)); 
            } 
        } 
        return h.toString(); 
    } 
    
    /**
     * Se encarga de cifrar un String usando el metodo de Base64.
     *
     * @param data Datos a cifrar.
     * @return El parametro data cifrado en Base64.
     */
    
    public String codificarB64(String data){ 
        return Base64.getEncoder().encodeToString(data.getBytes());
    } 
    
    /**
     * Se encarga de decifrar un String usando el metodo de Base64.
     *
     * @param data Datos a decifrar.
     * @return El parametro data decifrado en Base64.
     */
    
    public String decodificarB64(String data){ 
        byte[] bytesDecodificados = Base64.getDecoder().decode(data);
        String cadenaDecodificada = new String(bytesDecodificados);
        return cadenaDecodificada;
    } 
}

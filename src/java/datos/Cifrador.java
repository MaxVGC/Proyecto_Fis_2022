/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author carlo
 */
public class Cifrador {
    public String hash(String clear) throws Exception { 
        
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
        md.reset();
        byte[] b = md.digest(clear.getBytes()); 
        int size = b.length;
        StringBuffer h = new StringBuffer(size); 
        for (int i = 0; i < size; i++) { 
            int u = b[i]&255; // unsigned conversion 
            if (u<16) { 
                h.append("0"+Integer.toHexString(u)); 
            } else { 
                h.append(Integer.toHexString(u)); 
            } 
        } 
        return h.toString(); 
    } 
    
    public String codificarB64(String clear){ 
        return Base64.getEncoder().encodeToString(clear.getBytes());
    } 
    
    public String decodificarB64(String clear){ 
        byte[] bytesDecodificados = Base64.getDecoder().decode(clear);
        String cadenaDecodificada = new String(bytesDecodificados);
        return cadenaDecodificada;
    } 
}

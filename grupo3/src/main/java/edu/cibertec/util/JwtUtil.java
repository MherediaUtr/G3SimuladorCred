package edu.cibertec.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyList;

@Component
public class JwtUtil {
    // Clave secreta para firmar el JWT
    //private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Las aplicaciones deben de estar en el mismo contexto
    private static SecretKey SECRET_KEY; //= Keys.hmacShaKeyFor("GRUPO3-APLICACION-FINANCIERA-1234".getBytes());     
    
    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKeyString) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public static void generarToken(HttpServletResponse res, String userName, String role) {
        String token = Jwts.builder()
                // Se agrega los datos del Payload
                .subject("Datos del usuario")
                .claim("userName",userName) // Ejemplo de agregar un rol al token
                .claim("role",role) // Ejemplo de agregar un rol al token
                // Se asigna un tiempo de expiración de 1 minuto
                .expiration(new Date(System.currentTimeMillis() + 60000))
                // Hash con el que firmaremos la clave
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer " + token);
    }

    // Método para validar el token enviado por el cliente
    public static Authentication leerToken(HttpServletRequest request) {
        String user;
        String role="USER";
        // Obtenemos el token que viene en el encabezado de la petición
        String token = request.getHeader("Authorization");
        // si hay un token presente, entonces se valida
        if (token != null) {
            try{
                user=Jwts.parser()
                         // Hash con el que firmaremos la clave
                         .verifyWith((SecretKey)SECRET_KEY).build()
                         // Token
                         .parseSignedClaims(token.replace("Bearer", "").trim()) //este método es el que valida
                         .getPayload()
                         .get("userName", String.class);
                // Aquí podríamos capturar el rol si queremos manejar con autorizaciones
                role = "ROLE_"+Jwts.parser()
                        .verifyWith((SecretKey) SECRET_KEY).build()
                        .parseSignedClaims(token.replace("Bearer", "").trim())
                        .getPayload()
                        .get("role", String.class);
            }catch(Exception ex){
                System.out.println("Ocurrio un error en:"+ex.getMessage());
                user=null;
            }            
            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticación por username/password 
            // por ese motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            // Aqui deberiamos de capturar el rol si queremos manejar con autorizaiones // el emptyList deberia de remplazarce por la lista de roles
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_"+role)  );
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, roles)  : null;
        }
        return null;
    }
}

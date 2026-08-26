/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author Acer3
 */
public class clsSecurityUtils {
    // 1. Genera un hash seguro para guardar en la BD al registrar usuario
    public static String opHashPassword(String prmPlainPassword) {
        return BCrypt.hashpw(prmPlainPassword, BCrypt.gensalt(12));
    }

    // 2. Compara la contraseña ingresada en el Login con el hash de la BD
    public static boolean opCheckPassword(String prmPlainPassword, String prmHashedPassword) {
        if (prmHashedPassword == null || !prmHashedPassword.startsWith("$2a$")) {
            return false;
        }
        return BCrypt.checkpw(prmPlainPassword, prmHashedPassword);
    }
}

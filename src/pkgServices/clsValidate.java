/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices;

import java.util.regex.Pattern;

/**
 *
 * @author Acer3
 */
public class clsValidate {
    // Expressión regular para la contraseña:
    // Min 6 caracteres, al menos un dígito (?=.*\\d), al menos una mayúscula (?=.*[A-Z]), y al menos un carácter especial (?=.*[^a-zA-Z0-9])

    private static final String USER_PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{6,}$";
    private static final Pattern USER_PASSWORD_PATTERN = Pattern.compile(USER_PASSWORD_REGEX);

    /**
     * Valida el nombre de usuario (nickname). Requisito: Obligatorio (no nulo
     * ni vacío).
     * @param prmNickname
     * @return 
     */
    public static boolean opValidNickname(String prmNickname) {
        return prmNickname != null && !prmNickname.trim().isEmpty();
    }

    /**
     * Valida el nombre completo. Requisito: Obligatorio (no nulo ni vacío).
     * @param prmFullName
     * @return 
     */
    public static boolean opValidFullName(String prmFullName) {
        return prmFullName != null && !prmFullName.trim().isEmpty();
    }

    /**
     * Valida la contraseña del usuario. Requisitos: Mínimo 6 caracteres, al
     * menos un dígito, al menos una mayúscula y al menos un carácter especial.
     * @param prmPassword
     * @return 
     */
    public static boolean opValidUserPassword(String prmPassword) {
        if (prmPassword == null) {
            return false;
        }
        return USER_PASSWORD_PATTERN.matcher(prmPassword).matches();
    }
}

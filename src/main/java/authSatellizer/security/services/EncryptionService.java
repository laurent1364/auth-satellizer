package authSatellizer.security.services;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface EncryptionService {

    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}

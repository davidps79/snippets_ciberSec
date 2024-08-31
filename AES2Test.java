import javax.crypto.*;
import javax.crypto.spec.*;

class AES2Test {
    public static void main(String[] args) {
        
        String aesType = "AES-256"; // "AES-128", "AES-192" o "AES-256"

        try {
            byte[] theKey = null;
            byte[] theMsg = null;
            byte[] theExp = null;

            switch (aesType) {
                case "AES-128":
                    theKey = hexToBytes("2B7E151628AED2A6ABF7158809CF4F3C");
                    theMsg = hexToBytes("6BC1BEE22E409F96E93D7E117393172A");
                    theExp = hexToBytes("3AD77BB40D7A3660A89ECAF32466EF97");
                    break;
                case "AES-192":
                    theKey = hexToBytes("8E73B0F7DA0E6452C810F32B809079E5" + "62F8EAD2522C6B7B");
                    theMsg = hexToBytes("6BC1BEE22E409F96E93D7E117393172A");
                    theExp = hexToBytes("BD334F1D6E45F25FF712A214571FA5CC");
                    break;
                case "AES-256":
                    theKey = hexToBytes("603DEB1015CA71BE2B73AEF0857D7781" + "1F352C073B6108D72D9810A30914DFF4");
                    theMsg = hexToBytes("6BC1BEE22E409F96E93D7E117393172A");
                    theExp = hexToBytes("F3EED1BDB5D2A03C064B5A7E3DB181F8");
                    break;
                default:
                    System.out.println("Invalid AES Type");
                    return;
            }

            SecretKeySpec ky = new SecretKeySpec(theKey, "AES");
            Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");

            // Cifrar
            cf.init(Cipher.ENCRYPT_MODE, ky);

            byte[] theCph = cf.doFinal(theMsg);
            System.out.println("AES Type: " + aesType);
            System.out.println("Key     : " + bytesToHex(theKey));
            System.out.println("Message : " + bytesToHex(theMsg));
            System.out.println("Cipher  : " + bytesToHex(theCph));
            System.out.println("Expected: " + bytesToHex(theExp));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16)
                    str.append("0").append(Integer.toHexString(data[i] & 0xFF));
                else
                    str.append(Integer.toHexString(data[i] & 0xFF));
            }
            return str.toString().toUpperCase();
        }
    }
}
